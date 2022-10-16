package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "config",
    "query"
})
public class JobsPostMultipartFormDataImpl implements JobsPostMultipartFormData {
  @JsonProperty("config")
  private QueryJobRequest config;

  @JsonProperty("query")
  private File query;

  @JsonProperty("config")
  public QueryJobRequest getConfig() {
    return this.config;
  }

  @JsonProperty("config")
  public void setConfig(QueryJobRequest config) {
    this.config = config;
  }

  @JsonProperty("query")
  public File getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(File query) {
    this.query = query;
  }
}
