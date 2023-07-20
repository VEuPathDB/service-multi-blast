package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastHitSorting {
  @JsonProperty("by-evalue")
  BYEVALUE("by-evalue"),

  @JsonProperty("by-bit-score")
  BYBITSCORE("by-bit-score"),

  @JsonProperty("by-total-score")
  BYTOTALSCORE("by-total-score"),

  @JsonProperty("by-percent-identity")
  BYPERCENTIDENTITY("by-percent-identity"),

  @JsonProperty("by-query-coverage")
  BYQUERYCOVERAGE("by-query-coverage");

  private final String value;

  public String getValue() {
    return this.value;
  }

  BlastHitSorting(String name) {
    this.value = name;
  }
}
