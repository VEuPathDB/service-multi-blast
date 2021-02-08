package mb.lib.db.model.impl;

import mb.lib.db.model.UserRow;

public class UserRowImpl extends RowImpl implements UserRow
{
  private final long   userID;
  private final String description;
  private final long   maxDownloadSize;

  public UserRowImpl(byte[] hash, long userID, String description, long maxDlSize) {
    super(hash);
    this.userID          = userID;
    this.description     = description;
    this.maxDownloadSize = maxDlSize;
  }

  @Override
  public long getUserId() {
    return userID;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public long getMaxDownloadSize() {
    return maxDownloadSize;
  }
}
