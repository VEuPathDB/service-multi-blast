package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastStrand {
  Both,
  Minus,
  Plus,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      Both  -> "both"
      Minus -> "minus"
      Plus  -> "plus"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastStrand {
      js.dEnumRequireTextual(BlastStrand::class)

      return when (val v = js.textValue()) {
        "both"  -> Both
        "minus" -> Minus
        "plus"  -> Plus
        else    -> throw UnrecognizedEnumException(BlastStrand::class, v)
      }
    }
  }
}