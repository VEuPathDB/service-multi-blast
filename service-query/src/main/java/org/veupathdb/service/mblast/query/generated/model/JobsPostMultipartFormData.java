package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.File;

@JsonDeserialize(
    as = JobsPostMultipartFormDataImpl.class
)
public interface JobsPostMultipartFormData {
  @JsonProperty("config")
  QueryJobPostRequest getConfig();

  @JsonProperty("config")
  void setConfig(QueryJobPostRequest config);

  @JsonProperty("query")
  File getQuery();

  @JsonProperty("query")
  void setQuery(File query);
}
