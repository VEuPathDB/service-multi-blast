package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RPSTBlastNCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  private final String value;

  public String getValue() {
    return this.value;
  }

  RPSTBlastNCompBasedStats(String name) {
    this.value = name;
  }
}
