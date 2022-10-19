package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = QueryJobPatchRequestImpl.class
)
public interface QueryJobPatchRequest {
  @JsonProperty("meta")
  QueryJobUserMeta getMeta();

  @JsonProperty("meta")
  void setMeta(QueryJobUserMeta meta);
}
