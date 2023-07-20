package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TBlastNCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats"),

  @JsonProperty("comp-based-score-adjustment-conditional")
  COMPBASEDSCOREADJUSTMENTCONDITIONAL("comp-based-score-adjustment-conditional"),

  @JsonProperty("comp-based-score-adjustment-unconditional")
  COMPBASEDSCOREADJUSTMENTUNCONDITIONAL("comp-based-score-adjustment-unconditional");

  private final String value;

  public String getValue() {
    return this.value;
  }

  TBlastNCompBasedStats(String name) {
    this.value = name;
  }
}
