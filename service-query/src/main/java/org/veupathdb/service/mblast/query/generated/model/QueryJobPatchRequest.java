package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = QueryJobPatchRequestImpl.class
)
public interface QueryJobPatchRequest {
  @JsonProperty("userMeta")
  QueryJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(QueryJobUserMeta userMeta);
}
