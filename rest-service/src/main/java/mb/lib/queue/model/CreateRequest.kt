package mb.lib.queue.model;

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import mb.lib.util.jsonObject

open class CreateRequest<T>(val host: String, val payload: T) {
  @JsonValue
  fun toJSON(): JsonNode = jsonObject {
    put("url", host)
    putPOJO("payload", payload)
  }
}
