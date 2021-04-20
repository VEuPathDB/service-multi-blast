package mb.lib.blast.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

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

  @JsonValue
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

  @JsonCreator
  public static BlastTool unsafeFromString(String value) {
    return fromString(value).orElseThrow(() -> new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      BlastTool.class.getSimpleName()
    )));
  }
}
