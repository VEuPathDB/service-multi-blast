package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = BlastNDustImpl.class
)
public interface BlastNDust {
  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  Integer getLevel();

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  void setLevel(Integer level);

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  Integer getWindow();

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  void setWindow(Integer window);

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  Integer getLinker();

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  void setLinker(Integer linker);
}
