package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastnDustImpl.class
)
public interface InputBlastnDust {
  @JsonProperty(
      value = "enable",
      defaultValue = "true"
  )
  boolean getEnable();

  @JsonProperty(
      value = "enable",
      defaultValue = "true"
  )
  void setEnable(boolean enable);

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  short getLevel();

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  void setLevel(short level);

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  short getWindow();

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  void setWindow(short window);

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  short getLinker();

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  void setLinker(short linker);
}
