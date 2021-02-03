package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = IOBlastLocationImpl.class
)
public interface IOBlastLocation {
  @JsonProperty("start")
   Long getStart();

  @JsonProperty("start")
  void setStart (Long start);

  @JsonProperty("stop")
   Long getStop();

  @JsonProperty("stop")
  void setStop (Long stop);
}
