package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class DeltaBlastCompBasedStats {
  None,
  CompBasedStats,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      None                                  -> "none"
      CompBasedStats                        -> "comp-based-stats"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): DeltaBlastCompBasedStats {
      js.dEnumRequireTextual(DeltaBlastCompBasedStats::class)
      return when (val v = js.textValue()) {
        "none"                                      -> None
        "comp-based-stats"                          -> CompBasedStats
        else                                        -> throw UnrecognizedEnumException(DeltaBlastCompBasedStats::class, v)
      }
    }
  }
}