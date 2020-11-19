package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastnDcTemplateType {
  @JsonProperty("coding")
  CODING("coding"),

  @JsonProperty("optimal")
  OPTIMAL("optimal"),

  @JsonProperty("both")
  BOTH("both");

  public final String name;

  InputBlastnDcTemplateType(String name) {
    this.name = name;
  }
}
