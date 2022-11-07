package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "status",
    "site",
    "userMeta"
})
public class QueryJobListEntryImpl implements QueryJobListEntry {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("status")
  private JobStatus status;

  @JsonProperty("site")
  private TargetSite site;

  @JsonProperty("userMeta")
  private QueryJobUserMeta userMeta;

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

  @JsonProperty("site")
  public TargetSite getSite() {
    return this.site;
  }

  @JsonProperty("site")
  public void setSite(TargetSite site) {
    this.site = site;
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
