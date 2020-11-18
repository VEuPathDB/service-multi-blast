package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = GetJobResponseImpl.class
)
public interface GetJobResponse {
  @JsonProperty("id")
  Integer getId();

  @JsonProperty("id")
  void setId(int id);

  @JsonProperty("status")
  IOJobStatus getStatus();

  @JsonProperty("status")
  void setStatus(IOJobStatus status);

  @JsonProperty("config")
  InputBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(InputBlastConfig config);
}
