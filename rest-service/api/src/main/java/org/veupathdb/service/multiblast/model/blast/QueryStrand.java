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
    return switch (value.toLowerCase()) {
      case "both" -> Both;
      case "minus" -> Minus;
      case "plus" -> Plus;
      default -> throw new IllegalStateException("Unexpected QueryStrand value: " + value.toLowerCase());
    };
  }
}
