package mb.lib.report.model;

import java.time.OffsetDateTime;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.blast.BlastFormatter;

public class ReportRow
{
  private final HashID         reportID;
  private final HashID         jobID;
  private final BlastFormatter config;
  private final OffsetDateTime createdOn;

  private int       queueID;
  private JobStatus status;

  public ReportRow(
    HashID reportID,
    HashID jobID,
    JobStatus status,
    BlastFormatter config,
    int queueID,
    OffsetDateTime createdOn
  ) {
    this.reportID  = reportID;
    this.jobID     = jobID;
    this.status    = status;
    this.config    = config;
    this.queueID   = queueID;
    this.createdOn = createdOn;
  }

  public HashID getReportID() {
    return reportID;
  }

  public HashID getJobID() {
    return jobID;
  }

  public JobStatus getStatus() {
    return status;
  }

  public ReportRow setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public BlastFormatter getConfig() {
    return config;
  }

  public int getQueueID() {
    return queueID;
  }

  public ReportRow setQueueID(int queueID) {
    this.queueID = queueID;
    return this;
  }

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  @Override
  public String toString() {
    return "ReportRow{jobID=" + jobID + ", reportID=" + reportID + "}";
  }
}
