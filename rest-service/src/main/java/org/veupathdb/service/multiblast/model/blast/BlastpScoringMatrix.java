package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastpScoringMatrix
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

  public String value() {
    return name().toUpperCase();
  }

  @Override
  public String toString() {
    return value();
  }

  public static Optional<BlastpScoringMatrix> fromString(String value) {
    if (value == null || value.isBlank())
      return Optional.empty();

    value = value.toUpperCase();

    for (var e : values())
      if (e.value().equals(value))
        return Optional.of(e);

    return Optional.empty();
  }

  public static BlastpScoringMatrix unsafeFromString(String value) {
    return fromString(value).orElseThrow(IllegalArgumentException::new);
  }
}