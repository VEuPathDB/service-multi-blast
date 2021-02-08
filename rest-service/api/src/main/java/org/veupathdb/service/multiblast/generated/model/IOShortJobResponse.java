package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOShortJobResponseImpl.class)
public interface IOShortJobResponse
{
  @JsonProperty("id")
  String getId();

  @JsonProperty("id")
  IOShortJobResponse setId(String id);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("description")
  IOShortJobResponse setDescription(String description);

  @JsonProperty("status")
  IOJobStatus getStatus();

  @JsonProperty("status")
  IOShortJobResponse setStatus(IOJobStatus status);

  @JsonProperty(JsonKeys.Created)
  String getCreated();

  @JsonProperty(JsonKeys.Created)
  IOShortJobResponse setCreated(String date);

  @JsonProperty(JsonKeys.Expires)
  String getExpires();

  @JsonProperty(JsonKeys.Expires)
  IOShortJobResponse setExpires(String date);

  @JsonProperty(JsonKeys.MaxResultSize)
  Long getMaxResultSize();

  @JsonProperty(JsonKeys.MaxResultSize)
  IOShortJobResponse setMaxResultSize(Long val);
}
