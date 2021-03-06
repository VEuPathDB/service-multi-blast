package mb.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.io.JsonKeys;

@JsonDeserialize(as = OutputListOrganismImpl.class)
public interface OutputListOrganism {
  @JsonProperty(JsonKeys.Name)
  String getName();

  @JsonProperty(JsonKeys.Name)
  void setName(String name);

  @JsonProperty(JsonKeys.Blast)
  List<String> getBlast();

  @JsonProperty(JsonKeys.Blast)
  void setBlast(List<String> blast);
}
