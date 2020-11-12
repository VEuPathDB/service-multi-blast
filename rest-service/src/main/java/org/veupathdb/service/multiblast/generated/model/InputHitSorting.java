package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputHitSorting {
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

  private String name;

  InputHitSorting(String name) {
    this.name = name;
  }
}
