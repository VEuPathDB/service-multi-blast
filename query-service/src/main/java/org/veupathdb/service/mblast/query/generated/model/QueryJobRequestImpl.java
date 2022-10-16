package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jobConfig",
    "blastConfig",
    "meta"
})
public class QueryJobRequestImpl implements QueryJobRequest {
  @JsonProperty("jobConfig")
  private QueryJobConfig jobConfig;

  @JsonProperty("blastConfig")
  private BlastQueryConfig blastConfig;

  @JsonProperty("meta")
  private QueryJobUserMeta meta;

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
  public QueryJobUserMeta getMeta() {
    return this.meta;
  }

  @JsonProperty("meta")
  public void setMeta(QueryJobUserMeta meta) {
    this.meta = meta;
  }
}
