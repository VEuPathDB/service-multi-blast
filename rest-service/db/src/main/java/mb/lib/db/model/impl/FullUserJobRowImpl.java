package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long   userId;
  private final String description;
  private final long   maxDlSize;

  public FullUserJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config,
    long userId,
    String description,
    long maxDlSize
  ) {
    super(hash, queueID, createdOn, deleteOn, config);
    this.userId      = userId;
    this.description = description;
    this.maxDlSize   = maxDlSize;
  }

  @Override
  public long userID() {
    return userId;
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public long maxDownloadSize() {
    return maxDlSize;
  }
}
