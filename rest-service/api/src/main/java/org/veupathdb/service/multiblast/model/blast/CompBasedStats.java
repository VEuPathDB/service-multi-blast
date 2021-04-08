package org.veupathdb.service.multiblast.model.blast;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.util.ErrorText;

public enum CompBasedStats
{
  None("none"),
  CompBasedStats("comp-based-stats"),
  ConditionalScoreAdjustment("conditional-comp-based-score-adjustment"),
  UnconditionalScoreAdjustment("unconditional-comp-based-score-adjustment"),
  ;

  private final String externalValue;

  CompBasedStats(String externalValue) {
    this.externalValue = externalValue;
  }

  @JsonValue
  public String getExternalValue() {
    return externalValue;
  }

  @Override
  public String toString() {
    return String.valueOf(this.ordinal());
  }

  @JsonCreator
  public static CompBasedStats fromValue(String value) {
    value = value.toLowerCase();

    for (var v : values())
      if (v.externalValue.equals(value))
        return v;

    return switch(value) {
      case "0", "f", "false" -> None;
      case "1" -> CompBasedStats;
      case "d", "2", "t", "true" -> ConditionalScoreAdjustment;
      case "3" -> UnconditionalScoreAdjustment;
      default -> throw new IllegalArgumentException(String.format(
        ErrorText.InvalidEnumValue,
        value,
        org.veupathdb.service.multiblast.model.blast.CompBasedStats.class.getSimpleName()
      ));
    };
  }
}
