package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputTBlastnTask {
  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastn-fast")
  TBLASTNFAST("tblastn-fast");

  private String name;

  InputTBlastnTask(String name) {
    this.name = name;
  }
}
