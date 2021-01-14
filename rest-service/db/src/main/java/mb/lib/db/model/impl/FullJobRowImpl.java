package mb.lib.db.model.impl;

import java.time.OffsetDateTime;

import mb.lib.db.model.FullJobRow;

public class FullJobRowImpl extends ShortJobRowImpl implements FullJobRow
{
  private final String config;
  private final String query;

  public FullJobRowImpl(
    byte[] hash,
    int queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String query,
    String config
  ) {
    super(hash, queueID, createdOn, deleteOn);
    this.config = config;
    this.query  = query;
  }

  @Override
  public String config() {
    return config;
  }

  @Override
  public String query() {
    return query;
  }
}
