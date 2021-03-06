package mb.lib.db.model;

@Deprecated
public enum DBJobStatus
{
  Completed("completed"),
  Running("running"),
  Errored("errored"),
  Queued("queued");

  public final String value;

  DBJobStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return name();
  }

  public static DBJobStatus fromString(String value) {
    for (var v : values())
      if (v.value.equals(value))
        return v;

    throw new IllegalArgumentException(String.format("Invalid JobStatus value \"%s\"", value));
  }
}
