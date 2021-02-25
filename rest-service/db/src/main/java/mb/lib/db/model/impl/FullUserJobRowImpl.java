package mb.lib.db.model.impl;

import java.io.File;
import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;
import mb.lib.db.model.DBJobStatus;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long    userID;
  private final String  description;
  private final Long    maxDlSize;
  private final boolean runDirectly;

  public FullUserJobRowImpl(
    byte[]         hash,
    int            queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String         config,
    File           query,
    String         projectID,
    DBJobStatus status,
    long           userID,
    String         description,
    Long           maxDlSize,
    boolean        runDirectly
  ) {
    super(hash, queueID, createdOn, deleteOn, config, query, projectID, status);
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
    return "FullUserJobRow{hash="+ printID() +", userID=" + userID + '}';
  }
}
