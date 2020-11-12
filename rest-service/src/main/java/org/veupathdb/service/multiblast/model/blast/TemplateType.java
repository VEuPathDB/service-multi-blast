package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum TemplateType
{
  Coding("coding"),
  Optimal("optimal"),
  Both("coding_and_optimal"),
  ;

  private final String value;

  TemplateType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Optional<TemplateType> fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return Optional.of(e);

    return Optional.empty();
  }

  public static TemplateType unsafeFromString(String name) {
    return fromString(name).orElseThrow(IllegalArgumentException::new);
  }
}
