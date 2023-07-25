package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastNTemplateType {
  @JsonProperty("coding")
  CODING("coding"),

  @JsonProperty("coding-and-optimal")
  CODINGANDOPTIMAL("coding-and-optimal"),

  @JsonProperty("optimal")
  OPTIMAL("optimal");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastNTemplateType(String name) {
    this.value = name;
  }
}
