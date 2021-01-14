package mb.lib.db.model.impl;

import mb.lib.db.model.UserRow;

public class UserRowImpl extends RowImpl implements UserRow
{
  private final long userID;
  private final String description;

  public UserRowImpl(byte[] hash, long userID, String description) {
    super(hash);
    this.userID      = userID;
    this.description = description;
  }

  @Override
  public long getUserId() {
    return userID;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
