package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastTool {
  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastx")
  TBLASTX("tblastx");

  public final String name;

  IOBlastTool(String name) {
    this.name = name;
  }
}
