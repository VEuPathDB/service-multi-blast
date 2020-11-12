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
public class InputBlastOutFmtImpl implements InputBlastOutFmt {
  @JsonProperty("format")
  private InputBlastFormat format;

  @JsonProperty("delim")
  private String delim;

  @JsonProperty("fields")
  private List<InputBlastFmtField> fields;

  @JsonProperty("format")
  public InputBlastFormat getFormat() {
    return this.format;
  }

  @JsonProperty("format")
  public void setFormat(InputBlastFormat format) {
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
  public List<InputBlastFmtField> getFields() {
    return this.fields;
  }

  @JsonProperty("fields")
  public void setFields(List<InputBlastFmtField> fields) {
    this.fields = fields;
  }
}
