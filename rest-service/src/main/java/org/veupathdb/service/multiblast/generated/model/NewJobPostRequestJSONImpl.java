package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "site",
    "organism",
    "target-type",
    "config",
    "description"
})
public class NewJobPostRequestJSONImpl implements NewJobPostRequestJSON {
  @JsonProperty("site")
  private String site;

  @JsonProperty("organism")
  private String organism;

  @JsonProperty("target-type")
  private String targetType;

  @JsonProperty("config")
  private IOBlastConfig config;

  @JsonProperty("description")
  private String description;

  @JsonProperty("site")
  public String getSite() {
    return this.site;
  }

  @JsonProperty("site")
  public void setSite(String site) {
    this.site = site;
  }

  @JsonProperty("organism")
  public String getOrganism() {
    return this.organism;
  }

  @JsonProperty("organism")
  public void setOrganism(String organism) {
    this.organism = organism;
  }

  @JsonProperty("target-type")
  public String getTargetType() {
    return this.targetType;
  }

  @JsonProperty("target-type")
  public void setTargetType(String targetType) {
    this.targetType = targetType;
  }

  @JsonProperty("config")
  public IOBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(IOBlastConfig config) {
    this.config = config;
  }

  @JsonProperty("description")
  public String getDescription() {
    return this.description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }
}
