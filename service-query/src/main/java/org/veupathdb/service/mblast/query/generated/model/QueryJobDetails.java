package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.OffsetDateTime;
import java.util.List;

@JsonDeserialize(
    as = QueryJobDetailsImpl.class
)
public interface QueryJobDetails {
  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);

  @JsonProperty("status")
  JobStatus getStatus();

  @JsonProperty("status")
  void setStatus(JobStatus status);

  @JsonProperty("jobConfig")
  QueryJobConfig getJobConfig();

  @JsonProperty("jobConfig")
  void setJobConfig(QueryJobConfig jobConfig);

  @JsonProperty("blastConfig")
  BlastQueryConfig getBlastConfig();

  @JsonProperty("blastConfig")
  void setBlastConfig(BlastQueryConfig blastConfig);

  @JsonProperty("createdOn")
  OffsetDateTime getCreatedOn();

  @JsonProperty("createdOn")
  void setCreatedOn(OffsetDateTime createdOn);

  @JsonProperty("userMeta")
  QueryJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(QueryJobUserMeta userMeta);

  @JsonProperty("subJobs")
  List<String> getSubJobs();

  @JsonProperty("subJobs")
  void setSubJobs(List<String> subJobs);
}
