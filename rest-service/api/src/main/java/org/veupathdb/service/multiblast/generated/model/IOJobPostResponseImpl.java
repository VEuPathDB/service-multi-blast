package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOJobPostResponseImpl implements IOJobPostResponse
{
  private String jobId;

  public String getJobId() {
    return this.jobId;
  }

  public IOJobPostResponse setJobId(String jobId) {
    this.jobId = jobId;
    return this;
  }
}
