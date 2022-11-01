package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class PSIBlastCompBasedStats {
  None,
  CompBasedStats,
  CompBasedScoreAdjustmentConditional,
  CompBasedScoreAdjustmentUnconditional,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      None                                  -> "none"
      CompBasedStats                        -> "comp-based-stats"
      CompBasedScoreAdjustmentConditional   -> "comp-based-score-adjustment-conditional"
      CompBasedScoreAdjustmentUnconditional -> "comp-based-score-adjustment-unconditional"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): PSIBlastCompBasedStats {
      js.dEnumRequireTextual(PSIBlastCompBasedStats::class)
      return when (val v = js.textValue()) {
        "none"                                      -> None
        "comp-based-stats"                          -> CompBasedStats
        "comp-based-score-adjustment-conditional"   -> CompBasedScoreAdjustmentConditional
        "comp-based-score-adjustment-unconditional" -> CompBasedScoreAdjustmentUnconditional
        else                                        -> throw UnrecognizedEnumException(PSIBlastCompBasedStats::class, v)
      }
    }
  }

}