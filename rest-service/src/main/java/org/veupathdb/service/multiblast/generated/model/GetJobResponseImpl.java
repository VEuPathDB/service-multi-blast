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
  private Integer id;

  @JsonProperty("status")
  private IOJobStatus status;

  @JsonProperty("config")
  private IOBlastConfig config;

  @JsonProperty("id")
  public Integer getId() {
    return this.id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
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
  public IOBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(IOBlastConfig config) {
    this.config = config;
  }
}
