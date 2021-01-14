package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = LongJobResponseImpl.class
)
public interface LongJobResponse {
  @JsonProperty("id")
  String getId();

  @JsonProperty("id")
  void setId(String id);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("description")
  void setDescription(String description);

  @JsonProperty("status")
  IOJobStatus getStatus();

  @JsonProperty("status")
  void setStatus(IOJobStatus status);

  @JsonProperty("config")
  IOBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(IOBlastConfig config);
}
