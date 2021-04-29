package mb.lib.blast.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.lib.blast.field.HSPSorting;

public enum IOHSPSorting
{
  ByExpectValue("by-hsp-evalue"),
  ByScore("by-hsp-score"),
  ByQueryStart("by-hsp-query-start"),
  ByPercentIdentity("by-hsp-percent-identity"),
  BySubjectStart("by-hsp-subject-start");

  private final String externalValue;

  IOHSPSorting(String externalValue) {
    this.externalValue = externalValue;
  }

  @JsonValue
  public String getExternalValue() {
    return externalValue;
  }

  @Override
  public String toString() {
    return externalValue;
  }

  public HSPSorting toInternalValue() {
    return HSPSorting.values()[ordinal()];
  }

  @JsonCreator
  public static IOHSPSorting fromExternalValue(String value) {
    for (var val : values())
      if (val.externalValue.equals(value))
        return val;

    throw new IllegalArgumentException();
  }

  public static IOHSPSorting fromInternalValue(HSPSorting val) {
    return val == null ? null : values()[val.ordinal()];
  }
}
