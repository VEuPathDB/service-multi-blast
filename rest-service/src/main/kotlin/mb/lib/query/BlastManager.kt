package mb.lib.query

import mb.api.model.dmnd.databaseFile
import mb.api.service.http.job.makeDBPaths
import mb.api.service.http.job.makeOrthoDBPath
import mb.api.service.model.ErrorMap
import mb.lib.model.*
import mb.lib.query.model.*
import mb.lib.util.logger
import mb.lib.workspace.Workspaces
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.hash_id.HashID
import java.io.InputStream
import java.lang.IllegalStateException
import java.time.OffsetDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import kotlin.math.min

// TODO: Good gourd this class needs to be refactored
object BlastManager {
  private val Log = logger()

  private const val MaxWorkers = 5

  /**
   * Retrieves the job identified by {@code jobID} and links the given user ID
   * to that job if that link did not already exist.
   *
   * @param jobID  ID of the job to look up.
   * @param userID ID of the user to optionally link to the job.
   *
   * @return An option that will contain a {@link FullUserBlastRow} if a job
   * exists with the id {@code jobID}.  If no such job exists, an empty option
   * will be returned.
   */
  fun getAndLinkUserBlastJob(jobID: HashID, userID: Long): FullUserBlastRow? {
    Log.trace("::getUserBlastJob(jobID={}, userID={})", jobID, userID)

    BlastDBManager().use { db ->

      // See if the user is already linked to the job.
      db.getUserBlastRow(jobID, userID)
        ?.run { FullUserBlastRow(this) }
        ?.also { tmp ->
          // They are linked, fill out the rest of the fields and return.

          tmp.targetDBs = db.getTargetLinks(jobID)
          tmp.childJobs = db.getChildJobLinks(jobID)
          tmp.parentJobs = db.getParentJobLinks(jobID)

          refreshJobStatus(db, tmp)
          updateLastModified(jobID)

          return tmp
        }

      // The user is not linked to the target job.  Check to see if the job even
      // exists.
      // If the job doesn't exist, then bail here as there is nothing to do.
      val try2 = db.getBlastRow(jobID) ?: return null

      // The job does exist, so link the user.
      val tmp = FullUserBlastRow(
        row         = try2,
        userID      = userID,
        runDirectly = true,
      )

      db.linkUser(tmp)

      // Populate the rest of the fields.
      tmp.targetDBs = db.getTargetLinks(jobID)
      tmp.childJobs = db.getChildJobLinks(jobID)
      tmp.parentJobs = db.getParentJobLinks(jobID)

      refreshJobStatus(db, tmp)
      updateLastModified(jobID)

      // Link the user to all the child jobs.
      for (job in tmp.childJobs!!) {
        if (db.getUserBlastRow(job.childJobID, userID) != null) {
          db.linkUser(UserBlastRow(
            jobID       = job.childJobID,
            userID      = userID,
            runDirectly = false
          ))
        }
      }

      return tmp
    }
  }

  fun getUserBlastJobs(userID: Long): List<FullUserBlastRow> {
    Log.trace("::getUserBlastJobs(userID={})", userID)

    BlastDBManager().use { db ->
      val results = db.getUserJobs(userID)

      if (results.isEmpty())
        return emptyList()

      val jobLinks = db.getUserJobLinks(userID)
      val tgtLinks = db.getUserTargetLinks(userID)
      val out = results.asSequence()
        .map(::FullUserBlastRow)
        .onEach { r -> r.childJobs = jobLinks.byParentID[r.jobID] }
        .onEach { r -> r.parentJobs = jobLinks.byChildID[r.jobID] }
        .onEach { r -> r.targetDBs = tgtLinks[r.jobID] }
        .toList()

      if (out.size == 1) {
        refreshJobStatus(db, out[0])
        updateLastModified(out[0].jobID)
      } else {
        asyncRefreshJobStatuses(db, out)
      }

      return out
    }
  }

  fun getJobQuery(jobID: HashID): InputStream? {
    Log.trace("::getJobQuery(jobID={})", jobID)
    return BlastDBManager().use { db ->
      updateLastModified(jobID)
      db.getJobQuery(jobID)
    }
  }

