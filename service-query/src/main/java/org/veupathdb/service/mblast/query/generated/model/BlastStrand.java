package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastStrand {
  @JsonProperty("both")
  BOTH("both"),

  @JsonProperty("minus")
  MINUS("minus"),

  @JsonProperty("plus")
  PLUS("plus");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastStrand(String name) {
    this.value = name;
  }
}
