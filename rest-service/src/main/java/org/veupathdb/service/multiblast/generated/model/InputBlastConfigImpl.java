package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("tool")
public class InputBlastConfigImpl implements InputBlastConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("tool")
  public InputBlastTool getTool() {
    return this.tool;
  }
}
