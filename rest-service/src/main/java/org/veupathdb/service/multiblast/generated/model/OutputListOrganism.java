package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(
    as = OutputListOrganismImpl.class
)
public interface OutputListOrganism {
  @JsonProperty("name")
  String getName();

  @JsonProperty("name")
  void setName(String name);

  @JsonProperty("blast")
  List<String> getBlast();

  @JsonProperty("blast")
  void setBlast(List<String> blast);
}
