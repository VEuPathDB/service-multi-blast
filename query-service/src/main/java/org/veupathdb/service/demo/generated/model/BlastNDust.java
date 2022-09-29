package org.veupathdb.service.demo.generated.model;

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
  int getLevel();

  @JsonProperty(
      value = "level",
      defaultValue = "20"
  )
  void setLevel(int level);

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  int getWindow();

  @JsonProperty(
      value = "window",
      defaultValue = "64"
  )
  void setWindow(int window);

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  int getLinker();

  @JsonProperty(
      value = "linker",
      defaultValue = "1"
  )
  void setLinker(int linker);
}
