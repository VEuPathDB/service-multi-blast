package mb.lib.blast.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;
import org.veupathdb.lib.blast.field.HitSorting;

public enum IOHitSorting
{
  ByExpectValue("by-eval"),
  ByBitScore("by-bit-score"),
  ByTotalScore("by-total-score"),
  ByPercentIdentity("by-percent-identity"),
  ByQueryCoverage("by-query-coverage");

  private final String value;

  IOHitSorting(String name) {
    this.value = name;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public HitSorting toInternalValue() {
    return HitSorting.values()[ordinal()];
  }

  @JsonCreator
  public static IOHitSorting fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      name,
      IOHitSorting.class.getSimpleName()
    ));
  }

  public static IOHitSorting fromInternalValue(HitSorting value) {
    if (value == null)
      return null;

    return IOHitSorting.values()[value.ordinal()];
  }
}
