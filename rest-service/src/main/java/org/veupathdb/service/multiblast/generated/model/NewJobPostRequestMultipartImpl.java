package org.veupathdb.service.multiblast.generated.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "query",
    "properties"
})
public class NewJobPostRequestMultipartImpl implements NewJobPostRequestMultipart {
  @JsonProperty("query")
  private File query;

  @JsonProperty("properties")
  private NewJobPostRequestJSON properties;

  @JsonProperty("query")
  public File getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(File query) {
    this.query = query;
  }

  @JsonProperty("properties")
  public NewJobPostRequestJSON getProperties() {
    return this.properties;
  }

  @JsonProperty("properties")
  public void setProperties(NewJobPostRequestJSON properties) {
    this.properties = properties;
  }
}
