package org.veupathdb.service.mblast.query.generated.model;

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

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastQueryTool(String name) {
    this.value = name;
  }
}
