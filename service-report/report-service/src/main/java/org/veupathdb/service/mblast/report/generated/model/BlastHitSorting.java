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

  public final String name;

  BlastHitSorting(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
