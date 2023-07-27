package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastPTask {
  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastp-fast")
  BLASTPFAST("blastp-fast"),

  @JsonProperty("blastp-Short")
  BLASTPSHORT("blastp-Short");

  public final String value;

  public String getValue() {
    return this.value;
  }

  BlastPTask(String name) {
    this.value = name;
  }
}
