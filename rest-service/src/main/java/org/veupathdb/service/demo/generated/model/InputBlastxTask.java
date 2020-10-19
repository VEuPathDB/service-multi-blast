package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastxTask {
  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("blastx-fast")
  BLASTXFAST("blastx-fast");

  private String name;

  InputBlastxTask(String name) {
    this.name = name;
  }
}
