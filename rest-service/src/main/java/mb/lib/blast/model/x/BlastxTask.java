package mb.lib.blast.model.x;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

public enum BlastxTask
{
  BlastX("blastx"),
  BlastXFast("blastx-fast");

  private static final BlastxTask def = BlastX;

  private final String value;

  BlastxTask(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static BlastxTask getDefault() {
    return def;
  }

  @JsonCreator
  public static BlastxTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      BlastxTask.class.getSimpleName()
    ));
  }

  @Override
  public String toString() {
    return value;
  }
}
