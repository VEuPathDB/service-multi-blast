package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastpTask
{
  BlastP("blastp"),
  BlastPFast("blastp-fast"),
  BlastPShort("blastp-short");

  private final String value;

  BlastpTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Optional<BlastpTask> fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return Optional.of(e);

    return Optional.empty();
  }

  public static BlastpTask unsafeFromString(String value) {
    return fromString(value).orElseThrow(IllegalArgumentException::new);
  }
}
