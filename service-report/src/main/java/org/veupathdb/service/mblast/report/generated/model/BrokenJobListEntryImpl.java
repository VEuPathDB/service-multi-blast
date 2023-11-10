package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reportJobID",
    "rawConfig",
    "createdOn",
    "failedOn"
})
public class BrokenJobListEntryImpl implements BrokenJobListEntry {
  @JsonProperty("reportJobID")
  private String reportJobID;

  @JsonProperty("rawConfig")
  private Object rawConfig;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn;

  @JsonProperty("failedOn")
  private OffsetDateTime failedOn;

  @JsonProperty("reportJobID")
  public String getReportJobID() {
    return this.reportJobID;
  }

  @JsonProperty("reportJobID")
  public void setReportJobID(String reportJobID) {
    this.reportJobID = reportJobID;
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
