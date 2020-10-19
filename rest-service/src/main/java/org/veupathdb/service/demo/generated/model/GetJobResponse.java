package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = GetJobResponseImpl.class
)
public interface GetJobResponse {
  @JsonProperty("id")
  String getId();

  @JsonProperty("id")
  void setId(String id);

  @JsonProperty("status")
  JobStatus getStatus();

  @JsonProperty("status")
  void setStatus(JobStatus status);

  @JsonProperty("config")
  InputBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(InputBlastConfig config);
}
