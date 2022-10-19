package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("meta")
public class QueryJobPatchRequestImpl implements QueryJobPatchRequest {
  @JsonProperty("meta")
  private QueryJobUserMeta meta;

  @JsonProperty("meta")
  public QueryJobUserMeta getMeta() {
    return this.meta;
  }

  @JsonProperty("meta")
  public void setMeta(QueryJobUserMeta meta) {
    this.meta = meta;
  }
}
