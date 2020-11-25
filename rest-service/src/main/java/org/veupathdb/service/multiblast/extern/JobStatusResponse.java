package org.veupathdb.service.multiblast.extern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.veupathdb.service.multiblast.model.internal.JobStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatusResponse
{
  private String status;

  @JsonSetter("status")
  public JobStatusResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  @JsonIgnore
  public JobStatus getStatus() {
    var status = this.status.toLowerCase();

    if ("completed".equals(status))
      return JobStatus.Completed;

    if (status.contains("fail"))
      return JobStatus.Errored;

    if ("grabbed".equals(status))
      return JobStatus.InProgress;

    return JobStatus.Queued;
  }
}
