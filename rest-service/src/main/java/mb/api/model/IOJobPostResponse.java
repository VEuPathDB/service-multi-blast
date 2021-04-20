package mb.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOJobPostResponseImpl.class)
public interface IOJobPostResponse
{
  @JsonProperty(JsonKeys.JobId)
  String getJobId();

  @JsonProperty(JsonKeys.JobId)
  IOJobPostResponse setJobId(String jobId);
}
