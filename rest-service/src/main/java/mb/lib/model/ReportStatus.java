package mb.lib.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportStatus
{
  NeverRun("never-run"),
  Queued("queued"),
  InProgress("in-progress"),
  Completed("completed"),
  Errored("errored"),
  Expired("expired");

  private final String externalValue;

  ReportStatus(String externalValue) {
    this.externalValue = externalValue;
  }

  @JsonValue
  public String getExternalValue() {
    return externalValue;
  }

  @JsonCreator
  public static ReportStatus fromExternalValue(String value) {
    for (var v : values())
      if (v.externalValue.equals(value))
        return v;

    throw new IllegalArgumentException(String.format(
      "Unrecognized enum value \"%s\" for enum %s",
      value,
      ReportStatus.class.getSimpleName()
    ));
  }
}
