package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastStrand {
  @JsonProperty("plus")
  PLUS("plus"),

  @JsonProperty("minus")
  MINUS("minus"),

  @JsonProperty("both")
  BOTH("both");

  private String name;

  InputBlastStrand(String name) {
    this.name = name;
  }
}
