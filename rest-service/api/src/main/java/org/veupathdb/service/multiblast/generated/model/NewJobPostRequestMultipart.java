package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.File;

@JsonDeserialize(
    as = NewJobPostRequestMultipartImpl.class
)
public interface NewJobPostRequestMultipart {
  @JsonProperty("query")
  File getQuery();

  @JsonProperty("query")
  void setQuery(File query);

  @JsonProperty("properties")
  NewJobPostRequestJSON getProperties();

  @JsonProperty("properties")
  void setProperties(NewJobPostRequestJSON properties);
}
