package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.JobStatus;
import mb.lib.db.model.ShortJobRow;

public class ShortJobRowImpl extends RowImpl implements ShortJobRow
{
  private final int queueID;
  private final OffsetDateTime createdOn;
  private final OffsetDateTime deleteOn;
  private final String projectID;
  private final JobStatus status;

  public ShortJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String projectID,
    JobStatus status
  ) {
    super(hash);
    this.queueID   = queueID;
    this.createdOn = createdOn;
    this.deleteOn  = deleteOn;
    this.projectID = projectID;
    this.status    = status;
  }

  @Override
  public int queueID() {
    return queueID;
  }

  @Override
  public OffsetDateTime createdOn() {
    return createdOn;
  }

  @Override
  public OffsetDateTime deleteOn() {
    return deleteOn;
  }

  @Override
  public JobStatus status() {
    return status;
  }

  @Override
  public String projectID() {
    return projectID;
  }

  @Override
  public String toString() {
    return "ShortJobRow{hash=" + printID() + "}";
  }
}
