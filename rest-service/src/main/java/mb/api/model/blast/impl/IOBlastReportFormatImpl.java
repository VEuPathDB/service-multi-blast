package mb.api.model.blast.impl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.model.blast.IOBlastFormat;
import mb.api.model.blast.IOBlastReportFormat;
import mb.lib.blast.model.BlastReportField;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastReportFormatImpl implements IOBlastReportFormat
{
  private IOBlastFormat format;
  private String        delim;
  private List<BlastReportField> fields;

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
  public List<BlastReportField> getFields() {
    return this.fields;
  }

  @JsonProperty("fields")
  public void setFields(List<BlastReportField> fields) {
    this.fields = fields;
  }
}
