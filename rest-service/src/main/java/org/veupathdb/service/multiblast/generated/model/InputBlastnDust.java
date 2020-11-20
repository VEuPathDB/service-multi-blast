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
  void setEnable (Boolean enable);

  @JsonProperty("level")
   Short getLevel();

  @JsonProperty("level")
  void setLevel (Short level);

  @JsonProperty("window")
   Short getWindow();

  @JsonProperty("window")
  void setWindow (Short window);

  @JsonProperty("linker")
   Short getLinker();

  @JsonProperty("linker")
  void setLinker (Short linker);
}
