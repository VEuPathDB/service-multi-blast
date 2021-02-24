package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = IOBlastReportFormatImpl.class
)
public interface IOBlastReportFormat {
  @JsonProperty("format")
  IOBlastFormat getFormat();

  @JsonProperty("format")
  void setFormat(IOBlastFormat format);

  @JsonProperty("delim")
  String getDelim();

  @JsonProperty("delim")
  void setDelim(String delim);

  @JsonProperty("fields")
  List<IOBlastReportField> getFields();

  @JsonProperty("fields")
  void setFields(List<IOBlastReportField> fields);
}
