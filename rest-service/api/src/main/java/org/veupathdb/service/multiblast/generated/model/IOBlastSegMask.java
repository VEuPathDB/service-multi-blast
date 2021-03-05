package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOBlastSegMaskImpl.class)
public interface IOBlastSegMask {
  @JsonProperty(JsonKeys.Window)
  Integer getWindow();

  @JsonProperty(JsonKeys.Window)
  void setWindow(Integer window);

  @JsonProperty(JsonKeys.LowCut)
   Double getLocut();

  @JsonProperty(JsonKeys.LowCut)
  void setLocut (Double locut);

  @JsonProperty(JsonKeys.HighCut)
   Double getHicut();

  @JsonProperty(JsonKeys.HighCut)
  void setHicut (Double hicut);
}
