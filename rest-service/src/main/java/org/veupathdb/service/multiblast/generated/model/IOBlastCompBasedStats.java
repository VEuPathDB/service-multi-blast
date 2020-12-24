package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats"),

  @JsonProperty("conditional-comp-based-score-adjustment")
  CONDITIONALCOMPBASEDSCOREADJUSTMENT("conditional-comp-based-score-adjustment"),

  @JsonProperty("unconditional-comp-based-score-adjustment")
  UNCONDITIONALCOMPBASEDSCOREADJUSTMENT("unconditional-comp-based-score-adjustment");

  public final String name;

  IOBlastCompBasedStats(String name) {
    this.name = name;
  }
}
