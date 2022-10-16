package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "targetDisplayName",
    "targetFile"
})
public class QueryJobTargetImpl implements QueryJobTarget {
  @JsonProperty("targetDisplayName")
  private String targetDisplayName;

  @JsonProperty("targetFile")
  private String targetFile;

  @JsonProperty("targetDisplayName")
  public String getTargetDisplayName() {
    return this.targetDisplayName;
  }

  @JsonProperty("targetDisplayName")
  public void setTargetDisplayName(String targetDisplayName) {
    this.targetDisplayName = targetDisplayName;
  }

  @JsonProperty("targetFile")
  public String getTargetFile() {
    return this.targetFile;
  }

  @JsonProperty("targetFile")
  public void setTargetFile(String targetFile) {
    this.targetFile = targetFile;
  }
}
