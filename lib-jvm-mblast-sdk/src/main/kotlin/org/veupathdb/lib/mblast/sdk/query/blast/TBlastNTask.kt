package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class TBlastNTask {
  TBlastN,
  TBlastNFast,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      TBlastN     -> "tblastn"
      TBlastNFast -> "tblastn-fast"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): TBlastNTask {
      js.dEnumRequireTextual(TBlastNTask::class)
      return when (val v = js.textValue()) {
        "tblastn"      -> TBlastN
        "tblastn-fast" -> TBlastNFast
        else           -> throw UnrecognizedEnumException(TBlastNTask::class, v)
      }
    }
  }
}