package mb.api.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.blast.IOBlastConfig;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOJsonJobRequestImpl.class)
public interface IOJsonJobRequest
{
  @JsonProperty(JsonKeys.Site)
  String getSite();

  @JsonProperty(JsonKeys.Site)
  void setSite(String site);

  @JsonProperty(JsonKeys.Targets)
  Set<IOJobTarget> getTargets();

  @JsonProperty(JsonKeys.Targets)
  void setTargets(Set<IOJobTarget> targets);

  @JsonProperty(JsonKeys.Config)
  IOBlastConfig getConfig();

  @JsonProperty(JsonKeys.Config)
  void setConfig(IOBlastConfig config);

  @JsonProperty(JsonKeys.Description)
  String getDescription();

  @JsonProperty(JsonKeys.Description)
  void setDescription(String description);

  @JsonProperty(JsonKeys.MaxResults)
  Integer getMaxResults();

  @JsonProperty(JsonKeys.MaxResults)
  void setMaxResults(Integer maxResults);

  @JsonProperty(JsonKeys.MaxResultSize)
  Long getMaxResultSize();

  @JsonProperty(JsonKeys.MaxResultSize)
  void setMaxResultSize(Long maxSize);

  @JsonProperty(JsonKeys.MaxSequences)
  Byte getMaxSequences();

  @JsonProperty(JsonKeys.MaxSequences)
  void setMaxSequences(Byte b);

  @JsonProperty(JsonKeys.IsPrimary)
  boolean getIsPrimary();

  @JsonProperty(JsonKeys.IsPrimary)
  void setIsPrimary(boolean val);
}
