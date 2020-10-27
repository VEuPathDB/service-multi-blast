package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

public enum HitSorting
{
  BY_EXPECT_VALUE(0),
  BY_BIT_SCORE(1),
  BY_TOTAL_SCORE(2),
  BY_PERCENT_IDENTITY(3),
  BY_QUERY_COVERAGE(4);

  private final byte value;

  HitSorting(int value) {
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
}
