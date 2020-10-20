package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "file",
    "properties"
})
public class NewJobPostRequestMultipartImpl implements NewJobPostRequestMultipart {
  @JsonProperty("file")
  private File file;

  @JsonProperty("properties")
  private NewJobPostRequestJSON properties;

  @JsonProperty("file")
  public File getFile() {
    return this.file;
  }

  @JsonProperty("file")
  public void setFile(File file) {
    this.file = file;
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
