package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastStrand {
  @JsonProperty("both")
  BOTH("both"),

  @JsonProperty("minus")
  MINUS("minus"),

  @JsonProperty("plus")
  PLUS("plus");

  public final String name;

  BlastStrand(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
