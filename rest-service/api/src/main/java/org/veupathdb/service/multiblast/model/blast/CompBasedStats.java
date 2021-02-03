package org.veupathdb.service.multiblast.model.blast;

public enum CompBasedStats
{
  None,
  CompBasedStats,
  ConditionalScoreAdjustment,
  UnconditionalScoreAdjustment,
  ;

  @Override
  public String toString() {
    return String.valueOf(this.ordinal());
  }

  public static CompBasedStats fromValue(String value) {
    var ch = value.charAt(0);

    return switch(ch) {
      case '0', 'f', 'F' -> None;
      case '1' -> CompBasedStats;
      case 'D', 'd', '2', 't', 'T' -> ConditionalScoreAdjustment;
      case '3' -> UnconditionalScoreAdjustment;
      default -> throw new IllegalArgumentException(); // TODO: print actual value
    };
  }
}
