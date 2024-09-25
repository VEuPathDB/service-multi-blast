package mb.lib.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode

enum class JobStatus(val value: String) {
  Queued     ("queued"),
  InProgress ("in-progress"),
  Errored    ("errored"),
  Completed  ("completed"),
  Expired    ("expired");

  @JsonValue
  fun toJSON(): JsonNode = TextNode(value)

  companion object {
    private const val InProgressAlias1 = "grabbed"
    private const val InProgressAlias2 = "running"
    private const val QueuedAlias      = "claimed"

    @JvmStatic
    fun fromString(name: String): JobStatus? {
      for (e in values())
        if (e.value == name)
          return e

      return when (name) {
        QueuedAlias      -> Queued
        InProgressAlias1 -> InProgress
        InProgressAlias2 -> InProgress
        else             -> null
      }
    }

    @JvmStatic
    @JsonCreator
    fun unsafeFromString(name: String): JobStatus =
      fromString(name) ?: throw IllegalArgumentException("Unrecognized JobStatus value: $name")
  }
}
