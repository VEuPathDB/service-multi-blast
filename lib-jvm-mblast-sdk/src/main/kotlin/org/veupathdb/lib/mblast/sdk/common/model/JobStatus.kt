package org.veupathdb.lib.mblast.sdk.common.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import java.lang.IllegalArgumentException

enum class JobStatus {
  Queued,
  InProgress,
  Complete,
  Failed,
  Expired,
  ;

  @JsonValue
  fun toJson() =
    when (this) {
      Queued     -> TextNode("queued")
      InProgress -> TextNode("in-progress")
      Complete   -> TextNode("complete")
      Failed     -> TextNode("failed")
      Expired    -> TextNode("expired")
    }

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): JobStatus {
      if (!js.isTextual)
        throw IllegalArgumentException()

      return when (js.textValue()) {
        "queued"      -> Queued
        "in-progress" -> InProgress
        "complete"    -> Complete
        "failed"      -> Failed
        "expired"     -> Expired
        else          -> throw IllegalArgumentException()
      }
    }
  }
}