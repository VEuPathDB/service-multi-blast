package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long   userID;
  private final String description;
  private final long   maxDlSize;
  private final byte[] parentJobID;

  public FullUserJobRowImpl(
    byte[] hash,
    int queueID,
    byte[] parentJobID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    String query,
    long userID,
    String description,
    long maxDlSize
  ) {
    super(hash, queueID, createdOn, deleteOn, config, query);
    this.userID      = userID;
    this.description = description;
    this.parentJobID = parentJobID;
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
  public byte[] parentHash() {
    return parentJobID;
  }

  @Override
  public String toString() {
    return "FullUserJobRow{hash="+ printID() +", userID=" + userID + '}';
  }
}
