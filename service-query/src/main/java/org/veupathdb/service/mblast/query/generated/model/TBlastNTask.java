package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TBlastNTask {
  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastn-fast")
  TBLASTNFAST("tblastn-fast");

  public final String name;

  TBlastNTask(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
