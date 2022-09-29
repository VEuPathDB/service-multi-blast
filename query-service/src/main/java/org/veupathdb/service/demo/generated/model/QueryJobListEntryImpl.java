package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "status",
    "meta"
})
public class QueryJobListEntryImpl implements QueryJobListEntry {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("status")
  private JobStatus status;

  @JsonProperty("meta")
  private QueryJobRequestUserMeta meta;

  @JsonProperty("queryJobID")
  public String getQueryJobID() {
    return this.queryJobID;
  }

  @JsonProperty("queryJobID")
  public void setQueryJobID(String queryJobID) {
    this.queryJobID = queryJobID;
  }

  @JsonProperty("status")
  public JobStatus getStatus() {
    return this.status;
  }

  @JsonProperty("status")
  public void setStatus(JobStatus status) {
    this.status = status;
  }

  @JsonProperty("meta")
  public QueryJobRequestUserMeta getMeta() {
    return this.meta;
  }

  @JsonProperty("meta")
  public void setMeta(QueryJobRequestUserMeta meta) {
    this.meta = meta;
  }
}
