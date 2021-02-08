package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonDeserialize(
    as = IOJsonJobRequestImpl.class
)
public interface IOJsonJobRequest
{
  @JsonProperty("site")
  String getSite();

  @JsonProperty("site")
  void setSite(String site);

  @JsonProperty("targets")
  IOJobTarget[] getTargets();

  @JsonProperty("targets")
  void setTargets(IOJobTarget[] targets);

  @JsonProperty("config")
  IOBlastConfig getConfig();

  @JsonProperty("config")
  void setConfig(IOBlastConfig config);

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("description")
  void setDescription(String description);

  @JsonProperty(JsonKeys.MaxResults)
  Integer getMaxResults();

  @JsonProperty(JsonKeys.MaxResults)
  void setMaxResults(Integer maxResults);

  @JsonProperty(JsonKeys.MaxResultSize)
  Long getMaxResultSize();

  @JsonProperty(JsonKeys.MaxResultSize)
  void setMaxResultSize(Long maxSize);
}
