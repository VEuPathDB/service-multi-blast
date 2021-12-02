package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSetter
import mb.lib.model.JobStatus

@JsonIgnoreProperties(ignoreUnknown = true)
data class JobStatusResponse(
  @get:JsonIgnore
  @set:JsonSetter("status")
  var status: JobStatus
)