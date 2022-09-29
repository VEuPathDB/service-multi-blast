package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "start",
    "stop"
})
public class BlastLocationImpl implements BlastLocation {
  @JsonProperty("start")
  private int start;

  @JsonProperty("stop")
  private int stop;

  @JsonProperty("start")
  public int getStart() {
    return this.start;
  }

  @JsonProperty("start")
  public void setStart(int start) {
    this.start = start;
  }

  @JsonProperty("stop")
  public int getStop() {
    return this.stop;
  }

  @JsonProperty("stop")
  public void setStop(int stop) {
    this.stop = stop;
  }
}
