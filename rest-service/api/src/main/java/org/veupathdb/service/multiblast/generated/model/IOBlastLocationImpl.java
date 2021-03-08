package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOBlastLocationImpl implements IOBlastLocation
{
  private Long start;
  private Long stop;

  @JsonProperty("start")
  public Long getStart() {
    return this.start;
  }

  @JsonProperty("start")
  public void setStart(Long start) {
    this.start = start;
  }

  @JsonProperty("stop")
  public Long getStop() {
    return this.stop;
  }

  @JsonProperty("stop")
  public void setStop(Long stop) {
    this.stop = stop;
  }
}
