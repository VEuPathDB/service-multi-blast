package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("targets")
public class MetaResponseImpl implements MetaResponse {
  @JsonProperty("targets")
  private OutputSiteListing targets;

  @JsonProperty("targets")
  public OutputSiteListing getTargets() {
    return this.targets;
  }

  @JsonProperty("targets")
  public void setTargets(OutputSiteListing targets) {
    this.targets = targets;
  }
}
