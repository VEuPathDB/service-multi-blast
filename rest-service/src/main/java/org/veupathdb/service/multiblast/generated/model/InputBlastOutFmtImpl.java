package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "format",
    "fields"
})
public class InputBlastOutFmtImpl implements InputBlastOutFmt {
  @JsonProperty("format")
  private InputBlastFormat format;

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

  @JsonProperty("fields")
  public List<InputBlastFmtField> getFields() {
    return this.fields;
  }

  @JsonProperty("fields")
  public void setFields(List<InputBlastFmtField> fields) {
    this.fields = fields;
  }
}
