package org.veupathdb.service.multiblast.model.blast.p;

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

  public static BlastpScoringMatrix fromString(String value) {
    value = value.toUpperCase();

    for (var e : values())
      if (e.value().equals(value))
        return e;

    throw new IllegalArgumentException(); //TODO: print actual value
  }
}
