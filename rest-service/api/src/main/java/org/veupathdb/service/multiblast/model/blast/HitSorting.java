package org.veupathdb.service.multiblast.model.blast;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.util.ErrorText;

public enum HitSorting
{
  ByExpectValue(0, "by-eval"),
  ByBitScore(1, "by-bit-score"),
  ByTotalScore(2, "by-total-score"),
  ByPercentIdentity(3, "by-percent-identity"),
  ByQueryCoverage(4, "by-query-coverage");

  private final byte value;
  private final String name;

  HitSorting(int value, String name) {
    this.value = (byte) value;
    this.name  = name;
  }

  public byte getValue() {
    return value;
  }

  @JsonValue
  public String getName() {
    return name;
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

  @JsonCreator
  public static HitSorting fromName(String name) {
    for (var e : values())
      if (e.name.equals(name))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      name,
      HitSorting.class.getSimpleName()
    ));
  }
}
