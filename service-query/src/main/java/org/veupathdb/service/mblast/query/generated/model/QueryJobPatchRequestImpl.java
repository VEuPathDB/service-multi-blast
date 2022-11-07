package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("userMeta")
public class QueryJobPatchRequestImpl implements QueryJobPatchRequest {
  @JsonProperty("userMeta")
  private QueryJobUserMeta userMeta;

  @JsonProperty("userMeta")
  public QueryJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(QueryJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }
}
