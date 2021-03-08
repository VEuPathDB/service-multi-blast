package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastSegMaskImpl implements IOBlastSegMask
{
  private Integer window;
  private Double  locut;
  private Double  hicut;

  @JsonProperty("window")
  public Integer getWindow() {
    return this.window;
  }

  @JsonProperty("window")
  public void setWindow(Integer window) {
    this.window = window;
  }

  @JsonProperty("locut")
  public Double getLocut() {
    return this.locut;
  }

  @JsonProperty("locut")
  public void setLocut(Double locut) {
    this.locut = locut;
  }

  @JsonProperty("hicut")
  public Double getHicut() {
    return this.hicut;
  }

  @JsonProperty("hicut")
  public void setHicut(Double hicut) {
    this.hicut = hicut;
  }
}
