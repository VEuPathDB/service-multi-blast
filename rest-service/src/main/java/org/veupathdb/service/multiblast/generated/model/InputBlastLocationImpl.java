package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "start",
    "stop"
})
public class InputBlastLocationImpl implements InputBlastLocation {
  @JsonProperty("start")
  private Long start;

  @JsonProperty("stop")
  private Long stop;

  @JsonProperty("start")
  public Long getStart() {
    return this.start;
  }

  @JsonProperty("start")
  public void setStart(long start) {
    this.start = start;
  }

  @JsonProperty("stop")
  public Long getStop() {
    return this.stop;
  }

  @JsonProperty("stop")
  public void setStop(long stop) {
    this.stop = stop;
  }
}
