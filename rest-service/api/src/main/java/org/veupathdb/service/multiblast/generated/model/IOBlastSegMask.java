package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = IOBlastSegMaskImpl.class
)
public interface IOBlastSegMask {
  @JsonProperty("window")
  Integer getWindow();

  @JsonProperty("window")
  void setWindow(Integer window);

  @JsonProperty("locut")
   Double getLocut();

  @JsonProperty("locut")
  void setLocut (Double locut);

  @JsonProperty("hicut")
   Double getHicut();

  @JsonProperty("hicut")
  void setHicut (Double hicut);
}
