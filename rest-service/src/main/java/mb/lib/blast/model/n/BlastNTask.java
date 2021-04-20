package mb.lib.blast.model.n;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

public enum BlastNTask
{
  BlastN("blastn"),
  BlastNShort("blastn-short"),
  DiscontiguousMegablast("dc-megablast"),
  Megablast("megablast"),
  RMBlastN("rmblastn");

  private final String value;

  BlastNTask(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static BlastNTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      BlastNTask.class.getSimpleName()
    ));
  }

  @Override
  public String toString() {
    return value;
  }
}
