package mb.api.model.blast.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.model.blast.IOBlastnDust;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastnDustImpl implements IOBlastnDust
{
  private Boolean enable;
  private Short   level;
  private Short   window;
  private Short   linker;

  @JsonProperty("enable")
  public Boolean getEnable() {
    return this.enable;
  }

  @JsonProperty("enable")
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  @JsonProperty("level")
  public Short getLevel() {
    return this.level;
  }

  @JsonProperty("level")
  public void setLevel(Short level) {
    this.level = level;
  }

  @JsonProperty("window")
  public Short getWindow() {
    return this.window;
  }

  @JsonProperty("window")
  public void setWindow(Short window) {
    this.window = window;
  }

  @JsonProperty("linker")
  public Short getLinker() {
    return this.linker;
  }

  @JsonProperty("linker")
  public void setLinker(Short linker) {
    this.linker = linker;
  }
}
