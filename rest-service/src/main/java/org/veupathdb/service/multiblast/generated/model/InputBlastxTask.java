package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastxTask {
  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("blastx-fast")
  BLASTXFAST("blastx-fast");

  public final String name;

  InputBlastxTask(String name) {
    this.name = name;
  }
}
