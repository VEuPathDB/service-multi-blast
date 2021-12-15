package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys
import java.io.File

@JsonInclude(NON_NULL)
data class IOMultipartJobRequest(
  @JsonProperty(JsonKeys.Query)
  var query: File? = null,

  @JsonProperty(JsonKeys.Properties)
  var properties: IOJsonJobRequest? = null,
)