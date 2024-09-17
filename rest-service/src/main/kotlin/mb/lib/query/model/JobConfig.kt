package mb.lib.query.model

import com.fasterxml.jackson.databind.JsonNode

sealed interface JobConfig {
  fun toJson(): JsonNode
}
