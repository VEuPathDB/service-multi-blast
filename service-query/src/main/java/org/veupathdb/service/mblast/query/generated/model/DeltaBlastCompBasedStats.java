package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeltaBlastCompBasedStats {
  @JsonProperty("none")
  NONE("none"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  public final String value;

  public String getValue() {
    return this.value;
  }

  DeltaBlastCompBasedStats(String name) {
    this.value = name;
  }
}
