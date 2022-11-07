package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "jobConfig",
    "blastConfig",
    "userMeta"
})
public class QueryJobPostRequestImpl implements QueryJobPostRequest {
  @JsonProperty("jobConfig")
  private QueryJobConfig jobConfig;

  @JsonProperty("blastConfig")
  private BlastQueryConfig blastConfig;

  @JsonProperty("userMeta")
  private QueryJobUserMeta userMeta;

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

  @JsonProperty("userMeta")
  public QueryJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(QueryJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }
}
