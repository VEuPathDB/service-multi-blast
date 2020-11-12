package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = NewJobPostRequestJSONImpl.class
)
public interface NewJobPostRequestJSON {
  @JsonProperty("site")
  String getSite();

  @JsonProperty("site")
  void setSite(String site);

  @JsonProperty("organism")
  String getOrganism();

  @JsonProperty("organism")
  void setOrganism(String organism);

  @JsonProperty("target-type")
  String getTargetType();

  @JsonProperty("target-type")
  void setTargetType(String targetType);

  @JsonProperty("config")
  InputBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(InputBlastConfig config);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("description")
  void setDescription(String description);
}
