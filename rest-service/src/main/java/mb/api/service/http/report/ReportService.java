package mb.api.service.http.report;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.StreamingOutput;

import mb.api.model.reports.ReportRequest;
import mb.api.model.reports.ReportResponse;
import mb.api.service.http.Util;
import mb.api.service.http.job.JobUtil;
import mb.lib.data.JobDataManager;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.FormatJobStatus;
import mb.lib.db.model.impl.FormatJobStatusImpl;
import mb.lib.formatter.FormatQueueManager;
import mb.lib.formatter.model.Payload;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportService
{
  private static final Logger Log = LogManager.getLogger(ReportService.class);

  private final HashID jobID;

  public ReportService(String jobID) {
    Log.trace("::new(jobID={})", jobID);

    try {
      this.jobID = new HashID(jobID);
    } catch (Exception e) {
      Log.debug("Invalid job ID value.");
      throw new NotFoundException();
    }
  }

  /**
   * Retrieves a list of all the report jobs created for the given {@code jobID}
   * value.
   * <p>
   * On report job status retrieval, the status will be automatically updated if
   * needed.
   *
   * @return A list of all the report jobs created for the given {@code jobID}.
   */
  public static List<ReportResponse> listReports(HashID jobID) {
    Log.trace("::listReports(jobID={})", jobID);


    try (var man = new JobDBManager()) {
      var reports = man.selectFormatJobStatuses(jobID);
      var out     = new ArrayList<ReportResponse>(reports.size());

      for (int i = 0; i < reports.size(); i++) {
        var row = updateReportStatus(reports.get(i), man);

        out.add(new ReportResponse(
          row.jobID(),
          row.reportID(),
          row.config(),
          row.status()
        ));
      }

      return out;
    } catch (Exception e) {
      Log.debug("Error while listing reports: ", e);
      throw Util.wrapException(e);
    }
  }

  /**
   * Looks up metadata about the target report.
   *
   * @param jobID    Parent Blast job ID for the report.
   * @param reportID ID of the report.
   *
   * @return Report job metadata response.
   */
  public static ReportResponse getReport(HashID jobID, HashID reportID) {
    Log.trace("::getReport(jobID={}, reportID={})", jobID, reportID);
    try (var man = new JobDBManager()) {
       return convert(updateReportStatus(
         man.selectFormatJobStatus(jobID, reportID).orElseThrow(NotFoundException::new),
         man
       ));
    } catch (Exception e) {
      Log.debug("Error while looking up report: ", e);
      throw Util.wrapException(e);
    }
  }

  public static ReportResponse rerunReport(HashID jobID, HashID reportID) {
    Log.trace("::rerunReport(jobID={}, reportID={})", jobID, reportID);
    try (var man = new JobDBManager()) {
      // Fetch the matching report (or 404) and update it's status
      var val = updateReportStatus(
        man.selectFormatJobStatus(jobID, reportID).orElseThrow(NotFoundException::new),
        man
      );

      // At this point the status has already been checked, so barring the
      // unhandled potential race condition of the job being deleted between the
      // status update and here, we can say that there is nothing left to do.
      if (val.status() != JobStatus.Expired)
        return convert(val);

      // If the job workspace itself doesn't exist, reject the request.  This
      // endpoint does not resubmit query jobs.
      if (!JobDataManager.workspaceExists(jobID))
        throw new ForbiddenException("Cannot re-run a report on an expired job.");

      // At this point we "know" the blast job itself is not expired, and the
      // report is.  Resubmit the job, update the row, and return.
      var queueID = FormatQueueManager.submitJob(new Payload(jobID, val.config()));
      val = new FormatJobStatusImpl(
        jobID,
        reportID,
        val.config(),
        queueID,
        JobStatus.Queued
      );
      man.updateFormatJobStatusAndQueueID(val);

      return convert(val);
    } catch (Exception e) {
      Log.debug("Error while rerunning report: ", e);
      throw Util.wrapException(e);
    }
  }

  public static ReportResponse runReport(HashID jobID, ReportRequest req) {
    Log.trace("::runReport(jobID={}, req={})", jobID, req);
    try (var db = new JobDBManager()) {
      var queryJob = JobUtil.updateJobStatus(
        db.getQueryJob(jobID).orElseThrow(NotFoundException::new),
        db
      );

      if (queryJob.status() != JobStatus.Completed)
        throw new ForbiddenException("Cannot re-run a report on an incomplete or failed job.");

      var queueID = FormatQueueManager.submitJob(new Payload(jobID, req));
      var row     = new FormatJobStatusImpl(
        jobID,
        new HashID(req.hashConfig()),
        req,
        queueID,
        JobStatus.Queued
      );

      db.insertFormatJob(row);
      return convert(row);
    } catch (Exception e) {
      Log.debug("Error while running report: ", e);
      throw Util.wrapException(e);
    }
  }

  public static ReportDownload downloadReport(
    HashID jobID,
    HashID reportID,
    boolean download,
    boolean zip
  ) {
    try (var db = new JobDBManager()) {
      var rep = updateReportStatus(db.selectFormatJobStatus(jobID, reportID).orElseThrow(NotFoundException::new), db);

      if (rep.status() == JobStatus.Expired)
        throw new BadRequestException("Cannot download an expired report. ");
      if (rep.status() == JobStatus.Errored)
        throw new BadRequestException("Cannot download report from failed job.");
      if (rep.status() != JobStatus.Completed)
        throw new BadRequestException("Cannot download a report before it is complete.");

      var ws = JobDataManager.jobWorkspace(jobID).reportWorkspace(reportID);

      var meta = ws.getReportMeta();

      // Reject requests for multi-file reports that are unzipped.  We can't
      // return more than one file in the response so it must be zipped.
      if (meta.files().size() > 1 && !zip)
        throw new BadRequestException("Multi-file reports must be zipped.");

      // If the requester wants a zip output, enable download no matter what.
      if (zip) {
        download = true;

        if (!ws.zipFileExists())
          throw new InternalServerErrorException("Invalid state: report is completed but output is missing.")


      }





    } catch (Exception e) {
      Log.debug("Error while setting up report download: ", e);
      throw Util.wrapException(e);
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Converts an internal report job representation to the external serializable
   * format.
   *
   * @param stat Report job representation to convert.
   *
   * @return External serializable report job value.
   */
  private static ReportResponse convert(FormatJobStatus stat) {
    Log.trace("::convert(stat={})", stat);
    return new ReportResponse(stat.jobID(), stat.reportID(), stat.config(), stat.status());
  }

  /**
   * Checks and updates the status of a report job, returning an updated
   * {@link FormatJobStatus} instance.
   *
   * @param stat Job record to check/update.
   * @param db   DB Manager instance
   *
   * @return An updated job record containing the new status (if the status
   * changed).
   */
  private static FormatJobStatus updateReportStatus(FormatJobStatus stat, JobDBManager db)
  throws Exception {
    return switch (stat.status()) {
      // If the job is already known to be expired or errored no need to look
      // further.
      //
      // We don't check to see if it's been requeued here.  That is is handled
      // by the endpoint(s) that do the requeuing.
      case Expired, Errored -> stat;

      // If the job is marked as completed, check to see if it has expired.
      case Completed -> {
        // If the job itself has expired, the report is also expired.
        if (!JobDataManager.workspaceExists(stat.jobID())) {
          db.updateFormatJobStatus(stat.jobID(), stat.reportID(), JobStatus.Expired);
          yield new FormatJobStatusImpl(
            stat.jobID(),
            stat.reportID(),
            stat.config(),
            stat.queueID(),
            JobStatus.Expired
          );
        }

        // The job may have been re-run, check that the report exists.
        if (!JobDataManager.jobWorkspace(stat.jobID()).reportExists(stat.reportID()))
          yield new FormatJobStatusImpl(
            stat.jobID(),
            stat.reportID(),
            stat.config(),
            stat.queueID(),
            JobStatus.Expired
          );

        yield stat;
      }

      // If the job is marked as queued or in progress, check it's status.
      case Queued, InProgress -> switch (FormatQueueManager.jobStatus(stat.queueID())) {
        // From queued/in-progress to queued.
        case Queued -> {
          // It's status hasn't changed.
          if (stat.status() == JobStatus.Queued)
            yield stat;

          // It went from last known "in-progress" to "queued"
          db.updateFormatJobStatus(stat.jobID(), stat.reportID(), JobStatus.Queued);
          yield new FormatJobStatusImpl(
            stat.jobID(),
            stat.reportID(),
            stat.config(),
            stat.queueID(),
            JobStatus.Queued
          );
        }

        // From queued/in-progress to in-progress.
        case InProgress -> {
          // It's status hasn't changed.
          if (stat.status() == JobStatus.InProgress)
            yield stat;

          // It went from last known "queued" to "in-progress"
          db.updateFormatJobStatus(stat.jobID(), stat.reportID(), JobStatus.InProgress);
          yield new FormatJobStatusImpl(
            stat.jobID(),
            stat.reportID(),
            stat.config(),
            stat.queueID(),
            JobStatus.InProgress
          );
        }

        // From queued/in-progress to completed.
        case Completed -> updateReportStatus(new FormatJobStatusImpl(
          stat.jobID(),
          stat.reportID(),
          stat.config(),
          stat.queueID(),
          JobStatus.Completed
        ), db);

        // From queued/in-progress to errored.
        case Errored -> {
          db.updateFormatJobStatus(stat.jobID(), stat.reportID(), JobStatus.Errored);
          yield new FormatJobStatusImpl(
            stat.jobID(),
            stat.reportID(),
            stat.config(),
            stat.queueID(),
            JobStatus.Errored
          );
        }
      };
    };
  }
}
