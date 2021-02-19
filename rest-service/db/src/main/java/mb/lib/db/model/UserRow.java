package mb.lib.db.model;

public interface UserRow extends Row
{
  long userID();

  String description();

  long maxDownloadSize();
}
