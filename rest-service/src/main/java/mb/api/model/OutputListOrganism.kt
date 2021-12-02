package mb.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys

data class OutputListOrganism(
  @JsonProperty(JsonKeys.Name)
  var name: String?,

  @JsonProperty(JsonKeys.Blast)
  var blast: List<String>?
)