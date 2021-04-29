package mb.lib.formatter;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import mb.lib.data.JobDataManager;
import mb.lib.formatter.model.Payload;
import mb.lib.formatter.model.ReportJob;
import mb.lib.formatter.model.ReportRow;
import mb.lib.formatter.model.UserReportRow;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportManager
{
  private static final Logger Log = LogManager.getLogger();

  public static Optional<UserReportRow> rerunJob(HashID reportID, long userID) throws Exception {
    Log.trace("::rerunJob(reportID={}, userID={})", reportID, userID);
    try (var db = new ReportDBManager()) {
      // See if the job already exists for this user
      var try1 = db.getUserReportJob(reportID, userID);

      if (try1.isPresent()) {
        // The user is already linked to the job
        var job = try1.get();

        refreshJobStatus(db, job);

        // If the job isn't expired, then there's nothing to do.
        if (job.getStatus() != JobStatus.Expired)
          return try1;

        // Submit the job to be re-run
        var queueID = ReportQueueManager.submitJob(new Payload(
          job.getJobID(),
          job.getConfig()
        ));

        // Build an updated job row
        job.setStatus(JobStatus.Queued)
          .setQueueID(queueID);

        // Update the DB
        db.updateReportRow(job);

        return Optional.of(job);
      }

      // Job doesn't exist OR user is not linked to job.

      // Try to load the job.
      var try2 = db.getReportJob(reportID);

      if (try2.isPresent()) {
        var raw = try2.get();

        db.linkUserToReport(reportID, userID, null);
        refreshJobStatus(db, raw);

        var job = new UserReportRow(raw, userID, null);

        // Job isn't expired, nothing to do.
        if (raw.getStatus() != JobStatus.Expired)
          return Optional.of(job);

        // Resubmit the job.
        var queueID = ReportQueueManager.submitJob(new Payload(job.getJobID(), job.getConfig()));
        job.setStatus(JobStatus.Queued)
          .setQueueID(queueID);

        // Update the job status in the DB.
        db.updateReportRow(job);

        return Optional.of(job);
      }

      // Report job doesn't exist, return empty.
      return Optional.empty();
    }
  }

  /**
   * Creates a new report job from the given information.
   * <p>
   * If a job matching the digest of the given {@link ReportJob} already exists,
   * a new job will not be created, instead the requesting user will be linked
   * to the existing job.  If the matching job has expired, it will be requeued.
   *
   * @param job Configuration from which a report job should be created.
   *
   * @return A row representing the newly created report job.
   */
  public static UserReportRow newReportJob(ReportJob job) throws Exception {
    Log.trace("::newReportJob(job={})", job);
    try (var db = new ReportDBManager()) {
      var reportID = job.getReportID();
      var optOld   = db.getReportJob(reportID);

      // Set the archive to the job id for hashing.
      //
      // Without this there would be hash collisions for report jobs across
      // different blast jobs.
      job.getConfig().setArchiveFile(job.getJobID().string());

      // If a report with our hash already exists
      if (optOld.isPresent()) {
        var oldJob = optOld.get();

        refreshJobStatus(db, oldJob);

        // If the job is expired, rerun it for the new requesting user.
        if (oldJob.getStatus() == JobStatus.Expired) {
          // Queue the job
          ReportQueueManager.submitJob(new Payload(job.getJobID(), job.getConfig()));
          // Update the status in the DB
          oldJob.setStatus(JobStatus.Queued);
          db.updateReportRow(oldJob);
        }

        // Check to see if the user is already linked to the report job.
        var optFull = db.getUserReportJob(reportID, job.getUserID());
        if (optFull.isPresent()) {
          var full = optFull.get();

          // If the user submitted the new report job with a different
          // description than last time, update the description.
          if (!Objects.equals(full.getDescription(), job.getDescription())) {
            var out = new UserReportRow(
              full.getReportID(),
              full.getJobID(),
              full.getStatus(),
              full.getConfig(),
              full.getQueueID(),
              full.getCreatedOn(),
              full.getUserID(),
              job.getDescription()
            );

            db.updateReportDescription(out);

            return out;
          }

          // Since the description hasn't changed, we can just return the row
          // we found.
          return full;
        }

        // The report job exists, but the user is not linked to it yet.  Link
        // the user to the report job and return a record that has been updated
        // to reflect the user being linked.
        db.linkUserToReport(reportID, job.getUserID(), job.getDescription());

        return new UserReportRow(oldJob, job.getUserID(), job.getDescription());
      }

      // No report with that hash exists

      var queueID = ReportQueueManager.submitJob(new Payload(
        job.getJobID(),
        job.getConfig()
      ));

      var row = new UserReportRow(
        reportID,
        job.getJobID(),
        JobStatus.Queued,
        job.getConfig(),
        queueID,
        OffsetDateTime.now(),
        job.getUserID(),
        job.getDescription()
      );
      db.insertReportRow(row);
      db.linkUserToReport(reportID, job.getUserID(), job.getDescription());

      return row;
    }
  }

  public static List<String> getReportFiles(ReportRow row) throws Exception {
    Log.trace("::getReportFiles(row={})", row);

    if (!JobDataManager.workspaceExists(row.getJobID()))
      return Collections.emptyList();

    var ws = JobDataManager.jobWorkspace(row.getJobID());

    if (!ws.reportExists(row.getReportID()))
      return Collections.emptyList();

    return ws.reportWorkspace(row.getReportID()).getReportMeta().files();
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
  public static Optional<UserReportRow> getAndLinkReport(HashID reportID, long userID)
  throws Exception {
    Log.trace("::getAndLinkReport(reportID={}, userID={})", reportID, userID);
    try (var db = new ReportDBManager()) {
      // See if the user is already linked to the report job.
      var userRow = db.getUserReportJob(reportID, userID);

      if (userRow.isPresent())
        return userRow;

      // If we're here, then we don't yet know if the report job even exists.
      // Check for the job's existence.
      var rawRow = db.getReportJob(reportID);

      // Report doesn't exist.
      if (rawRow.isEmpty())
        return Optional.empty();

      // Report exists, so lets link the user to it.
      db.linkUserToReport(reportID, userID, null);

      var raw = rawRow.get();

      return Optional.of(new UserReportRow(
        reportID,
        raw.getJobID(),
        raw.getStatus(),
        raw.getConfig(),
        raw.getQueueID(),
        raw.getCreatedOn(),
        userID,
        null
      ));
    }
  }

  /**
   * Returns an optional input stream over the specified file in the target
   * report workspace.
   * <p>
   * If the job workspace, report workspace, or target file do not exist, this
   * method will return an empty option.
   *
   * @param row  Report job record.
   * @param file Target file name.
   *
   * @return an optional input stream over the target file.
   */
  public static Optional<InputStream> getReportFile(ReportRow row, String file)
  throws Exception {
    Log.trace("::getReportFiles(row={})", row);

    if (!JobDataManager.workspaceExists(row.getJobID()))
      return Optional.empty();

    var ws = JobDataManager.jobWorkspace(row.getJobID());

    if (!ws.reportExists(row.getReportID()))
      return Optional.empty();

    var rs = ws.reportWorkspace(row.getReportID());

    if (!rs.fileExists(file))
      return Optional.empty();

    return Optional.of(rs.getFileStream(file));
  }

  public static Optional<UserReportRow> getUpdatedUserReport(HashID reportID, long userID)
  throws Exception {
    Log.trace("::getUserReport(reportID={}, userID={})", reportID, userID);
    try (var db = new ReportDBManager()) {
      var tmpOpt = db.getUserReportJob(reportID, userID);

      if (tmpOpt.isPresent())
        updateJobStatus(tmpOpt.get());

      return tmpOpt;
    }
  }

  public static List<ReportRow> getAllReportsForJob(HashID jobID) throws Exception {
    Log.trace("::getAllReportsForJob(jobID={})", jobID);
    try (var db = new ReportDBManager()) {
      return db.getJobReports(jobID);
    }
  }

  public static List<UserReportRow> getUserReportsForJob(HashID jobID, long userID)
  throws Exception {
    Log.trace("::getUserReportsForJob(jobID={}, userID={})", jobID, userID);
    try (var db = new ReportDBManager()) {
      return db.getUserJobReports(jobID, userID);
    }
  }

  public static List<UserReportRow> getAllReportsForUser(long userID) throws Exception {
    Log.trace("::getAllReportsForUser(userID={})", userID);
    try (var db = new ReportDBManager()) {
      return db.getUserJobReports(userID);
    }
  }

  /**
   * Unlinks a user from a report job.
   *
   * @param reportID ID of the report job the user should be unlinked from.
   * @param userID   ID of the user to unlink.
   */
  public static void unlinkUserReport(HashID reportID, long userID) throws Exception {
  }

  /**
   * Updates the job status field for a report job matching the given report
   * hash.
   * <p>
   * If no such report exists, this method updates nothing and returns an empty
   * option.
   *
   * @param reportID ID of the report job to have it's status updated.
   *
   * @return An option containing the updated row, if such a row exists, or else
   * an empty option if no job with the given hash was found.
   */
  public static Optional<ReportRow> updateJobStatusIfExists(HashID reportID) throws Exception {
    Log.trace("::updateJobStatusIfExists(reportID={})", reportID);
    try (var db = new ReportDBManager()) {
      var optRow = db.getReportJob(reportID);

      if (optRow.isEmpty())
        return Optional.empty();

      refreshJobStatus(db, optRow.get());

      return Optional.of(optRow.get());
    }
  }

  /**
   * Updates the job status field for the given report job.
   *
   * @param row Report job row to update.
   */
  public static void updateJobStatus(ReportRow row) throws Exception {
    Log.trace("::updateJobStatus(row={})", row);
    try (var db = new ReportDBManager()) {
      refreshJobStatus(db, row);
    }
  }

  /**
   * Returns an input stream over a blast report's zipped output.
   *
   * @param jobID    ID of the report job's parent blast job.
   * @param reportID ID of the report job.
   *
   * @return An input stream over a blast report's zipped output.
   */
  public InputStream getReportZip(HashID jobID, HashID reportID) throws Exception {
    Log.trace("::getReportZip(jobID={}, reportID={})", jobID, reportID);
    return JobDataManager.jobWorkspace(jobID).reportWorkspace(reportID).getZipStream();
  }

  private static void refreshJobStatus(ReportDBManager db, ReportRow row) throws Exception {
    Log.trace("::updateJobStatus(db={}, row={})", db, row);
    var status = ReportQueueManager.jobStatus(row.getQueueID());

    if (status == JobStatus.Completed) {
      if (!JobDataManager.workspaceExists(row.getJobID()))
        status = JobStatus.Expired;

      var ws = JobDataManager.jobWorkspace(row.getJobID());
      if (!ws.reportExists(row.getReportID()))
        status = JobStatus.Expired;
    }

    if (status != row.getStatus()) {
      row.setStatus(status);
      db.updateReportRow(row);
    }
  }
}
