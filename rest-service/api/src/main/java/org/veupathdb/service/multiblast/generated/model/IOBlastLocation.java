package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOBlastLocationImpl.class)
public interface IOBlastLocation {
  @JsonProperty(JsonKeys.Start)
   Long getStart();

  @JsonProperty(JsonKeys.Start)
  void setStart (Long start);

  @JsonProperty(JsonKeys.Stop)
   Long getStop();

  @JsonProperty(JsonKeys.Stop)
  void setStop (Long stop);
}
