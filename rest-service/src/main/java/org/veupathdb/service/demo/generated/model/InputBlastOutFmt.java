package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(
    as = InputBlastOutFmtImpl.class
)
public interface InputBlastOutFmt {
  @JsonProperty("format")
  InputBlastFormat getFormat();

  @JsonProperty("format")
  void setFormat(InputBlastFormat format);

  @JsonProperty("fields")
  List<InputBlastFmtField> getFields();

  @JsonProperty("fields")
  void setFields(List<InputBlastFmtField> fields);
}