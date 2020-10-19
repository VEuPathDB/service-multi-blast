package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobStatus {
  @JsonProperty("queued")
  QUEUED("queued"),

  @JsonProperty("in-progress")
  INPROGRESS("in-progress"),

  @JsonProperty("completed")
  COMPLETED("completed"),

  @JsonProperty("errored")
  ERRORED("errored");

  private String name;

  JobStatus(String name) {
    this.name = name;
  }
}