  fun rerunJob(jobID: HashID, userID: Long) {
    Log.trace("::rerunJob(jobID={}, userID={})", jobID, userID)

    val job = getAndLinkUserBlastJob(jobID, userID) ?: return

    // Refresh/update job targets.
    //
    // Because the blast DBs for each site build are kept in a build directory
    // separate from the previous build (then eventually deleted after release)
    // the target blast DB used for the job originally may no longer exist.
    //
    // This attempts to locate the same target in a later build to re-run the
    // job against.
    //
    // For example:
    //   Assume our job was created on build-49 targeting Foo organism's
    //   annotated transcripts and has since expired.
    //
    //   We then attempt to re-run our expired job on build-53.
    //
    //   At this point, the original (build-49) blast targets have been deleted
    //   and the job cannot be re-run as is.  We need to try to locate Foo
    //   organism's annotated transcripts in the build-53 blast targets now.
    //
    // If the blast target does not exist anymore the re-run request will fail
    // with a 400 error.
    var isDiamond = false

    when (val w = job.config!!) {
      is BlastConfig   -> (w.config as BlastQueryConfig).dbFile = makeDBPaths(job.projectID!!, job.targetDBs!!)
      is DiamondConfig -> {
        w.config.databaseFile = makeOrthoDBPath(job.projectID!!)
        isDiamond = true
      }
    }

    BlastDBManager().use { db ->
      // If the job is not expired then there is nothing to do
      if (job.status == JobStatus.Expired) {
        Log.debug("Resubmitting parent job {}", jobID)

        with(Workspaces.open(jobID)) {
          val ws = if (isDiamond) resolveAsDiamond() else resolveAsBlast()
          if (ws.createIfNotExists())
            db.getJobQuery(jobID)!!.use { ws.createQueryFile(it) }
        }

        val qID = BlastQueueManager.submitNewJob(jobID, job.config!!)

        db.updateJobStatus(jobID, qID, JobStatus.Queued)
      }

      // For each child job, do the same
      for (link in db.getChildJobLinks(jobID)) {
        val child = db.getBlastRow(link.childJobID) ?: throw IllegalStateException()

        refreshJobStatus(db, child)

        if (child.status == JobStatus.Expired) {
          Log.debug("Resubmitting child job {} of parent {}", child.jobID, jobID)

          with(Workspaces.open(child.jobID)) {
            val ws = if (isDiamond) resolveAsDiamond() else resolveAsBlast()
            if (ws.createIfNotExists())
              db.getJobQuery(jobID)!!.use { ws.createQueryFile(it) }
          }

          val qID = BlastQueueManager.submitNewJob(child.jobID, job.config!!)

          db.updateJobStatus(child.jobID, qID, JobStatus.Queued)
        }
      }
    }
  }

  private fun populateLinks(db: BlastDBManager, row: UserBlastRow) = FullUserBlastRow(row).apply {
    childJobs = db.getChildJobLinks(row.jobID)
    parentJobs = db.getParentJobLinks(row.jobID)
    targetDBs = db.getTargetLinks(row.jobID)
  }

  private fun submitToQueueIfExpired(db: BlastDBManager, row: BlastRow, queryProvider: () -> InputStream) {
    if (row.status == JobStatus.Expired) {
      val workspace = when (row.config!!) {
        is BlastConfig   -> Workspaces.open(row.jobID).resolveAsBlast()
        is DiamondConfig -> Workspaces.open(row.jobID).resolveAsDiamond()
      }

      if (workspace.createIfNotExists())
        queryProvider().use { workspace.createQueryFile(it) }

      val queueID = BlastQueueManager.submitNewJob(row.jobID, row.config!!)

      row.queueID = queueID
      row.status = JobStatus.Queued

      db.updateJobStatus(row.jobID, queueID, JobStatus.Queued)
    }
  }

  private fun asyncRefreshJobStatuses(
    db: BlastDBManager,
    rows: Collection<BlastRow>,
  ) {
    val pool = Executors.newFixedThreadPool(min(rows.size, MaxWorkers))

    val counter = CountDownLatch(rows.size)
    for (row in rows) {
      pool.execute(asyncRefreshJobStatus(db, row, counter))
    }

    counter.await()
    pool.shutdownNow()
  }

  private fun asyncRefreshJobStatus(
    db: BlastDBManager,
    row: BlastRow,
    counter: CountDownLatch,
  ): () -> Unit {
    return {
      try {
        refreshJobStatus(db, row)
        updateLastModified(row.jobID)
        counter.countDown()
      } catch (e: Exception) {
        counter.countDown()
        throw RuntimeException(e)
      }
    }
  }

  /**
   * Refreshes the job status by checking the job workspace.
   *
   * Status conditions:
   * * Job previously failed    -> Failed
   * * Workspace does not exist -> Expired
   * * Has "completed" flag     -> Completed
   * * Has "failed" flag        -> Failed
   * * Has no flag files        -> In Progress
   *
   * This method mutates the input [BlastRow] to update the status field with
   * the newly determined status.
   *
   * This method additionally updates the database status if the status has
   * changed from the previously observed status.
   *
   * @param db Active DB session
   *
   * @param row Blast record from the DB
   */
  private fun refreshJobStatus(db: BlastDBManager, row: BlastRow) {
    // If the job failed, there is no need to proceed any further, we don't
    // re-run failed jobs.
    if (row.status == JobStatus.Errored)
      return

    val status = Workspaces.open(row.jobID).let { ws ->
      if (!ws.exists) {
        Log.debug("Blast workspace {} does not exist.", row.jobID)
        JobStatus.Expired
      } else {
        val resolved = ws.resolve()

        if (resolved.hasSuccessFlag()) {
          Log.debug("Blast workspace {} contains a completed flag.", row.jobID)
          JobStatus.Completed
        } else if (resolved.hasFailedFlag()) {
          Log.debug("Blast workspace {} contains a failed flag.", row.jobID)
          JobStatus.Errored
        } else {
          JobStatus.InProgress
        }
      }
    }

    if (status != row.status)
      db.updateJobStatus(row.jobID, row.queueID!!, status)

    row.status = status
  }

