package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryJobID",
    "blastConfig",
    "addToUserCollection",
    "userMeta"
})
public class ReportJobPostRequestImpl implements ReportJobPostRequest {
  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty(
      value = "blastConfig",
      defaultValue = "{\n"
              + "\"formatType\" : \"pairwise\"\n"
              + "}"
  )
  private BlastFormatConfig blastConfig;

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "true"
  )
  private Boolean addToUserCollection;

  @JsonProperty("userMeta")
  private ReportJobUserMeta userMeta;

  @JsonProperty("queryJobID")
  public String getQueryJobID() {
    return this.queryJobID;
  }

  @JsonProperty("queryJobID")
  public void setQueryJobID(String queryJobID) {
    this.queryJobID = queryJobID;
  }

  @JsonProperty(
      value = "blastConfig",
      defaultValue = "{\n"
              + "\"formatType\" : \"pairwise\"\n"
              + "}"
  )
  public BlastFormatConfig getBlastConfig() {
    return this.blastConfig;
  }

  @JsonProperty(
      value = "blastConfig",
      defaultValue = "{\n"
              + "\"formatType\" : \"pairwise\"\n"
              + "}"
  )
  public void setBlastConfig(BlastFormatConfig blastConfig) {
    this.blastConfig = blastConfig;
  }

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "true"
  )
  public Boolean getAddToUserCollection() {
    return this.addToUserCollection;
  }

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "true"
  )
  public void setAddToUserCollection(Boolean addToUserCollection) {
    this.addToUserCollection = addToUserCollection;
  }

  @JsonProperty("userMeta")
  public ReportJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(ReportJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }
}
