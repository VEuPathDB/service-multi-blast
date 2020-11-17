package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("jobId")
public class NewJobPostResponseImpl implements NewJobPostResponse {
  @JsonProperty("jobId")
  private int jobId;

  @JsonProperty("jobId")
  public int getJobId() {
    return this.jobId;
  }

  @JsonProperty("jobId")
  public void setJobId(int jobId) {
    this.jobId = jobId;
  }
}
