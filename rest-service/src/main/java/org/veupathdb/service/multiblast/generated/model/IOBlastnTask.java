package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastnTask {
  @JsonProperty("megablast")
  MEGABLAST("megablast"),

  @JsonProperty("dc-megablast")
  DCMEGABLAST("dc-megablast"),

  @JsonProperty("blastn")
  BLASTN("blastn"),

  @JsonProperty("blastn-short")
  BLASTNSHORT("blastn-short");

  public final String name;

  IOBlastnTask(String name) {
    this.name = name;
  }
}
