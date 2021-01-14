package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.ShortUserJobRow;

public class ShortUserJobRowImpl extends ShortJobRowImpl implements ShortUserJobRow
{
  private final long   userID;
  private final String description;

  public ShortUserJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    long userID,
    String description
  ) {
    super(hash, queueID, createdOn, deleteOn);
    this.userID      = userID;
    this.description = description;
  }

  @Override
  public long userID() {
    return userID;
  }

  @Override
  public String description() {
    return description;
  }
}
