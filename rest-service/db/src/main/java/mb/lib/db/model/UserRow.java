package mb.lib.db.model;

public final class UserRow extends Row
{
  private final long userId;
  private final String description;

  public UserRow(byte[] hash, long userId, String description) {
    super(hash);
    this.userId      = userId;
    this.description = description;
  }

  public long getUserId() {
    return userId;
  }

  public String getDescription() {
    return description;
  }
}
