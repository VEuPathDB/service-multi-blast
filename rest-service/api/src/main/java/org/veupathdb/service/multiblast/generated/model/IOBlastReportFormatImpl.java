package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastReportFormatImpl implements IOBlastReportFormat
{
  private IOBlastFormat            format;
  private String                   delim;
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
