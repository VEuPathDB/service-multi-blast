package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastSegMaskImpl.class
)
public interface InputBlastSegMask {
  @JsonProperty("enabled")
  boolean getEnabled();

  @JsonProperty("enabled")
  void setEnabled(boolean enabled);

  @JsonProperty("window")
  int getWindow();

  @JsonProperty("window")
  void setWindow(int window);

  @JsonProperty("locut")
  double getLocut();

  @JsonProperty("locut")
  void setLocut(double locut);

  @JsonProperty("hicut")
  double getHicut();

  @JsonProperty("hicut")
  void setHicut(double hicut);
}
