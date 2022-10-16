package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = BlastLocationImpl.class
)
public interface BlastLocation {
  @JsonProperty("start")
  Integer getStart();

  @JsonProperty("start")
  void setStart(Integer start);

  @JsonProperty("stop")
  Integer getStop();

  @JsonProperty("stop")
  void setStop(Integer stop);
}
