package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "format",
    "delim",
    "fields"
})
public class IOBlastReportFormatImpl implements IOBlastReportFormat {
  @JsonProperty("format")
  private IOBlastFormat format;

  @JsonProperty("delim")
  private String delim;

  @JsonProperty("fields")
  private List<IOBlastReportField> fields;

  @JsonProperty("format")
  public IOBlastFormat getFormat() {
    return this.format;
  }

  @JsonProperty("format")
  public void setFormat(IOBlastFormat format) {
    this.format = format;
  }

  @JsonProperty("delim")
  public String getDelim() {
    return this.delim;
  }

  @JsonProperty("delim")
  public void setDelim(String delim) {
    this.delim = delim;
  }

  @JsonProperty("fields")
  public List<IOBlastReportField> getFields() {
    return this.fields;
  }

  @JsonProperty("fields")
  public void setFields(List<IOBlastReportField> fields) {
    this.fields = fields;
  }
}
