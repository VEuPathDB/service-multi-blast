
package mb.lib.report

import mb.lib.report.model.ReportMeta
import mb.lib.model.JobStatus
import mb.lib.report.model.ReportJob
import mb.lib.report.model.ReportPayload
import mb.lib.report.model.ReportRow
import mb.lib.report.model.UserReportRow
import mb.lib.workspace.Workspaces
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import java.io.File
import java.time.OffsetDateTime
import java.util.Optional

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
  fun getReportFile(row: ReportRow, file: String): File? {
    with(Workspaces.open(row.jobID)) {
      exists || return null

      val rs = reportWorkspace(row.reportID)

      rs.exists || return null

      return rs.getFile(file).let { if (it.exists()) it else null }
    }
  }

  fun getUpdatedUserReport(reportID: HashID, userID: Long): UserReportRow? {
    Log.trace("::getUserReport(reportID={}, userID={})", reportID, userID)

    return ReportDBManager().use { db ->
      db.getUserReportJob(reportID, userID)?.also { updateJobStatus(it) }
    }
  }

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
    ReportDBManager().use {
      val out = it.getUserJobReports(userID)

      for (rep in out) {
        if (rep.status == JobStatus.Queued || rep.status == JobStatus.InProgress)
          updateJobStatus(rep)
      }

      return out
    }
  }

  /**
   * Updates the job status field for a report job matching the given report
   * hash.
   *
   * If no such report exists, this method updates nothing and returns an empty
   * option.
   *
   * @param reportID ID of the report job to have its status updated.
   *
   * @return An option containing the updated row, if such a row exists, or else
   * an empty option if no job with the given hash was found.
   */
  fun updateJobStatusIfExists(reportID: HashID): Optional<ReportRow> {
    Log.trace("::updateJobStatusIfExists(reportID={})", reportID)
    ReportDBManager().use {
      val optRow = it.getReportJob(reportID)

      if (optRow.isEmpty)
        return Optional.empty()

      refreshJobStatus(it, optRow.get())

      return Optional.of(optRow.get())
    }
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

  fun getReportFiles(row: ReportRow): List<String> {
    with(Workspaces.open(row.jobID)) {
      exists || return emptyList()

      val rs = reportWorkspace(row.reportID)
      rs.exists      || return emptyList()
      rs.hasMetaJson || return emptyList()

      return Json.Mapper
        .readValue(rs.metaJson, ReportMeta::class.java)
        .files
    }
  }

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
        Log.debug("Job status = ${userRow.status}")
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
      Log.debug("Job status = ${raw.status}")

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
      val optOld   = db.getReportJob(reportID)

      // Set the archive to the job id for hashing.
      //
      // Without this there would be hash collisions for report jobs across
      // different blast jobs.
      job.config.archiveFile = job.jobID.string

      // If a report with our hash already exists
      if (optOld.isPresent) {
        Log.debug("Found pre-existing job with this reportID.")

        return handlePreExisting(db, reportID, optOld.get(), job)
      }

      // No report with that hash exists
      Log.debug("No pre-existing job found.")

      val queueID = ReportQueueManager.submitNewJob(ReportPayload(
        job.jobID,
        job.getReportID(),
        job.config
      ))

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

  private fun handlePreExisting(
    db: ReportDBManager,
    reportID: HashID,
    oldJob: ReportRow,
    job: ReportJob,
  ): UserReportRow {
    refreshJobStatus(db, oldJob)

    // If the job is expired, rerun it for the new requesting user.
    if (oldJob.status == JobStatus.Expired) {
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

  private fun refreshJobStatus(db: ReportDBManager, row: ReportRow) {
    var status: JobStatus? = null

    // Before anything else, let's check if the workspace exists since that is
    // the fastest check.
    val ws = Workspaces.open(row.jobID)

    if (!ws.exists) {
      Log.debug("Blast job workspace does not exist.")
      status = JobStatus.Expired
    }

    val rs = ws.reportWorkspace(row.reportID)

    if (!rs.exists) {
      Log.debug("Report job workspace does not exist. ${row.reportID}")
      status = JobStatus.Expired
    }

    // If the status isn't Expired
    if (status != JobStatus.Expired) {
      status = ReportQueueManager.getJobStatus(row.queueID)
    }

    if (status != row.status) {
      row.status = status
      db.updateReportRow(row)
    }
  }
}