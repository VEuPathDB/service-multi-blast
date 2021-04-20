package mb.lib.db.model.impl;

import mb.api.model.reports.ReportRequest;
import mb.lib.db.model.FormatJobStatus;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

public class FormatJobStatusImpl extends RowImpl implements FormatJobStatus
{
  private final HashID        reportID;
  private final ReportRequest config;
  private final int           queueID;
  private final JobStatus     status;

  public FormatJobStatusImpl(
    HashID jobID,
    HashID reportID,
    ReportRequest config,
    int queueID,
    JobStatus status
  ) {
    super(jobID);
    this.reportID = reportID;
    this.config   = config;
    this.queueID  = queueID;
    this.status   = status;
  }

  @Override
  public HashID reportID() {
    return reportID;
  }

  @Override
  public ReportRequest config() {
    return config;
  }

  @Override
  public int queueID() {
    return queueID;
  }

  @Override
  public JobStatus status() {
    return status;
  }

  @Override
  public String toString() {
    return "FormatJobStatusImpl{" +
      "jobID=" + jobID() + ", " +
      "reportID=" + reportID +
    '}';
  }
}
