package mb.api.model.blast.impl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.model.blast.IOBlastFormat;
import mb.api.model.blast.IOBlastReportFormat;
import org.veupathdb.lib.blast.field.FormatField;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastReportFormatImpl implements IOBlastReportFormat
{
  private IOBlastFormat     format;
  private String            delim;
  private List<FormatField> fields;

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
  public List<FormatField> getFields() {
    return this.fields;
  }

  @JsonProperty("fields")
  public void setFields(List<FormatField> fields) {
    this.fields = fields;
  }
}
