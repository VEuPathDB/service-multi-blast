package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastxTask {
  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("blastx-fast")
  BLASTXFAST("blastx-fast");

  public final String name;

  IOBlastxTask(String name) {
    this.name = name;
  }
}
