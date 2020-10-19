package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastLocationImpl.class
)
public interface InputBlastLocation {
  @JsonProperty("start")
  long getStart();

  @JsonProperty("start")
  void setStart(long start);

  @JsonProperty("stop")
  long getStop();

  @JsonProperty("stop")
  void setStop(long stop);
}
