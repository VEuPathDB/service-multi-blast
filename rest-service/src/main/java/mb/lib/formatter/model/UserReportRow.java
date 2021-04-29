package mb.lib.formatter.model;

import java.time.OffsetDateTime;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.blast.BlastFormatter;

public class UserReportRow extends ReportRow
{
  private final long   userID;
  private final String description;

  public UserReportRow(
    HashID reportID,
    HashID jobID,
    JobStatus status,
    BlastFormatter config,
    int queueID,
    OffsetDateTime createdOn,
    long userID,
    String description
  ) {
    super(reportID, jobID, status, config, queueID, createdOn);
    this.userID      = userID;
    this.description = description;
  }

  public UserReportRow(ReportRow row, long userID, String description) {
    this(
      row.getReportID(),
      row.getJobID(),
      row.getStatus(),
      row.getConfig(),
      row.getQueueID(),
      row.getCreatedOn(),
      userID,
      description
    );
  }

  public long getUserID() {
    return userID;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "UserReportRow{jobID="
      + getJobID()
      + ", reportID="
      + getReportID()
      + ", userID="
      + userID
      + "}";
  }
}
