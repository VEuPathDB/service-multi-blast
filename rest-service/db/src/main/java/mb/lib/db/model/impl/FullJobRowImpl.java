package mb.lib.db.model.impl;

import java.io.File;
import java.time.OffsetDateTime;

import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.JobStatus;

public class FullJobRowImpl extends ShortJobRowImpl implements FullJobRow
{
  private final String config;
  private final File query;

  public FullJobRowImpl(
    byte[]         hash,
    int            queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String         config,
    File           query,
    String         projectID,
    JobStatus      status
  ) {
    super(hash, queueID, createdOn, deleteOn, projectID, status);
    this.config = config;
    this.query  = query;
  }

  @Override
  public String config() {
    return config;
  }

  @Override
  public File query() {
    return query;
  }

  @Override
  public String toString() {
    return "FullJobRow{hash=" + printID() + "}";
  }
}
