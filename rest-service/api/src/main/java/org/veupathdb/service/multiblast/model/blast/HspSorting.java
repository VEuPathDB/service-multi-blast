package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

public enum HspSorting
{
  ByExpectValue(0),
  ByScore(1),
  ByQueryStart(2),
  ByPercentIdentity(3),
  BySubjectStart(4);

  private final byte value;

  HspSorting(int value) {
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

  public static HspSorting fromString(String value) {
    return HspSorting.values()[Integer.parseInt(value)];
  }
}
