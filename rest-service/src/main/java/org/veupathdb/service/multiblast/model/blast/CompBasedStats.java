package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum CompBasedStats
{
  None,
  CompBasedStats,
  ConditionalScoreAdjustment,
  UnconditionalScoreAdjustment,
  ;

  public static Optional<CompBasedStats> fromValue(String value) {
    if (value == null || value.isBlank() || value.length() > 1)
      return Optional.empty();

    var ch = value.charAt(0);

    return switch(ch) {
      case '0', 'f', 'F' -> Optional.of(None);
      case '1' -> Optional.of(CompBasedStats);
      case 'D', 'd', '2', 't', 'T' -> Optional.of(ConditionalScoreAdjustment);
      case '3' -> Optional.of(UnconditionalScoreAdjustment);
      default -> Optional.empty();
    };
  }

  public static CompBasedStats unsafeFromValue(String value) {
    return fromValue(value).orElseThrow(IllegalArgumentException::new);
  }
}
