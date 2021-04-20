package mb.lib.blast.model.tn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

public enum TBlastNTask
{
  TBlastN("tblastn"),
  TBlastNFast("tblastn-fast");

  private static final TBlastNTask def = TBlastN;

  private final String value;

  TBlastNTask(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static TBlastNTask getDefault() {
    return def;
  }

  @Override
  @JsonValue
  public String toString() {
    return value;
  }

  @JsonCreator
  public static TBlastNTask fromString(String value) {
    for (var e : values())
      if (e.value.equals(value))
        return e;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      TBlastNTask.class.getSimpleName()
    ));
  }
}
