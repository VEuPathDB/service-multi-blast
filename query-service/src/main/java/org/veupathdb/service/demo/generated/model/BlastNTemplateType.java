package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastNTemplateType {
  @JsonProperty("coding")
  CODING("coding"),

  @JsonProperty("coding-and-optimal")
  CODINGANDOPTIMAL("coding-and-optimal"),

  @JsonProperty("optimal")
  OPTIMAL("optimal");

  public final String name;

  BlastNTemplateType(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
