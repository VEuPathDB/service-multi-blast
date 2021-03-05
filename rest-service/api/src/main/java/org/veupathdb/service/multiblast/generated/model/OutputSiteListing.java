package org.veupathdb.service.multiblast.generated.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = OutputSiteListingImpl.class)
public interface OutputSiteListing {
  @JsonProperty("Site")
  List<OutputListOrganism> getSite();

  @JsonProperty("Site")
  void setSite(List<OutputListOrganism> site);

  @JsonAnyGetter
  Map<String, Object> getAdditionalProperties();

  @JsonAnySetter
  void setAdditionalProperties(String key, Object value);
}
