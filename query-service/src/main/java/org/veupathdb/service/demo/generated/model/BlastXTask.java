package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastXTask {
  @JsonProperty("blastx")
  BLASTX("blastx"),

  @JsonProperty("blastx-fast")
  BLASTXFAST("blastx-fast");

  public final String name;

  BlastXTask(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
