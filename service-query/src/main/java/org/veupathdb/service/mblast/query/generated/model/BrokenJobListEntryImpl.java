package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "rawConfig",
    "createdOn",
    "failedOn"
})
public class BrokenJobListEntryImpl implements BrokenJobListEntry {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("rawConfig")
  private Object rawConfig;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn;

  @JsonProperty("failedOn")
  private OffsetDateTime failedOn;

  @JsonProperty("queryJobID")
  public String getQueryJobID() {
    return this.queryJobID;
  }

  @JsonProperty("queryJobID")
  public void setQueryJobID(String queryJobID) {
    this.queryJobID = queryJobID;
  }

  @JsonProperty("rawConfig")
  public Object getRawConfig() {
    return this.rawConfig;
  }

  @JsonProperty("rawConfig")
  public void setRawConfig(Object rawConfig) {
    this.rawConfig = rawConfig;
  }

  @JsonProperty("createdOn")
  public OffsetDateTime getCreatedOn() {
    return this.createdOn;
  }

  @JsonProperty("createdOn")
  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  @JsonProperty("failedOn")
  public OffsetDateTime getFailedOn() {
    return this.failedOn;
  }

  @JsonProperty("failedOn")
  public void setFailedOn(OffsetDateTime failedOn) {
    this.failedOn = failedOn;
  }
}
