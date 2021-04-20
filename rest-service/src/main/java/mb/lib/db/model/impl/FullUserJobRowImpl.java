package mb.lib.db.model.impl;

import java.io.File;
import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long    userID;
  private final String  description;
  private final Long    maxDlSize;
  private final boolean runDirectly;

  public FullUserJobRowImpl(
    HashID jobID,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    File query,
    String projectID,
    JobStatus status,
    long userID,
    String description,
    Long maxDlSize,
    boolean runDirectly
  ) {
    super(jobID, queueID, createdOn, deleteOn, config, query, projectID, status);
    this.userID      = userID;
    this.description = description;
    this.maxDlSize   = maxDlSize;
    this.runDirectly = runDirectly;
  }

  @Override
  public long userID() {
    return userID;
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public Long maxDownloadSize() {
    return maxDlSize;
  }

  @Override
  public boolean runDirectly() {
    return runDirectly;
  }

  @Override
  public String toString() {
    return "FullUserJobRow{hash=" + jobID() + ", userID=" + userID + '}';
  }
}
