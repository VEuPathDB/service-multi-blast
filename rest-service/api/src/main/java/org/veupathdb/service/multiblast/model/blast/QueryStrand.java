package org.veupathdb.service.multiblast.model.blast;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.util.ErrorText;

public enum QueryStrand
{
  Both,
  Minus,
  Plus;

  @JsonValue
  public String value() {
    return name().toLowerCase();
  }

  @Override
  public String toString() {
    return value();
  }

  @JsonCreator
  public static QueryStrand fromString(String value) {
    return switch (value.toLowerCase()) {
      case "both" -> Both;
      case "minus" -> Minus;
      case "plus" -> Plus;
      default -> throw new IllegalStateException(String.format(
        ErrorText.InvalidEnumValue,
        value,
        QueryStrand.class.getSimpleName()
      ));
    };
  }
}
