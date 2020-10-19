package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastTool {
  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastx")
  TBLASTX("tblastx"),

  @JsonProperty("rpsblast")
  RPSBLAST("rpsblast");

  private String name;

  InputBlastTool(String name) {
    this.name = name;
  }
}
