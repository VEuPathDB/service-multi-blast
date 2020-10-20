package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.File;

@JsonDeserialize(
    as = NewJobPostRequestMultipartImpl.class
)
public interface NewJobPostRequestMultipart {
  @JsonProperty("file")
  File getFile();

  @JsonProperty("file")
  void setFile(File file);

  @JsonProperty("properties")
  NewJobPostRequestJSON getProperties();

  @JsonProperty("properties")
  void setProperties(NewJobPostRequestJSON properties);
}
