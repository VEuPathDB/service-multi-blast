package mb.lib.db.model.impl;

import java.io.File;
import java.time.OffsetDateTime;

import mb.lib.db.model.FullUserJobRow;

public class FullUserJobRowImpl extends FullJobRowImpl implements FullUserJobRow
{
  private final long    userID;
  private final String  description;
  private final long    maxDlSize;
  private final boolean runDirectly;

  public FullUserJobRowImpl(
    byte[]         hash,
    int            queueID,
    OffsetDateTime createdOn,
    OffsetDateTime deleteOn,
    String         config,
    File           query,
    long           userID,
    String         description,
    long           maxDlSize,
    boolean        runDirectly
  ) {
    super(hash, queueID, createdOn, deleteOn, config, query);
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
  public long maxDownloadSize() {
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
