package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.File;

@JsonDeserialize(
    as = JobsPostMultipartFormDataImpl.class
)
public interface JobsPostMultipartFormData {
  @JsonProperty("config")
  QueryJobRequest getConfig();

  @JsonProperty("config")
  void setConfig(QueryJobRequest config);

  @JsonProperty("query")
  File getQuery();

  @JsonProperty("query")
  void setQuery(File query);
}
