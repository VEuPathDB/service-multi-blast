package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastxTask
{
  BLASTX("blastx"),
  BLASTX_FAST("blastx-fast");

  private static final BlastxTask def = BLASTX;

  private final String value;

  BlastxTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastxTask getDefault() {
    return def;
  }

  public static Optional<BlastxTask> fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return Optional.of(e);

    return Optional.empty();
  }

  @Override
  public String toString() {
    return value;
  }
}
