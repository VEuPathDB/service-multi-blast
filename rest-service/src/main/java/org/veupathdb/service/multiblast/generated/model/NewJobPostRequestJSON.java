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

  @JsonProperty("targets")
  IOJobTarget[] getTargets();

  @JsonProperty("targets")
  void setTargets(IOJobTarget[] targets);

  @JsonProperty("config")
  IOBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(IOBlastConfig config);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("description")
  void setDescription(String description);
}
