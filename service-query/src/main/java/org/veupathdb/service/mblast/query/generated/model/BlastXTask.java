package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastXTask {
  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("blastx-fast")
  BLASTXFAST("blastx-fast");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastXTask(String name) {
    this.value = name;
  }
}
