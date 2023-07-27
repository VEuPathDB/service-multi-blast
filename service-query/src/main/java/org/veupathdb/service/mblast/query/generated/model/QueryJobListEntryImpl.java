package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "status",
    "site",
    "createdOn",
    "userMeta"
})
public class QueryJobListEntryImpl implements QueryJobListEntry {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("status")
  private JobStatus status;

  @JsonProperty("site")
  private TargetSite site;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
  )
  @JsonDeserialize(
      using = TimestampDeserializer.class
  )
  @JsonProperty("createdOn")
  private Date createdOn;

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

  @JsonProperty("createdOn")
  public Date getCreatedOn() {
    return this.createdOn;
  }

  @JsonProperty("createdOn")
  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
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
