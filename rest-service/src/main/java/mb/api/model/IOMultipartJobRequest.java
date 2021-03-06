package mb.api.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = IOMultipartJobRequestImpl.class)
public interface IOMultipartJobRequest
{
  @JsonProperty(JsonKeys.Query)
  File getQuery();

  @JsonProperty(JsonKeys.Query)
  void setQuery(File query);

  @JsonProperty(JsonKeys.Properties)
  IOJsonJobRequest getProperties();

  @JsonProperty(JsonKeys.Properties)
  void setProperties(IOJsonJobRequest properties);
}
