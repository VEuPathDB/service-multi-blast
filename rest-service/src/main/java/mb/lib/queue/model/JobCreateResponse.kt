package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSetter

@JsonIgnoreProperties(ignoreUnknown = true)
data class JobCreateResponse(
  @get:JsonGetter("id")
  @set:JsonSetter("id")
  var id: Int = 0
)