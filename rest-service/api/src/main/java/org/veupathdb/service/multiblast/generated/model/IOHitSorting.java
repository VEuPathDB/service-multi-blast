package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOHitSorting {
  @JsonProperty("by-eval")
  BYEVAL("by-eval"),

  @JsonProperty("by-bit-score")
  BYBITSCORE("by-bit-score"),

  @JsonProperty("by-total-score")
  BYTOTALSCORE("by-total-score"),

  @JsonProperty("by-percent-identity")
  BYPERCENTIDENTITY("by-percent-identity"),

  @JsonProperty("by-query-coverage")
  BYQUERYCOVERAGE("by-query-coverage");

  public final String name;

  IOHitSorting(String name) {
    this.name = name;
  }
}
