package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "status",
    "config"
})
public class GetJobResponseImpl implements GetJobResponse {
  @JsonProperty("id")
  private int id;

  @JsonProperty("status")
  private IOJobStatus status;

  @JsonProperty("config")
  private InputBlastConfig config;

  @JsonProperty("id")
  public int getId() {
    return this.id;
  }

  @JsonProperty("id")
  public void setId(int id) {
    this.id = id;
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
  public InputBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(InputBlastConfig config) {
    this.config = config;
  }
}
