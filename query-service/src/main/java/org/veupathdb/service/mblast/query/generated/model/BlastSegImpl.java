package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "window",
    "locut",
    "hicut"
})
public class BlastSegImpl implements BlastSeg {
  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  private Integer window;

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  private Double locut;

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  private Double hicut;

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  public Integer getWindow() {
    return this.window;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  public void setWindow(Integer window) {
    this.window = window;
  }

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  public Double getLocut() {
    return this.locut;
  }

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  public void setLocut(Double locut) {
    this.locut = locut;
  }

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  public Double getHicut() {
    return this.hicut;
  }

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  public void setHicut(Double hicut) {
    this.hicut = hicut;
  }
}
