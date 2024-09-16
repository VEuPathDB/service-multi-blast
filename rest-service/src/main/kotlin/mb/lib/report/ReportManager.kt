package mb.lib.report

import com.fasterxml.jackson.module.kotlin.readValue
import mb.lib.report.model.ReportMeta
import mb.lib.model.JobStatus
import mb.lib.query.BlastDBManager
import mb.lib.query.model.DiamondConfig
import mb.lib.report.model.ReportJob
import mb.lib.report.model.ReportPayload
import mb.lib.report.model.ReportRow
import mb.lib.report.model.UserReportRow
import mb.lib.workspace.BlastWorkspace
import mb.lib.workspace.DiamondReportWorkspace
import mb.lib.workspace.DiamondWorkspace
import mb.lib.workspace.Workspaces
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import java.io.File
import java.time.OffsetDateTime

object ReportManager {
  private val Log = LogManager.getLogger("ReportManager")

  /**
   * Returns an optional input stream over the specified file in the target
   * report workspace.
   *
   * If the job workspace, report workspace, or target file do not exist, this
   * method will return an empty option.
   *
   * @param row  Report job record.
   * @param file Target file name.
   *
   * @return an optional input stream over the target file.
   */
  fun getReportFile(row: ReportRow, file: String): File? =
    Workspaces.open(row.jobID)
      .resolve()
      .takeIf { it.exists }
      ?.let {
        when (it) {
          is BlastWorkspace   -> getReportFile(row, it, file)
          is DiamondWorkspace -> getReportFile(row, it, file)
        }
      }

  private fun getReportFile(row: ReportRow, workspace: DiamondWorkspace, file: String) =
    if (file == DiamondReportWorkspace.ReportZip)
      workspace.reportWorkspace(row.reportID)
        .getFile(file)
        .takeIf { it.exists() }
    else
      null

  private fun getReportFile(row: ReportRow, workspace: BlastWorkspace, file: String) =
    workspace.reportWorkspace(row.reportID)
      .getFile(file)
      .takeIf { it.exists() }

  fun getUserReportsForJob(jobID: HashID, userID: Long): List<UserReportRow> {
    Log.trace("::getUserReportsForJob(jobID={}, userID={})", jobID, userID)

    ReportDBManager().use {
      val out = it.getUserJobReports(jobID, userID)

      for (rep in out) {
        if (rep.status == JobStatus.Queued || rep.status == JobStatus.InProgress)
          updateJobStatus(rep)
      }

      return out
    }
  }

  fun getAllReportsForUser(userID: Long): List<UserReportRow> {
    Log.trace("::getAllReportsForUser(userID={})", userID)

    // For the user's diamond jobs, create imaginary report records to have
    // something to show and for the client to use.
    val results = BlastDBManager().use {
      it.getUserJobs(userID)
        .asSequence()
        .filter { it.config is DiamondConfig }
        .map { UserReportRow(it.jobID, it.jobID, it.status!!, null, it.queueID!!, it.createdOn!!, userID, null) }
        .toMutableList()
    }

    ReportDBManager().use {
      it.getUserJobReports(userID).forEach { rep ->
        if (rep.status == JobStatus.Queued || rep.status == JobStatus.InProgress)
          updateJobStatus(rep)

        results.add(rep)
      }
    }

    return results
  }

  /**
   * Updates the job status field for the given report job.
   *
   * @param row Report job row to update.
   */
  fun updateJobStatus(row: ReportRow) {
    Log.trace("::updateJobStatus(row={})", row)
    ReportDBManager().use { refreshJobStatus(it, row) }
  }

  fun getReportFiles(row: ReportRow): List<String> =
    Workspaces.open(row.jobID)
      .resolve()
      .takeIf { it is BlastWorkspace }
      ?.takeIf { it.exists }
      ?.reportWorkspace(row.reportID)
      ?.takeIf { it.exists }
      ?.takeIf { it.hasMetaJson }
      ?.let { Json.Mapper.readValue<ReportMeta>(it.metaJson) }
      ?.files
      ?: emptyList()

