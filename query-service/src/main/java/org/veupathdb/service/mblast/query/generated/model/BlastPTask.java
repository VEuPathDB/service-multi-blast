package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BlastPTask {
  @JsonProperty("blastp")
  BLASTP("blastp"),

  @JsonProperty("blastp-fast")
  BLASTPFAST("blastp-fast"),

  @JsonProperty("blastp-Short")
  BLASTPSHORT("blastp-Short");

  public final String name;

  BlastPTask(String name) {
    this.name = name;
  }


  public String getName() {
    return this.name;
  }
}
