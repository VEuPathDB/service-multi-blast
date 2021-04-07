package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(as = IOShortJobResponseImpl.class)
public interface IOShortJobResponse
{
  @JsonProperty(JsonKeys.ID)
  String getId();

  @JsonProperty(JsonKeys.ID)
  IOShortJobResponse setId(String id);

  @JsonProperty(JsonKeys.Description)
  String getDescription();

  @JsonProperty(JsonKeys.Description)
  IOShortJobResponse setDescription(String description);

  @JsonProperty(JsonKeys.Status)
  IOJobStatus getStatus();

  @JsonProperty(JsonKeys.Status)
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

  @JsonProperty(JsonKeys.ParentJobs)
  IOParentJobLink[] getParentJobs();

  @JsonProperty(JsonKeys.ParentJobs)
  IOShortJobResponse setParentJobs(IOParentJobLink[] parentJobID);

  @JsonProperty(JsonKeys.IsPrimary)
  boolean getIsPrimary();

  @JsonProperty(JsonKeys.IsPrimary)
  IOShortJobResponse setIsPrimary(boolean isPrimary);

  @JsonProperty(JsonKeys.Site)
  String getSite();

  @JsonProperty(JsonKeys.Site)
  IOShortJobResponse setSite(String site);

  @JsonProperty(JsonKeys.Targets)
  IOJobTarget[] getTargets();

  @JsonProperty(JsonKeys.Targets)
  IOShortJobResponse setTargets(IOJobTarget[] targets);

  @JsonGetter(JsonKeys.IsCached)
  boolean isCached();

  @JsonSetter(JsonKeys.IsCached)
  IOShortJobResponse setIsCached(boolean value);
}
