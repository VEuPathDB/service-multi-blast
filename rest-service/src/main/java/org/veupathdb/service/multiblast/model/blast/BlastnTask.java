package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastnTask
{
  BLASTN("blastn"),
  BLASTN_SHORT("blastn-short"),
  DC_MEGABLAST("dc-megablast"),
  MEGABLAST("megablast"),
  RMBLASTN("rmblast");

  private static final BlastnTask def = MEGABLAST;

  private final String value;

  BlastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BlastnTask getDefault() {
    return def;
  }

  public static Optional<BlastnTask> fromString(String value) {
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
