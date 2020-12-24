package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastnDcTemplateType {
  @JsonProperty("coding")
  CODING("coding"),

  @JsonProperty("optimal")
  OPTIMAL("optimal"),

  @JsonProperty("both")
  BOTH("both");

  public final String name;

  IOBlastnDcTemplateType(String name) {
    this.name = name;
  }
}
