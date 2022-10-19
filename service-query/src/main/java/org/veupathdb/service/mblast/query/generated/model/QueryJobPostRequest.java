package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = QueryJobPostRequestImpl.class
)
public interface QueryJobPostRequest {
  @JsonProperty("jobConfig")
  QueryJobConfig getJobConfig();

  @JsonProperty("jobConfig")
  void setJobConfig(QueryJobConfig jobConfig);

  @JsonProperty("blastConfig")
  BlastQueryConfig getBlastConfig();

  @JsonProperty("blastConfig")
  void setBlastConfig(BlastQueryConfig blastConfig);

  @JsonProperty("meta")
  QueryJobUserMeta getMeta();

  @JsonProperty("meta")
  void setMeta(QueryJobUserMeta meta);
}
