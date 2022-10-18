package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = QueryJobTargetImpl.class
)
public interface QueryJobTarget {
  @JsonProperty("targetDisplayName")
  String getTargetDisplayName();

  @JsonProperty("targetDisplayName")
  void setTargetDisplayName(String targetDisplayName);

  @JsonProperty("targetFile")
  String getTargetFile();

  @JsonProperty("targetFile")
  void setTargetFile(String targetFile);
}
