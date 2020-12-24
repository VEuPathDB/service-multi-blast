package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = NewJobPostResponseImpl.class
)
public interface NewJobPostResponse {
  @JsonProperty("jobId")
  String getJobId();

  @JsonProperty("jobId")
  void setJobId(String jobId);
}
