package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RPSBlastCompBasedStats {
  @JsonProperty("simplified")
  SIMPLIFIED("simplified"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  public final String name;

  RPSBlastCompBasedStats(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
