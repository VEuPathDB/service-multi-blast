package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "enable",
    "level",
    "window",
    "linker"
})
public class InputBlastnDustImpl implements InputBlastnDust {
  @JsonProperty(
      value = "enable",
      defaultValue = "true"
  )
  private boolean enable;

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  private short level;

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  private short window;

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  private short linker;

  @JsonProperty(
      value = "enable",
      defaultValue = "true"
  )
  public boolean getEnable() {
    return this.enable;
  }

  @JsonProperty(
      value = "enable",
      defaultValue = "true"
  )
  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public short getLevel() {
    return this.level;
  }

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public void setLevel(short level) {
    this.level = level;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public short getWindow() {
    return this.window;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public void setWindow(short window) {
    this.window = window;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public short getLinker() {
    return this.linker;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public void setLinker(short linker) {
    this.linker = linker;
  }
}
