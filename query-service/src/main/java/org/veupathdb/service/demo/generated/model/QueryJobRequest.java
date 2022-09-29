package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = QueryJobRequestImpl.class
)
public interface QueryJobRequest {
  @JsonProperty("jobConfig")
  QueryJobConfig getJobConfig();

  @JsonProperty("jobConfig")
  void setJobConfig(QueryJobConfig jobConfig);

  @JsonProperty("blastConfig")
  BlastQueryConfig getBlastConfig();

  @JsonProperty("blastConfig")
  void setBlastConfig(BlastQueryConfig blastConfig);

  @JsonProperty("meta")
  QueryJobRequestUserMeta getMeta();

  @JsonProperty("meta")
  void setMeta(QueryJobRequestUserMeta meta);
}
