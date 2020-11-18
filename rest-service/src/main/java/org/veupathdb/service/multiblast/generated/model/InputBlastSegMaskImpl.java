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
  private boolean enabled;

  @JsonProperty("window")
  private int window;

  @JsonProperty("locut")
  private double locut;

  @JsonProperty("hicut")
  private double hicut;

  @JsonProperty("enabled")
  public boolean getEnabled() {
    return this.enabled;
  }

  @JsonProperty("enabled")
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @JsonProperty("window")
  public int getWindow() {
    return this.window;
  }

  @JsonProperty("window")
  public void setWindow(int window) {
    this.window = window;
  }

  @JsonProperty("locut")
  public double getLocut() {
    return this.locut;
  }

  @JsonProperty("locut")
  public void setLocut(double locut) {
    this.locut = locut;
  }

  @JsonProperty("hicut")
  public double getHicut() {
    return this.hicut;
  }

  @JsonProperty("hicut")
  public void setHicut(double hicut) {
    this.hicut = hicut;
  }
}
