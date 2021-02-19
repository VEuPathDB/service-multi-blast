package org.veupathdb.service.multiblast.generated.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = IOMultipartJobRequestImpl.class
)
public interface IOMultipartJobRequest
{
  @JsonProperty("query")
  File getQuery();

  @JsonProperty("query")
  void setQuery(File query);

  @JsonProperty("properties")
  IOJsonJobRequest getProperties();

  @JsonProperty("properties")
  void setProperties(IOJsonJobRequest properties);
}
