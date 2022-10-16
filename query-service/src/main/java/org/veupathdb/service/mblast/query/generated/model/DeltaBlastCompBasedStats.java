package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeltaBlastCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  public final String name;

  DeltaBlastCompBasedStats(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
