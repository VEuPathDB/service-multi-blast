package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;
import javax.validation.constraints.NotNull;

public enum QueryStrand
{
  BOTH,
  MINUS,
  PLUS;

  @NotNull
  public String value() {
    return name().toLowerCase();
  }

  @NotNull
  @Override
  public String toString() {
    return value();
  }

  public static Optional<QueryStrand> fromString(String value) {
    if (value == null)
      return Optional.empty();

    value = value.toUpperCase();

    for (var e : values())
      if (e.name().equals(value))
        return Optional.of(e);

    return Optional.empty();
  }
}
