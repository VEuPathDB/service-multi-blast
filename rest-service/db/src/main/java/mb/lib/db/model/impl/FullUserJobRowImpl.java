package mb.lib.db.model.impl;

import java.io.File;
import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long   userID;
  private final String description;
  private final long   maxDlSize;

  public FullUserJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    File query,
    long userID,
    String description,
    long maxDlSize
  ) {
    super(hash, queueID, createdOn, deleteOn, config, query);
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
    return "FullUserJobRow{hash="+ printID() +", userID=" + userID + '}';
  }
}
