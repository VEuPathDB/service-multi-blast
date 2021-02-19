package mb.lib.db.model;

public interface UserRow extends Row
{
  long userID();

  String description();

  long maxDownloadSize();

  /**
   * @return whether the job linked to this row was run directly by the client
   * (not a sub-job).
   */
  boolean runDirectly();
}
