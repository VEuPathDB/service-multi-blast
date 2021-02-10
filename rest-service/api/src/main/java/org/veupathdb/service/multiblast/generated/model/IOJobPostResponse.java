package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = IOJobPostResponseImpl.class)
public interface IOJobPostResponse
{
  @JsonProperty("jobId")
  String getJobId();

  @JsonProperty("jobId")
  IOJobPostResponse setJobId(String jobId);
}
