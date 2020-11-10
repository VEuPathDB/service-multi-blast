package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

public enum HspSorting
{
  BY_EXPECT_VALUE(0),
  BY_SCORE(1),
  BY_QUERY_START(2),
  BY_PERCENT_IDENTITY(3),
  BY_SUBJECT_START(4);

  private final byte value;

  HspSorting(int value) {
    this.value = (byte) value;
  }

  public byte getValue() {
    return value;
  }

  public boolean isValidFor(ReportFormatType fmt) {
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
