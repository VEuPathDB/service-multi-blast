package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("jobId")
public class NewJobPostResponseImpl implements NewJobPostResponse {
  @JsonProperty("jobId")
  private String jobId;

  @JsonProperty("jobId")
  public String getJobId() {
    return this.jobId;
  }

  @JsonProperty("jobId")
  public void setJobId(String jobId) {
    this.jobId = jobId;
  }
}
