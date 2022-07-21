package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys

@JsonInclude(NON_NULL)
data class IOJobLink(@JsonProperty(JsonKeys.ID) var id: String, @JsonProperty(JsonKeys.Index) var index: Int)