package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "status",
    "jobConfig",
    "blastConfig",
    "meta",
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

  @JsonProperty("meta")
  private QueryJobRequestUserMeta meta;

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

  @JsonProperty("meta")
  public QueryJobRequestUserMeta getMeta() {
    return this.meta;
  }

  @JsonProperty("meta")
  public void setMeta(QueryJobRequestUserMeta meta) {
    this.meta = meta;
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
