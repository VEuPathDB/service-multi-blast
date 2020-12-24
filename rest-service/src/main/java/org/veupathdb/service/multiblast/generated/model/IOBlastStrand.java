package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastStrand {
  @JsonProperty("plus")
  PLUS("plus"),

  @JsonProperty("minus")
  MINUS("minus"),

  @JsonProperty("both")
  BOTH("both");

  public final String name;

  IOBlastStrand(String name) {
    this.name = name;
  }
}
