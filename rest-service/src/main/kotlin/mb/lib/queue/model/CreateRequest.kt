package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.fireworq.JobRequest
import org.veupathdb.lib.jackson.Json

open class CreateRequest<T>(val host: String, val payload: T) {
  @JsonValue
  fun toJSON(): JsonNode = Json.newObject {
    put("url", host)
    putPOJO("payload", payload)
  }

  fun toInternal() = JobRequest(host, payload)
}
