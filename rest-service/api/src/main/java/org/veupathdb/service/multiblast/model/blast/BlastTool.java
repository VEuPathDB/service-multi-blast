package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum BlastTool
{
  BlastN,
  BlastP,
  BlastX,
  PSIBlast,
  RPSBlast,
  RPSTBlastN,
  TBlastN,
  TBlastX;

  public String value() {
    return name().toLowerCase();
  }

  @Override
  public String toString() {
    return value();
  }

  public static Optional<BlastTool> fromString(String value) {
    if (value == null)
      return Optional.empty();

    var up = value.toLowerCase();

    for (var e : values())
      if (e.value().equals(up))
        return Optional.of(e);

    return Optional.empty();
  }
}
