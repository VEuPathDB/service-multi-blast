package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum TblastnTask
{
  TBLASTN("tblastn"),
  TBLASTN_FAST("tblastn-fast");

  private static final TblastnTask def = TBLASTN;

  private final String value;

  TblastnTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TblastnTask getDefault() {
    return def;
  }

  public static Optional<TblastnTask> fromString(String value) {
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
