package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOBlastpTask {
  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastp-short")
  BLASTPSHORT("blastp-short"),

  @JsonProperty("blastp-fast")
  BLASTPFAST("blastp-fast");

  public final String name;

  IOBlastpTask(String name) {
    this.name = name;
  }
}
