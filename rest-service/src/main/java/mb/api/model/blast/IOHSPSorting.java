package mb.api.model.blast;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOHSPSorting {
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

  public final String name;

  IOHSPSorting(String name) {
    this.name = name;
  }
}
