package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

public enum HitSorting
{
  ByExpectValue(0),
  ByBitScore(1),
  ByTotalScore(2),
  ByPercentIdentity(3),
  ByQueryCoverage(4);

  private final byte value;

  HitSorting(int value) {
    this.value = (byte) value;
  }

  public byte getValue() {
    return value;
  }

  public boolean isValidFor(BlastReportType fmt) {
    Objects.requireNonNull(fmt);

    return fmt.getValue() <= 4;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static HitSorting fromString(String value) {
    return HitSorting.values()[Integer.parseInt(value)];
  }
}
