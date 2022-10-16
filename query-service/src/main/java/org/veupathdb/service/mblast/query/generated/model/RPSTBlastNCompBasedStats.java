package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RPSTBlastNCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  public final String name;

  RPSTBlastNCompBasedStats(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
