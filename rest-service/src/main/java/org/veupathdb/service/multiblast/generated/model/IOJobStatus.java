package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IOJobStatus {
  @JsonProperty("queued")
  QUEUED("queued"),

  @JsonProperty("in-progress")
  INPROGRESS("in-progress"),

  @JsonProperty("completed")
  COMPLETED("completed"),

  @JsonProperty("errored")
  ERRORED("errored");

  public final String name;

  IOJobStatus(String name) {
    this.name = name;
  }
}
