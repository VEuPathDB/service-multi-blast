package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RPSBlastCompBasedStats {
  @JsonProperty("simplified")
  SIMPLIFIED("simplified"),

  @JsonProperty("comp-based-stats")
  COMPBASEDSTATS("comp-based-stats");

  public final String value;

  public String getValue() {
    return this.value;
  }

  RPSBlastCompBasedStats(String name) {
    this.value = name;
  }
}
