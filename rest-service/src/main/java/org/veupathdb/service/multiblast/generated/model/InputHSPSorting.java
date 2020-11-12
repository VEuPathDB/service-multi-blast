package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputHSPSorting {
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

  private String name;

  InputHSPSorting(String name) {
    this.name = name;
  }
}
