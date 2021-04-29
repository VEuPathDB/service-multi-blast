package mb.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;
import mb.lib.model.JobStatus;

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
  JobStatus getStatus();

  @JsonProperty(JsonKeys.Status)
  IOShortJobResponse setStatus(JobStatus status);

  @JsonProperty(JsonKeys.Created)
  String getCreated();

  @JsonProperty(JsonKeys.Created)
  IOShortJobResponse setCreated(String date);

  @JsonProperty(JsonKeys.Expires)
  String getExpires();

  @JsonSetter(JsonKeys.Expires)
  IOShortJobResponse setExpires(String date);

  @JsonProperty(JsonKeys.MaxResultSize)
  Long getMaxResultSize();

  @JsonSetter(JsonKeys.MaxResultSize)
  IOShortJobResponse setMaxResultSize(Long val);

  @JsonProperty(JsonKeys.ParentJobs)
  IOParentJobLink[] getParentJobs();

  @JsonSetter(JsonKeys.ParentJobs)
  IOShortJobResponse setParentJobs(IOParentJobLink[] parentJobID);

  @JsonProperty(JsonKeys.IsPrimary)
  boolean getIsPrimary();

  @JsonSetter(JsonKeys.IsPrimary)
  IOShortJobResponse setIsPrimary(boolean isPrimary);

  @JsonProperty(JsonKeys.Site)
  String getSite();

  @JsonSetter(JsonKeys.Site)
  IOShortJobResponse setSite(String site);

  @JsonProperty(JsonKeys.Targets)
  IOJobTarget[] getTargets();

  @JsonSetter(JsonKeys.Targets)
  IOShortJobResponse setTargets(IOJobTarget[] targets);

  @JsonGetter(JsonKeys.IsCached)
  boolean isCached();

  @JsonSetter(JsonKeys.IsCached)
  IOShortJobResponse setIsCached(boolean value);
}
