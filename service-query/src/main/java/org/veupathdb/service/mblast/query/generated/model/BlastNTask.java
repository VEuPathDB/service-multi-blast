package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastNTask {
  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastn-Short")
  BLASTNSHORT("blastn-Short"),

  @JsonProperty("dc-megablast")
  DCMEGABLAST("dc-megablast"),

  @JsonProperty("megablast")
  MEGABLAST("megablast"),

  @JsonProperty("rmblastn")
  RMBLASTN("rmblastn");

  private final String value;

  public String getValue() {
    return this.value;
  }

  BlastNTask(String name) {
    this.value = name;
  }
}
