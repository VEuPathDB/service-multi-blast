package mb.lib.db.model;

public enum JobStatus
{
  Completed("completed"),
  Running("running"),
  Errored("errored"),
  Queued("queued");

  public final String value;

  JobStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return name();
  }

  public static JobStatus fromString(String value) {
    for (var v : values())
      if (v.value.equals(value))
        return v;

    throw new IllegalArgumentException(String.format("Invalid JobStatus value \"%s\"", value));
  }
}
