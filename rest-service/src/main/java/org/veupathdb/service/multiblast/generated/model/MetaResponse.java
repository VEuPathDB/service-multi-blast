package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = MetaResponseImpl.class
)
public interface MetaResponse {
  @JsonProperty("targets")
  OutputSiteListing getTargets();

  @JsonProperty("targets")
  void setTargets(OutputSiteListing targets);
}
