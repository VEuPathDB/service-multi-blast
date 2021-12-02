package mb.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys

data class IOJobTarget(
  @JsonProperty(JsonKeys.Organism) val organism: String,
  @JsonProperty(JsonKeys.Target) val target: String
)