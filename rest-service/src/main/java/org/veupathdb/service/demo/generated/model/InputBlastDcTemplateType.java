package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastDcTemplateType {
  @JsonProperty("coding")
  CODING("coding"),

  @JsonProperty("optimal")
  OPTIMAL("optimal"),

  @JsonProperty("both")
  BOTH("both");

  private String name;

  InputBlastDcTemplateType(String name) {
    this.name = name;
  }
}
