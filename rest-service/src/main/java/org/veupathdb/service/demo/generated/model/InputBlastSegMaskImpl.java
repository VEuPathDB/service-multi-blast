package org.veupathdb.service.demo.generated.model;

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
  @JsonProperty(
      value = "enabled",
      defaultValue = "false"
  )
  private boolean enabled;

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  private int window;

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  private double locut;

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  private double hicut;

  @JsonProperty(
      value = "enabled",
      defaultValue = "false"
  )
  public boolean getEnabled() {
    return this.enabled;
  }

  @JsonProperty(
      value = "enabled",
      defaultValue = "false"
  )
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  public int getWindow() {
    return this.window;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  public void setWindow(int window) {
    this.window = window;
  }

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  public double getLocut() {
    return this.locut;
  }

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  public void setLocut(double locut) {
    this.locut = locut;
  }

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  public double getHicut() {
    return this.hicut;
  }

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  public void setHicut(double hicut) {
    this.hicut = hicut;
  }
}
