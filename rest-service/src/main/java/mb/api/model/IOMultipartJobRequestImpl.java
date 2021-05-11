package mb.api.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOMultipartJobRequestImpl implements IOMultipartJobRequest
{
  private File             query;
  private IOJsonJobRequest properties;

  @JsonProperty("query")
  public File getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(File query) {
    this.query = query;
  }

  @JsonProperty("properties")
  public IOJsonJobRequest getProperties() {
    return this.properties;
  }

  @JsonProperty("properties")
  public void setProperties(IOJsonJobRequest properties) {
    this.properties = properties;
  }
}
