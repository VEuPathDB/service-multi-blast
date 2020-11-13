package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum TBlastnTask
{
  TBLASTN("tblastn"),
  TBLASTN_FAST("tblastn-fast");

  private static final TBlastnTask def = TBLASTN;

  private final String value;

  TBlastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TBlastnTask getDefault() {
    return def;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Optional<TBlastnTask> fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return Optional.of(e);

    return Optional.empty();
  }
}
