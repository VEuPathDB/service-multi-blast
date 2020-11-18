package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastSegMaskImpl.class
)
public interface InputBlastSegMask {
  @JsonProperty("enabled")
  Boolean getEnabled();

  @JsonProperty("enabled")
  void setEnabled(boolean enabled);

  @JsonProperty("window")
  Integer getWindow();

  @JsonProperty("window")
  void setWindow(int window);

  @JsonProperty("locut")
  Double getLocut();

  @JsonProperty("locut")
  void setLocut(double locut);

  @JsonProperty("hicut")
  Double getHicut();

  @JsonProperty("hicut")
  void setHicut(double hicut);
}
