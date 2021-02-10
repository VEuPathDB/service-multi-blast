package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.FullJobRow;

public class FullJobRowImpl extends ShortJobRowImpl implements FullJobRow
{
  private final String config;

  public FullJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String config
  ) {
    super(hash, queueID, createdOn, deleteOn);
    this.config = config;
  }

  @Override
  public String config() {
    return config;
  }

  @Override
  public String toString() {
    return "FullJobRow{hash=" + printID() + "}";
  }
}
