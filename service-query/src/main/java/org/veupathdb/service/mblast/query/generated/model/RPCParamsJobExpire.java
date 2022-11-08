package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = RPCParamsJobExpireImpl.class
)
public interface RPCParamsJobExpire {
  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);
}
