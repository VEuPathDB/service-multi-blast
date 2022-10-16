package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "size"
})
public class FileEntryImpl implements FileEntry {
  @JsonProperty("name")
  private String name;

  @JsonProperty("size")
  private Integer size;

  @JsonProperty("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("size")
  public Integer getSize() {
    return this.size;
  }

  @JsonProperty("size")
  public void setSize(Integer size) {
    this.size = size;
  }
}
