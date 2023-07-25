package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastHSPSorting {
  @JsonProperty("by-hsp-evalue")
  BYHSPEVALUE("by-hsp-evalue"),

  @JsonProperty("by-hsp-score")
  BYHSPSCORE("by-hsp-score"),

  @JsonProperty("by-hsp-query-start")
  BYHSPQUERYSTART("by-hsp-query-start"),

  @JsonProperty("by-hsp-percent-identity")
  BYHSPPERCENTIDENTITY("by-hsp-percent-identity"),

  @JsonProperty("by-hsp-subject-start")
  BYHSPSUBJECTSTART("by-hsp-subject-start");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastHSPSorting(String name) {
    this.value = name;
  }
}
