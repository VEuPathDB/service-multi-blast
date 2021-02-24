package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "blast"
})
public class OutputListOrganismImpl implements OutputListOrganism {
  @JsonProperty("name")
  private String name;

  @JsonProperty("blast")
  private List<String> blast;

  @JsonProperty("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("blast")
  public List<String> getBlast() {
    return this.blast;
  }

  @JsonProperty("blast")
  public void setBlast(List<String> blast) {
    this.blast = blast;
  }
}
