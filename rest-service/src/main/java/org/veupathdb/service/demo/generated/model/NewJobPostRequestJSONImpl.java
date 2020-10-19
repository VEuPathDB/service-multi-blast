package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("config")
public class NewJobPostRequestJSONImpl implements NewJobPostRequestJSON {
  @JsonProperty("config")
  private InputBlastConfig config;

  @JsonProperty("config")
  public InputBlastConfig getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(InputBlastConfig config) {
    this.config = config;
  }
}
