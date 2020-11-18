package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = InputBlastnDustImpl.class
)
public interface InputBlastnDust {
  @JsonProperty("enable")
  Boolean getEnable();

  @JsonProperty("enable")
  void setEnable(boolean enable);

  @JsonProperty("level")
  Short getLevel();

  @JsonProperty("level")
  void setLevel(short level);

  @JsonProperty("window")
  Short getWindow();

  @JsonProperty("window")
  void setWindow(short window);

  @JsonProperty("linker")
  Short getLinker();

  @JsonProperty("linker")
  void setLinker(short linker);
}
