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

  @JsonProperty("target")
  String getTarget();

  @JsonProperty("target")
  void setTarget(String target);

  @JsonProperty("config")
  InputBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(InputBlastConfig config);
}
