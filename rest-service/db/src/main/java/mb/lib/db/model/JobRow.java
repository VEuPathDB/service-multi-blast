package mb.lib.db.model;

public final class JobRow extends Row
{
  private final String config;

  public JobRow(byte[] hash, String config) {
    super(hash);
    this.config = config;
  }

  public String getConfig() {
    return config;
  }
}
