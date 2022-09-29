package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastNTask {
  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastn-short")
  BLASTNSHORT("blastn-short"),

  @JsonProperty("dc-megablast")
  DCMEGABLAST("dc-megablast"),

  @JsonProperty("megablast")
  MEGABLAST("megablast"),

  @JsonProperty("rmblastn")
  RMBLASTN("rmblastn");

  public final String name;

  BlastNTask(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
