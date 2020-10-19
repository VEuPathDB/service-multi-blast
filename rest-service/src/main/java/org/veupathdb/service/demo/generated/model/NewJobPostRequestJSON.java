package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = NewJobPostRequestJSONImpl.class
)
public interface NewJobPostRequestJSON {
  @JsonProperty("config")
  InputBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(InputBlastConfig config);
}
