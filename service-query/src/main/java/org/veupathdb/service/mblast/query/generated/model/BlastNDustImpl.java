package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "enabled",
    "level",
    "window",
    "linker"
})
public class BlastNDustImpl implements BlastNDust {
  @JsonProperty(
      value = "enabled",
      defaultValue = "true"
  )
  private Boolean enabled;

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  private Integer level;

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  private Integer window;

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  private Integer linker;

  @JsonProperty(
      value = "enabled",
      defaultValue = "true"
  )
  public Boolean getEnabled() {
    return this.enabled;
  }

  @JsonProperty(
      value = "enabled",
      defaultValue = "true"
  )
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public Integer getLevel() {
    return this.level;
  }

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  public void setLevel(Integer level) {
    this.level = level;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public Integer getWindow() {
    return this.window;
  }

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  public void setWindow(Integer window) {
    this.window = window;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public Integer getLinker() {
    return this.linker;
  }

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  public void setLinker(Integer linker) {
    this.linker = linker;
  }
}
