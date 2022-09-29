package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "level",
    "window",
    "linker"
})
public class BlastNDustImpl implements BlastNDust {
  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  private int level;

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  private int window;

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  private int linker;

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public int getLevel() {
    return this.level;
  }

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public void setLevel(int level) {
    this.level = level;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public int getWindow() {
    return this.window;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public void setWindow(int window) {
    this.window = window;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public int getLinker() {
    return this.linker;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public void setLinker(int linker) {
    this.linker = linker;
  }
}
