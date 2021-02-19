package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.ShortUserJobRow;

public class ShortUserJobRowImpl extends ShortJobRowImpl implements ShortUserJobRow
{
  private final long    userID;
  private final String  description;
  private final long    maxDlSize;

  public ShortUserJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userID,
    String description,
    long maxDlSize
  ) {
    super(hash, queueID, createdOn, deleteOn);
    this.userID      = userID;
    this.description = description;
    this.maxDlSize   = maxDlSize;
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
  public long maxDownloadSize() {
    return maxDlSize;
  }

  @Override
  public String toString() {
    return "ShortUserJobRow{hash=" + printID() + ", userID=" + userID + '}';
  }
}
