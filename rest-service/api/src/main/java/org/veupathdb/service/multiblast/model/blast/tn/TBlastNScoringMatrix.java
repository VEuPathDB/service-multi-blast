package org.veupathdb.service.multiblast.model.blast.tn;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.util.ErrorText;

public enum TBlastNScoringMatrix
{
  Blosum45,
  Blosum50,
  Blosum62,
  Blosum80,
  Blosum90,
  Pam30,
  Pam70,
  Pam250,
  Identity
  ;

  @JsonValue
  public String value() {
    return name().toUpperCase();
  }

  @Override
  public String toString() {
    return value();
  }

  public static Optional<TBlastNScoringMatrix> fromString(String value) {
    if (value == null || value.isBlank())
      return Optional.empty();

    value = value.toUpperCase();

    for (var e : values())
      if (e.value().equals(value))
        return Optional.of(e);

    return Optional.empty();
  }

  @JsonCreator
  public static TBlastNScoringMatrix unsafeFromString(String value) {
    return fromString(value).orElseThrow(() -> new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      TBlastNScoringMatrix.class.getSimpleName()
    )));
  }
}
