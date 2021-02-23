package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.ShortUserJobRow;

public class ShortUserJobRowImpl extends ShortJobRowImpl implements ShortUserJobRow
{
  private final long    userID;
  private final String  description;
  private final Long    maxDlSize;
  private final boolean runDirectly;

  public ShortUserJobRowImpl(
    byte[]         hash,
    int            queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long           userID,
    String         description,
    Long           maxDlSize,
    boolean        runDirectly
  ) {
    super(hash, queueID, createdOn, deleteOn);
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
    return "ShortUserJobRow{hash=" + printID() + ", userID=" + userID + '}';
  }
}
