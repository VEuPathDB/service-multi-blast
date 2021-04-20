package mb.lib.db.model.impl;

import mb.lib.db.model.UserRow;
import mb.lib.model.HashID;

public class UserRowImpl extends RowImpl implements UserRow
{
  private final long    userID;
  private final String  description;
  private final Long    maxDownloadSize;
  private final boolean runDirectly;

  public UserRowImpl(
    HashID hash,
    long    userID,
    String  description,
    Long    maxDlSize,
    boolean runDirectly
  ) {
    super(hash);
    this.userID          = userID;
    this.description     = description;
    this.maxDownloadSize = maxDlSize;
    this.runDirectly     = runDirectly;
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
    return maxDownloadSize;
  }

  @Override
  public boolean runDirectly() {
    return runDirectly;
  }

  @Override
  public String toString() {
    return "UserRow{hash=" + jobID() + ", userID=" + userID + '}';
  }
}
