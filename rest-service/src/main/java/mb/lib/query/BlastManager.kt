@file:JvmName("BlastManager")

package mb.lib.query

import mb.api.service.http.job.makeDBPaths
import mb.api.service.model.ErrorMap
import mb.lib.data.JobDataManager
import mb.lib.model.HashID
import mb.lib.model.JobStatus
import mb.lib.query.model.*
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.blast.BlastQueryConfig
import java.lang.IllegalStateException
import java.time.OffsetDateTime
import java.util.Optional
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.stream.Collectors
import kotlin.math.min


object BlastManager {
  private val Log = LogManager.getLogger("BlastManager")

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
      val out = results.stream()
        .map(::FullUserBlastRow)
        .peek { r -> r.childJobs = jobLinks.byParentID[r.jobID] }
        .peek { r -> r.parentJobs = jobLinks.byChildID[r.jobID] }
        .peek { r -> r.targetDBs = tgtLinks[r.jobID] }
        .collect(Collectors.toList())

      if (out.size == 1) {
        refreshJobStatus(db, out[0])
        updateLastModified(out[0].jobID)
      } else {
        asyncRefreshJobStatuses(db, out)
      }

      return out
    }
  }

  fun getJobQuery(jobID: HashID): Optional<String> {
    Log.trace("::getJobQuery(jobID={})", jobID)
    return BlastDBManager().use { db ->
      updateLastModified(jobID)
      db.getBlastQuery(jobID)
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
    (job.config as BlastQueryConfig).dbFile = makeDBPaths(job.projectID!!, job.targetDBs!!)

    BlastDBManager().use { db ->
      // If the job is not expired then there is nothing to do
      if (job.status == JobStatus.Expired) {
        Log.debug("Resubmitting parent job {}", jobID)

        JobDataManager.createJobWorkspace(jobID)
        JobDataManager.createQueryFile(jobID, job.query!!)

        val qID = BlastQueueManager.submitNewJob(jobID, job.config!!)

        db.updateJobQueue(jobID, qID, JobStatus.Queued)
      }

      // For each child job, do the same
      for (link in db.getChildJobLinks(jobID)) {
        val child = db.getBlastRow(link.childJobID) ?: throw IllegalStateException()

        refreshJobStatus(db, child)

        if (child.status == JobStatus.Expired) {
          Log.debug("Resubmitting child job {} of parent {}", child.jobID, jobID)

          JobDataManager.createJobWorkspace(child.jobID)
          JobDataManager.createQueryFile(child.jobID, job.query!!)

          val qID = BlastQueueManager.submitNewJob(child.jobID, job.config!!)

          db.updateJobQueue(child.jobID, qID, JobStatus.Queued)
        }
      }
    }
  }

  private fun populateLinks(db: BlastDBManager, row: UserBlastRow) = FullUserBlastRow(row).apply {
    childJobs = db.getChildJobLinks(row.jobID)
    parentJobs = db.getParentJobLinks(row.jobID)
    targetDBs = db.getTargetLinks(row.jobID)
  }

  private fun submitToQueueIfExpired(db: BlastDBManager, row: BlastRow) {
    if (row.status == JobStatus.Expired) {
      JobDataManager.createJobWorkspace(row.jobID)
      JobDataManager.createQueryFile(row.jobID, row.query!!)

      val queueID = BlastQueueManager.submitNewJob(row.jobID, row.config!!)

      row.queueID = queueID
      row.status = JobStatus.Queued

      db.updateJobQueue(row.jobID, queueID, JobStatus.Queued)
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

  private fun refreshJobStatus(db: BlastDBManager, row: BlastRow) {
    Log.trace("::refreshJobStatus(db={}, row={})", db, row)

    when (row.status!!) {
      // If the job is marked as expired or errored there's nothing to do.
      JobStatus.Errored, JobStatus.Expired -> return

      // If the job is marked as queued or in progress, see if the status has
      // changed.
      JobStatus.Queued, JobStatus.InProgress -> {
        var status = BlastQueueManager.getJobStatus(row.queueID!!)

        if (status == row.status)
          return

        // To handle the case where a user fires off a job and then doesn't come
        // back to view it in the expiration period, check the filesystem to
        // confirm the job workspace still exists.
        if (status == JobStatus.Completed && !JobDataManager.workspaceExists(row.jobID)) {
          status = JobStatus.Expired
        }

        db.updateJobQueue(row.jobID, row.queueID!!, status)
        row.status = status

        if (status == JobStatus.Errored) {
          BlastQueueManager.failedJobs
            .stream()
            .filter { r -> r.jobID == row.queueID }
            .map { j -> j.failID }
            .forEach { i ->
              try {
                BlastQueueManager.deleteJobFailure(i)
              } catch (e: Exception) {
                throw RuntimeException(e)
              }
            }
        }

        return
      }

      //
      JobStatus.Completed ->
        if (!JobDataManager.workspaceExists(row.jobID)) {
          db.updateJobQueue(row.jobID, row.queueID!!, JobStatus.Expired)
          row.status = JobStatus.Expired
        }
    }
  }

  fun validateConfig(conf: BlastQueryConfig): Optional<ErrorMap> {
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

    val wd = JobDataManager.jobWorkspace(jobID)

    if (!wd.exists)
      return

    wd.updateLastModified()
  }

  fun getJobError(jobID: HashID): String? {
    Log.trace("::getJobError(jobID={})", jobID)

    val wd = JobDataManager.jobWorkspace(jobID)

    if (!wd.exists || !wd.errorFileExists)
      return null

    return wd.errorText
  }

  // ╔══════════════════════════════════════════════════════════════════════╗ //
  // ║                                                                      ║ //
  // ║    Job Creation                                                      ║ //
  // ║                                                                      ║ //
  // ╚══════════════════════════════════════════════════════════════════════╝ //

  fun submitJob(job: BlastJob) = BlastDBManager().use { handleJobs(it, job) }

  private fun handleJobs(db: BlastDBManager, job: BlastJob): FullUserBlastRow {
    Log.trace("#handleJobs(db=$db, job=$job")

    val root = handleJob(db, job, job.query.fullQuery)

    // If size is 1 then it's the whole query, which we've already handled.
    if (job.query.sequenceCount > 1) {

      // We reuse the job instance here since we would otherwise be done with
      // it.  Just a small optimization to avoid a bunch of repeated
      // instantiations.

      // Set the parent to the original job ID.
      job.parentID = root.jobID

      // Sets the primary flag to false as this is a new child job and should
      // be hidden by default.
      job.isPrimary = false

      for (query in job.query.subQueries) {
        handleJob(db, job, query.toString())
      }
    }

    return populateLinks(db, root)
  }

  private fun handleJob(db: BlastDBManager, job: BlastJob, query: String): UserBlastRow {
    Log.trace("#handleJob(db=$db, job=$job, query=$query")

    val jobID = job.digest(query)

    // Check to see if this user already has a job matching this one.
    db.getUserBlastRow(jobID, job.userID)?.also { row ->
      // If the user has this job already exists, refresh it to see if it needs to
      // be rerun, then rerun it if needed.

      refreshJobStatus(db, row)
      submitToQueueIfExpired(db, row)

      if (!job.isPrimary)
        db.linkJobs(jobID, job.parentID!!)

      return row
    }

    // The user does not already have a matching job.

    // Check to see if this job already exists (not linked to this user)
    db.getBlastRow(jobID)?.also {
      // If the job already exists, but is not linked to this user...

      // Link the job to the current user.
      val row = UserBlastRow(it, job.userID, job.description, job.maxDLSize, job.isPrimary)
      db.linkUser(row)

      // Refresh the job's status.
      refreshJobStatus(db, row)

      // Rerun the job if needed.
      submitToQueueIfExpired(db, row)

      if (!job.isPrimary)
        db.linkJobs(jobID, job.parentID!!)

      return row
    }

    // No matching job exists.

    val tim = OffsetDateTime.now()
    val row = UserBlastRow(
      jobID           = jobID,
      config          = job.config,
      query           = query,
      status          = JobStatus.Expired,
      projectID       = job.site,
      createdOn       = tim,
      deleteOn        = tim,
      userID          = job.userID,
      description     = job.description,
      maxDownloadSize = job.maxDLSize,
      runDirectly     = job.isPrimary
    )

    submitToQueueIfExpired(db, row)

    db.insertJob(row)
    db.linkUser(row)
    db.linkTargets(jobID, *job.targets)

    if (!job.isPrimary)
      db.linkJobs(jobID, job.parentID!!)

    return row
  }

}
