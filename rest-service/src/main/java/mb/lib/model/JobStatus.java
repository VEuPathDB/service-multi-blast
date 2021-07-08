package mb.lib.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JobStatus
{
  Queued("queued"),
  InProgress("in-progress"),
  Errored("errored"),
  Completed("completed"),
  Expired("expired");

  private static final String InProgressAlias = "grabbed";
  private static final String QueuedAlias     = "claimed";

  private final String value;

  JobStatus(String name) {
    this.value = name;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static Optional<JobStatus> fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return Optional.of(e);

    return Optional.ofNullable(switch (name) {
      case QueuedAlias -> Queued;
      case InProgressAlias -> InProgress;
      default -> null;
    });
  }

  @JsonCreator
  public static JobStatus unsafeFromString(String name) {
    return fromString(name)
      .orElseThrow(() -> new IllegalArgumentException("Unrecognized JobStatus value: " + name));
  }
}
