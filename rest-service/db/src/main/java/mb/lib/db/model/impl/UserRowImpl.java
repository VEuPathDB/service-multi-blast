package mb.lib.db.model.impl;

import mb.lib.db.model.UserRow;

public class UserRowImpl extends RowImpl implements UserRow
{
  private final long    userID;
  private final String  description;
  private final long    maxDownloadSize;

  public UserRowImpl(
    byte[] hash,
    long userID,
    String description,
    long maxDlSize
  ) {
    super(hash);
    this.userID          = userID;
    this.description     = description;
    this.maxDownloadSize = maxDlSize;
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
    return maxDownloadSize;
  }

  @Override
  public String toString() {
    return "UserRow{hash=" + printID() + ", userID=" + userID + '}';
  }
}
