package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(
    as = IOBlastReportFormatImpl.class
)
public interface IOBlastReportFormat {
  @JsonProperty(JsonKeys.Format)
  IOBlastFormat getFormat();

  @JsonProperty(JsonKeys.Format)
  void setFormat(IOBlastFormat format);

  @JsonProperty(JsonKeys.Delimiter)
  String getDelim();

  @JsonProperty(JsonKeys.Delimiter)
  void setDelim(String delim);

  @JsonProperty(JsonKeys.Fields)
  List<IOBlastReportField> getFields();

  @JsonProperty(JsonKeys.Fields)
  void setFields(List<IOBlastReportField> fields);
}
