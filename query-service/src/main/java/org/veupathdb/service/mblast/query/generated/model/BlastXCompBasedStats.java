package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastXCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats"),

  @JsonProperty("comp-based-score-adjustment-conditional")
  COMPBASEDSCOREADJUSTMENTCONDITIONAL("comp-based-score-adjustment-conditional"),

  @JsonProperty("comp-based-score-adjustment-unconditional")
  COMPBASEDSCOREADJUSTMENTUNCONDITIONAL("comp-based-score-adjustment-unconditional");

  public final String name;

  BlastXCompBasedStats(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
