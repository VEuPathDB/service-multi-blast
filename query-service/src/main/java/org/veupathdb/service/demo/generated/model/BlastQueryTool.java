package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastQueryTool {
  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("deltablast")
  DELTABLAST("deltablast"),

  @JsonProperty("psiblast")
  PSIBLAST("psiblast"),

  @JsonProperty("rpsblast")
  RPSBLAST("rpsblast"),

  @JsonProperty("rpstblastn")
  RPSTBLASTN("rpstblastn"),

  @JsonProperty("tblastn")
  TBLASTN("tblastn"),

  @JsonProperty("tblastx")
  TBLASTX("tblastx");

  public final String name;

  BlastQueryTool(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
