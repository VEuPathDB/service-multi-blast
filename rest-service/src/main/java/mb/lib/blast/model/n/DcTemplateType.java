package mb.lib.blast.model.n;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mb.api.service.util.ErrorText;

public enum DcTemplateType
{
  Coding("coding", "coding"),
  Optimal("optimal", "optimal"),
  Both("coding_and_optimal", "both"),
  ;

  private final String value;
  private final String externalValue;

  DcTemplateType(String value, String external) {
    this.value         = value;
    this.externalValue = external;
  }

  public String getValue() {
    return value;
  }

  @JsonValue
  public String getExternalValue() {
    return externalValue;
  }

  public static Optional<DcTemplateType> fromString(String name) {
    for (var e : values())
      if (e.value.equals(name))
        return Optional.of(e);

    return Optional.empty();
  }

  public static DcTemplateType unsafeFromString(String name) {
    return fromString(name).orElseThrow(IllegalArgumentException::new);
  }

  @JsonCreator
  public static DcTemplateType fromExternalValue(String value) {
    for (var v : values())
      if (v.externalValue.equals(value))
        return v;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      DcTemplateType.class.getSimpleName()
    ));
  }

  @Override
  public String toString() {
    return value;
  }
}
