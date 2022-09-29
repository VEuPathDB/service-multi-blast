package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = BlastLocationImpl.class
)
public interface BlastLocation {
  @JsonProperty("start")
  int getStart();

  @JsonProperty("start")
  void setStart(int start);

  @JsonProperty("stop")
  int getStop();

  @JsonProperty("stop")
  void setStop(int stop);
}
