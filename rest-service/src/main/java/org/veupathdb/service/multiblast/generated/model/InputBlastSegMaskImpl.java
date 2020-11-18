package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "enabled",
    "window",
    "locut",
    "hicut"
})
public class InputBlastSegMaskImpl implements InputBlastSegMask {
  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("window")
  private Integer window;

  @JsonProperty("locut")
  private Double locut;

  @JsonProperty("hicut")
  private Double hicut;

  @JsonProperty("enabled")
  public Boolean getEnabled() {
    return this.enabled;
  }

  @JsonProperty("enabled")
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @JsonProperty("window")
  public Integer getWindow() {
    return this.window;
  }

  @JsonProperty("window")
  public void setWindow(int window) {
    this.window = window;
  }

  @JsonProperty("locut")
  public Double getLocut() {
    return this.locut;
  }

  @JsonProperty("locut")
  public void setLocut(double locut) {
    this.locut = locut;
  }

  @JsonProperty("hicut")
  public Double getHicut() {
    return this.hicut;
  }

  @JsonProperty("hicut")
  public void setHicut(double hicut) {
    this.hicut = hicut;
  }
}