  fun validateConfig(conf: BlastQueryConfig): ErrorMap? {
    return BlastValidationManager.validate(conf)
  }

  /**
   * Updates the last modified date on a job workspace to delay its expiration as
   * it is still being actively used.
   *
   * @param jobID
   * ID of the job which will have its workspace last modified date updated.
   */
  fun updateLastModified(jobID: HashID) {
    Log.trace("::updateLastModified(jobID={})", jobID)

    Workspaces.open(jobID)
      .takeIf { it.exists }
      ?.resolve()
      ?.updateLastModified(OffsetDateTime.now())
  }

  fun getJobError(jobID: HashID): String? {
    Log.trace("::getJobError(jobID={})", jobID)

    return Workspaces.open(jobID)
      .takeIf { it.exists }
      ?.resolve()
      ?.takeIf { it.hasErrorFile }
      ?.errorFile
      ?.readText()
  }

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Job Creation                                                      ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  fun submitJob(job: MBlastJob) = BlastDBManager().use { handleJobs(it, job) }

  private fun handleJobs(db: BlastDBManager, job: MBlastJob): FullUserBlastRow {

    val root = handleJob(db, job, job.query::getFullQuery)

    // If the job config is not a BlastConfig then it is for protein mapping
    // which does not have sub-jobs.
    //
    // If size is 1 then it's the whole query, which we've already handled.
    if (job.config is BlastConfig && job.query.sequences.size > 1) {

      // We reuse the job instance here since we would otherwise be done with
      // it.  Just a small optimization to avoid a bunch of repeated
      // instantiations.

      // Set the parent to the original job ID.
      job.parentID = root.jobID

      // Sets the primary flag to false as this is a new child job and should
      // be hidden by default.
      job.isPrimary = false

      for (query in job.query.sequences) {
        handleJob(db, job, query::toStandardString)
      }
    }

    return populateLinks(db, root)
  }

  private fun handleJob(db: BlastDBManager, job: MBlastJob, queryProvider: () -> InputStream): UserBlastRow {
    val jobID = queryProvider().use { job.digest(it) }

    var row = getUserBlastRow(db, jobID, job) ?: getBlastRow(db, jobID, job)

    if (row != null)
      return row

    Log.info("Cache miss for job {}.", jobID)

    // No matching job exists.

    val tim = OffsetDateTime.now()

    row = UserBlastRow(
      jobID           = jobID,
      config          = job.config,
      status          = JobStatus.Expired,
      projectID       = job.site,
      createdOn       = tim,
      deleteOn        = tim,
      userID          = job.userID,
      description     = job.description,
      maxDownloadSize = job.maxDLSize,
      runDirectly     = job.isPrimary
    )

    submitToQueueIfExpired(db, row, queryProvider)

    queryProvider().use { db.insertJob(row, it) }
    db.linkUser(row)
    db.linkTargets(jobID, *job.targets)

    if (!job.isPrimary && job.parentID != null)
      db.linkJobs(jobID, job.parentID!!)

    return row
  }

  private fun getUserBlastRow(
    db:    BlastDBManager,
    jobID: HashID,
    job:   MBlastJob
  ): UserBlastRow? {
    val row = db.getUserBlastRow(jobID, job.userID) ?: return null

    Log.info("Cache hit for job {} and user {}.", jobID, job.userID)

    // If the user has this job already exists, refresh it to see if it needs to
    // be rerun, then rerun it if needed.

    refreshJobStatus(db, row)
    submitToQueueIfExpired(db, row) { db.getJobQuery(jobID)!! }

    if (!job.isPrimary && job.parentID != null) {
      // Look for an existing link between these jobs.
      db.getParentJobLinks(jobID).forEach {
        // If we find one, skip out because we don't need to insert a link.
        if (it.parentJobID == job.parentID)
          return row
      }

      // Link this job to the parent (no existing parent link was found).
      db.linkJobs(jobID, job.parentID!!)
    }

    return row
  }

  private fun getBlastRow(
    db:    BlastDBManager,
    jobID: HashID,
    job:   MBlastJob
  ): UserBlastRow? {
    val unlinked = db.getBlastRow(jobID) ?: return null

    Log.info("Cache hit for job {}.", jobID)

    // If the job already exists, but is not linked to this user...

    // Link the job to the current user.
    val row = UserBlastRow(
      unlinked,
      job.userID,
      job.description,
      job.maxDLSize,
      job.isPrimary
    )

    db.linkUser(row)

    // Refresh the job's status.
    refreshJobStatus(db, row)

    // Rerun the job if needed.
    submitToQueueIfExpired(db, row) { db.getJobQuery(jobID)!! }

    if (!job.isPrimary && job.parentID != null) {
      // Look for an existing link between these jobs.
      db.getParentJobLinks(jobID).forEach {
        // If we find one, skip out because we don't need to insert a link.
        if (it.parentJobID == job.parentID)
          return row
      }

      // Link this job to the parent (no existing parent link was found).
      db.linkJobs(jobID, job.parentID!!)
    }

    return row
  }
}