  /**
   * Looks up a report by ID and links the given user to the report if they were
   * not already linked to the specified report job.
   *
   * @param reportID ID of the report job to look up.
   * @param userID   ID of the user to optionally link to the report.
   *
   * @return An option that will contain a user report row for the given user id
   * if such a job exists.
   */
  fun getAndLinkReport(reportID: HashID, userID: Long): UserReportRow? {
    Log.trace("::getAndLinkReport(reportID={}, userID={})", reportID, userID)

    ReportDBManager().use { db ->
      val userRow = db.getUserReportJob(reportID, userID)

      if (userRow != null) {
        refreshJobStatus(db, userRow)
        Log.debug("Job status = {}", userRow.status)
        return userRow
      }

      // If we're here, then we don't yet know if the report job even exists.
      // Check for the job's existence.
      val rawRow = db.getReportJob(reportID)

      // Report doesn't exist.
      if (rawRow.isEmpty)
        return null

      // Report exists, so lets link the user to it.
      db.linkUserToReport(reportID, userID, null)

      val raw = rawRow.get()

      refreshJobStatus(db, raw)
      Log.debug("Job status = {}", raw.status)

      return UserReportRow(
        reportID,
        raw.jobID,
        raw.status,
        raw.config,
        raw.queueID,
        raw.createdOn,
        userID,
        null
      )
    }
  }

  fun rerunJob(reportID: HashID, userID: Long): UserReportRow? {
    Log.debug("::rerunJob(reportID={}, userID={})", reportID, userID)

    ReportDBManager().use { db ->
      // See if the job already exists for this user
      val try1 = db.getUserReportJob(reportID, userID)

      if (try1 != null) {
        // The user is already linked to the job
        refreshJobStatus(db, try1)

        // If the job isn't expired, then there's nothing to do.
        if (try1.status != JobStatus.Expired)
          return try1

        // Create a workspace for the report job.
        val jobWs = Workspaces.open(try1.jobID).resolve()

        if (jobWs !is BlastWorkspace)
          throw IllegalStateException("attempted to rerun a report on a non-blast job")

        jobWs.reportWorkspace(reportID).createIfNotExists()

        // Submit the job to be re-run
        val queueID = ReportQueueManager.submitNewJob(ReportPayload(
          try1.jobID,
          try1.reportID,
          try1.config
        ))

        // Build an updated job row
        try1.status  = JobStatus.Queued
        try1.queueID = queueID

        // Update the DB
        db.updateReportRow(try1)

        return try1
      }

      // Job doesn't exist OR user is not linked to job.

      // Try to load the job.
      val try2 = db.getReportJob(reportID)

      if (try2.isPresent) {
        val raw = try2.get()

        db.linkUserToReport(reportID, userID, null)
        refreshJobStatus(db, raw)

        val job = UserReportRow(raw, userID, null)

        // Job isn't expired, nothing to do.
        if (raw.status != JobStatus.Expired)
          return job

        // Resubmit the job.
        job.status  = JobStatus.Queued
        job.queueID = ReportQueueManager.submitNewJob(ReportPayload(job.jobID, job.reportID, job.config))

        // Update the job status in the DB.
        db.updateReportRow(job)

        return job
      }

      // Report job doesn't exist, return empty.
      return null
    }
  }

  /**
   * Creates a new report job from the given information.
   *
   * If a job matching the digest of the given {@link ReportJob} already exists,
   * a new job will not be created, instead the requesting user will be linked
   * to the existing job.  If the matching job has expired, it will be
   * re-queued.
   *
   * @param job Configuration from which a report job should be created.
   *
   * @return A row representing the newly created report job.
   */
  fun newReportJob(job: ReportJob): UserReportRow {
    Log.trace("::newReportJob(job={})", job)

    ReportDBManager().use { db ->
      val reportID = job.getReportID()

      // Look up the target report job to see if it already exists.
      val optOld = db.getReportJob(reportID)

      // If a report with our hash already exists
      if (optOld.isPresent) {
        Log.debug("Found pre-existing job with this reportID.")

        return handlePreExistingNewReport(db, reportID, optOld.get(), job)
      }

      // No report with that hash exists
      Log.debug("No pre-existing job found.")

      // Create a report job workspace.
      Workspaces.open(job.jobID)
        .resolve()
        .takeIf { it is BlastWorkspace }
        ?.reportWorkspace(reportID)
        ?.createIfNotExists()
        ?: throw IllegalStateException("attempted to create a new report for a non-blast query")

      // Queue the job to be executed.
      val queueID = ReportQueueManager.submitNewJob(ReportPayload(
        job.jobID,
        job.getReportID(),
        job.config
      ))

      // Create the report job and link the current user to it.
      val row = UserReportRow(
        reportID,
        job.jobID,
        JobStatus.Queued,
        job.config,
        queueID,
        OffsetDateTime.now(),
        job.userID,
        job.description
      )
      db.insertReportRow(row)
      db.linkUserToReport(reportID, job.userID, job.description)

      return row
    }
  }

