package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOBlastnDustImpl.class)
public interface IOBlastnDust {
  @JsonProperty(JsonKeys.Enable)
   Boolean getEnable();

  @JsonProperty(JsonKeys.Enable)
  void setEnable (Boolean enable);

  @JsonProperty(JsonKeys.Level)
   Short getLevel();

  @JsonProperty(JsonKeys.Level)
  void setLevel (Short level);

  @JsonProperty(JsonKeys.Window)
   Short getWindow();

  @JsonProperty(JsonKeys.Window)
  void setWindow (Short window);

  @JsonProperty(JsonKeys.Linker)
   Short getLinker();

  @JsonProperty(JsonKeys.Linker)
  void setLinker (Short linker);
}
