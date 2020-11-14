package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastnTask
{
  BlastN("blastn"),
  BlastNShort("blastn-short"),
  DiscontiguousMegablast("dc-megablast"),
  Megablast("megablast"),
  RMBlastN("rmblastn");

  private final String value;

  BlastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Optional<BlastnTask> fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return Optional.of(e);

    return Optional.empty();
  }

  public static BlastnTask unsafeFromString(String value) {
    return fromString(value).orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public String toString() {
    return value;
  }
}
