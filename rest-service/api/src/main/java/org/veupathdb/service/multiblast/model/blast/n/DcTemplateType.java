package org.veupathdb.service.multiblast.model.blast.n;

import java.util.Optional;

public enum DcTemplateType
{
  Coding("coding"),
  Optimal("optimal"),
  Both("coding_and_optimal"),
  ;

  private final String value;

  DcTemplateType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Optional<DcTemplateType> fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return Optional.of(e);

    return Optional.empty();
  }

  public static DcTemplateType unsafeFromString(String name) {
    return fromString(name).orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public String toString() {
    return value;
  }
}
