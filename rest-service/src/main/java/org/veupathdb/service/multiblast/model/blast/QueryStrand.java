package org.veupathdb.service.multiblast.model.blast;

public enum QueryStrand
{
  BOTH,
  MINUS,
  PLUS;

  public String value() {
    return name().toLowerCase();
  }

  @Override
  public String toString() {
    return value();
  }
}
