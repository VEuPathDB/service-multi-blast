package mb.lib.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode

enum class ReportStatus(val externalValue: String) {
  NeverRun   ("never-run"),
  Queued     ("queued"),
  InProgress ("in-progress"),
  Completed  ("completed"),
  Errored    ("errored"),
  Expired    ("expired");

  @JsonValue
  fun toJSON(): JsonNode = TextNode(externalValue)

  companion object {
    @JsonCreator
    fun fromExternalValue(value: String): ReportStatus {
      for (v in values())
        if (v.externalValue == value)
          return v

      throw IllegalArgumentException("Unrecognized enum value \"$value\" for enum ${ReportStatus::class.simpleName}")
    }
  }
}
