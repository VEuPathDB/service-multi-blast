package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastLocationImpl.class
)
public interface InputBlastLocation {
  @JsonProperty("start")
   Long getStart();

  @JsonProperty("start")
  void setStart (Long start);

  @JsonProperty("stop")
   Long getStop();

  @JsonProperty("stop")
  void setStop (Long stop);
}
