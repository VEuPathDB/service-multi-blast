package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode

@JsonIgnoreProperties(ignoreUnknown = true)
interface FailedJobResponse<T: FailedJob<*>>
{
  val failedJobs: List<T>

  @JsonValue
  fun toJSON(): JsonNode
}
