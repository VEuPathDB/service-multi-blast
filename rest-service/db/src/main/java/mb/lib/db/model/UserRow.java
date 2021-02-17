package mb.lib.db.model;

public interface UserRow extends Row
{
  long userID();

  String description();

  /**
   * @return an optional 32 byte parent job hash.
   */
  byte[] parentHash();

  long maxDownloadSize();
}
