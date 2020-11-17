package org.veupathdb.service.multiblast.model.internal;

import java.util.Optional;

public enum JobStatus
{
  Queued("queued"),
  InProgress("in-progress"),
  Errored("errored"),
  Completed("completed");

  private final String value;

  JobStatus(String name) {
    this.value = name;
  }

  public String getValue() {
    return value;
  }

  public static Optional<JobStatus> fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return Optional.of(e);

    return Optional.empty();
  }

  public static JobStatus unsafeFromString(String name) {
    return fromString(name).orElseThrow(IllegalArgumentException::new);
  }
}