  /**
   * Handle "newReport" requests for report jobs that already existed.
   */
  private fun handlePreExistingNewReport(
    db: ReportDBManager,
    reportID: HashID,
    oldJob: ReportRow,
    job: ReportJob,
  ): UserReportRow {

    // Update the report job's status
    refreshJobStatus(db, oldJob)

    // If the job is expired, rerun it for the new requesting user.
    if (oldJob.status == JobStatus.Expired) {

      // Create the report job workspace.
      Workspaces.open(oldJob.jobID)
        .resolve()
        .takeIf { it is BlastWorkspace }
        ?.reportWorkspace(oldJob.reportID)
        ?.createIfNotExists()
        ?: throw IllegalStateException("attempted to create or link a new report on a non-blast query job")

      // Queue the job
      ReportQueueManager.submitNewJob(ReportPayload(job.jobID, job.getReportID(), job.config))

      // Update the status in the DB
      oldJob.status = JobStatus.Queued

      db.updateReportRow(oldJob)
    }

    // Check to see if the user is already linked to the report job.
    db.getUserReportJob(reportID, job.userID)?.let {
      Log.debug("User is already linked to report job.")

      // If the user submitted the new report job with a different
      // description than last time, update the description.
      if (it.description != job.description) {
        val out = UserReportRow(
          it.reportID,
          it.jobID,
          it.status,
          it.config,
          it.queueID,
          it.createdOn,
          it.userID,
          job.description
        )

        db.updateReportDescription(out)

        return out
      }

      // Since the description hasn't changed, we can just return the row
      // we found.
      return it
    }

    Log.debug("User is not already linked to report job.")

    // The report job exists, but the user is not linked to it yet.  Link
    // the user to the report job and return a record that has been updated
    // to reflect the user being linked.
    db.linkUserToReport(reportID, job.userID, job.description)

    return UserReportRow(oldJob, job.userID, job.description)
  }

  /**
   * Update the given report job's status.
   *
   * Status is checked by:
   * 1. Blast workspace exists or else status == expired
   * 2. Report workspace exists or else status == expired
   * 3. Report workspace has a completion flag or else status == in progress
   *
   * @param db Active DB session
   *
   * @param row Current report row from the database.
   */
  private fun refreshJobStatus(db: ReportDBManager, row: ReportRow) {

    // Determine job status.
    val status = Workspaces.open(row.jobID)
      .resolve()
      .takeIf { it is BlastWorkspace }
      ?.let bw@ { bws ->
        // If the blast workspace does not exist, the job has expired.
        if (!bws.exists) {
          Log.debug("Blast job workspace {} does not exist.", row.jobID)
          return@bw JobStatus.Expired
        }

        // Test the report workspace
        bws.reportWorkspace(row.reportID).let { rws ->
          when {
            // If the report workspace does not exist, the job has expired?
            !rws.exists -> {
              Log.debug("Report job {}/{} does not exist.", row.jobID, row.reportID)
              JobStatus.Expired
            }

            // If the report workspace contains a success flag file, the job
            // completed successfully.
            rws.hasSuccessFlag() -> {
              Log.debug("Report job {}/{} has a success flag.", row.jobID, row.reportID)
              JobStatus.Completed
            }

            // If the report workspace contains a failed flag file, the job
            // completed unsuccessfully.
            rws.hasFailedFlag() -> {
              Log.debug("Report job {}/{} has a failed flag.", row.jobID, row.reportID)
              JobStatus.Errored
            }

            // If the workspace exists, but there is no completion flag, then the
            // job is either still in progress, or in limbo due to a filesystem
            // error.
            else -> JobStatus.InProgress
          }
        }
      }
      ?: throw IllegalStateException("attempted to get a report job status for a non-blast query")

    // If the status has changed since it was last observed, update the
    // database.
    if (status != row.status) {
      row.status = status
      db.updateReportRow(row)
    }
  }
}
