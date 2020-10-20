package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "site",
    "target",
    "config"
})
public class NewJobPostRequestJSONImpl implements NewJobPostRequestJSON {
  @JsonProperty("site")
  private String site;

  @JsonProperty("target")
  private String target;

  @JsonProperty("config")
  private InputBlastConfig config;

  @JsonProperty("site")
  public String getSite() {
    return this.site;
  }

  @JsonProperty("site")
  public void setSite(String site) {
    this.site = site;
  }

  @JsonProperty("target")
  public String getTarget() {
    return this.target;
  }

  @JsonProperty("target")
  public void setTarget(String target) {
    this.target = target;
  }

  @JsonProperty("config")
  public InputBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(InputBlastConfig config) {
    this.config = config;
  }
}
