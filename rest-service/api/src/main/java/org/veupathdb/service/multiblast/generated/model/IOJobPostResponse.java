package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOJobPostResponseImpl.class)
public interface IOJobPostResponse
{
  @JsonProperty(JsonKeys.JobID)
  String getJobId();

  @JsonProperty(JsonKeys.JobID)
  IOJobPostResponse setJobId(String jobId);
}
