package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "description",
    "status",
    "config"
})
public class LongJobResponseImpl implements LongJobResponse {
  @JsonProperty("id")
  private String id;

  @JsonProperty("description")
  private String description;

  @JsonProperty("status")
  private IOJobStatus status;

  @JsonProperty("config")
  private IOBlastConfig config;

  @JsonProperty("id")
  public String getId() {
    return this.id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("description")
  public String getDescription() {
    return this.description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("status")
  public IOJobStatus getStatus() {
    return this.status;
  }

  @JsonProperty("status")
  public void setStatus(IOJobStatus status) {
    this.status = status;
  }

  @JsonProperty("config")
  public IOBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(IOBlastConfig config) {
    this.config = config;
  }
}
