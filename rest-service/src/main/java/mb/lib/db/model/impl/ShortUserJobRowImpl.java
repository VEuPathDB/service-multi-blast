package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.DBJobStatus;
import mb.lib.db.model.ShortUserJobRow;
import mb.lib.model.HashID;

public class ShortUserJobRowImpl extends ShortJobRowImpl implements ShortUserJobRow
{
  private final long    userID;
  private final String  description;
  private final Long    maxDlSize;
  private final boolean runDirectly;

  public ShortUserJobRowImpl(
    HashID hash,
    int            queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String         projectID,
    DBJobStatus status,
    long           userID,
    String         description,
    Long           maxDlSize,
    boolean        runDirectly
  ) {
    super(hash, queueID, createdOn, deleteOn, projectID, status);
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
    return "ShortUserJobRow{hash=" + jobID() + ", userID=" + userID + '}';
  }
}
