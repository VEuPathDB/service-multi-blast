package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InputBlastpTask {
  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastp-short")
  BLASTPSHORT("blastp-short"),

  @JsonProperty("blastp-fast")
  BLASTPFAST("blastp-fast");

  public final String name;

  InputBlastpTask(String name) {
    this.name = name;
  }
}
