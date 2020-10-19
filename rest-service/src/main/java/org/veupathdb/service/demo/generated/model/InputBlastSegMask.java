package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastSegMaskImpl.class
)
public interface InputBlastSegMask {
  @JsonProperty(
      value = "enabled",
      defaultValue = "false"
  )
  boolean getEnabled();

  @JsonProperty(
      value = "enabled",
      defaultValue = "false"
  )
  void setEnabled(boolean enabled);

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  int getWindow();

  @JsonProperty(
      value = "window",
      defaultValue = "12"
  )
  void setWindow(int window);

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  double getLocut();

  @JsonProperty(
      value = "locut",
      defaultValue = "2.2"
  )
  void setLocut(double locut);

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  double getHicut();

  @JsonProperty(
      value = "hicut",
      defaultValue = "2.5"
  )
  void setHicut(double hicut);
}
