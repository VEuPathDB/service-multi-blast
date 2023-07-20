package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TBlastNTask {
  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastn-fast")
  TBLASTNFAST("tblastn-fast");

  private final String value;

  public String getValue() {
    return this.value;
  }

  TBlastNTask(String name) {
    this.value = name;
  }
}
