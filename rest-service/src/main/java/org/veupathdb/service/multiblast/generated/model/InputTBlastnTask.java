package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputTBlastnTask {
  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastn-fast")
  TBLASTNFAST("tblastn-fast");

  public final String name;

  InputTBlastnTask(String name) {
    this.name = name;
  }
}
