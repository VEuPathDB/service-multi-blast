package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
  private String site;
  private IOJobTarget[] targets;
  private IOBlastConfig config;
  private String description;

  @Override
  public String getSite() {
    return this.site;
  }

  @Override
  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public IOJobTarget[] getTargets() {
    return targets;
  }

  @Override
  public void setTargets(IOJobTarget[] targets) {
    this.targets = targets;
  }

  @Override
  public IOBlastConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(IOBlastConfig config) {
    this.config = config;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }
}
