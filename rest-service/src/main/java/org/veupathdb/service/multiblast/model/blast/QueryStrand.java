package org.veupathdb.service.multiblast.model.blast;

public enum QueryStrand
{
  Both,
  Minus,
  Plus;

  public String value() {
    return name().toLowerCase();
  }

  @Override
  public String toString() {
    return value();
  }

  public static QueryStrand fromString(String value) {
    value = value.toUpperCase();

    for (var e : values())
      if (e.name().equals(value))
        return e;

    throw new IllegalArgumentException(); //TODO: print actual value
  }
}
