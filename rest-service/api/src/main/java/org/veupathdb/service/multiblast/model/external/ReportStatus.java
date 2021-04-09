package org.veupathdb.service.multiblast.model.external;

import com.fasterxml.jackson.annotation.JsonValue;
import org.veupathdb.service.multiblast.util.ErrorText;

public enum ReportStatus
{
  NeverRun("never-run"),
  Queued("queued"),
  InProgress("in-progress"),
  Completed("completed"),
  Errored("errored"),
  Expired("expired");

  private final String externalValue;

  ReportStatus(String externalValue) {
    this.externalValue = externalValue;
  }

  @JsonValue
  public String getExternalValue() {
    return externalValue;
  }

  public static ReportStatus fromExternalValue(String value) {
    for (var v : values())
      if (v.externalValue.equals(value))
        return v;

    throw new IllegalArgumentException(String.format(
      ErrorText.InvalidEnumValue,
      value,
      ReportStatus.class.getSimpleName()
    ));
  }
}
