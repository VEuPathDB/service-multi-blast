package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = NewJobPostResponseImpl.class
)
public interface NewJobPostResponse {
  @JsonProperty("jobId")
  Integer getJobId();

  @JsonProperty("jobId")
  void setJobId(Integer jobId);
}
