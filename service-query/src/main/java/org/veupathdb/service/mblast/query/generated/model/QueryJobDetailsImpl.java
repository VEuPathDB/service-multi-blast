package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "status",
    "jobConfig",
    "blastConfig",
    "createdOn",
    "userMeta",
    "subJobs"
})
public class QueryJobDetailsImpl implements QueryJobDetails {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("status")
  private JobStatus status;

  @JsonProperty("jobConfig")
  private QueryJobConfig jobConfig;

  @JsonProperty("blastConfig")
  private BlastQueryConfig blastConfig;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn;

  @JsonProperty("userMeta")
  private QueryJobUserMeta userMeta;

  @JsonProperty("subJobs")
  private List<String> subJobs;

  @JsonProperty("queryJobID")
  public String getQueryJobID() {
    return this.queryJobID;
  }

  @JsonProperty("queryJobID")
  public void setQueryJobID(String queryJobID) {
    this.queryJobID = queryJobID;
  }

  @JsonProperty("status")
  public JobStatus getStatus() {
    return this.status;
  }

  @JsonProperty("status")
  public void setStatus(JobStatus status) {
    this.status = status;
  }

  @JsonProperty("jobConfig")
  public QueryJobConfig getJobConfig() {
    return this.jobConfig;
  }

  @JsonProperty("jobConfig")
  public void setJobConfig(QueryJobConfig jobConfig) {
    this.jobConfig = jobConfig;
  }

  @JsonProperty("blastConfig")
  public BlastQueryConfig getBlastConfig() {
    return this.blastConfig;
  }

  @JsonProperty("blastConfig")
  public void setBlastConfig(BlastQueryConfig blastConfig) {
    this.blastConfig = blastConfig;
  }

  @JsonProperty("createdOn")
  public OffsetDateTime getCreatedOn() {
    return this.createdOn;
  }

  @JsonProperty("createdOn")
  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  @JsonProperty("userMeta")
  public QueryJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(QueryJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }

  @JsonProperty("subJobs")
  public List<String> getSubJobs() {
    return this.subJobs;
  }

  @JsonProperty("subJobs")
  public void setSubJobs(List<String> subJobs) {
    this.subJobs = subJobs;
  }
}
