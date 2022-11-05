package org.veupathdb.lib.mblast.sdk.report.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.NonStringEnumException
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException

/**
 * Hit Sorting
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
enum class BlastHitSorting {

  ByEValue,
  ByBitScore,
  ByTotalScore,
  ByPercentIdentity,
  ByQueryCoverage,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      ByEValue          -> "by-evalue"
      ByBitScore        -> "by-bit-score"
      ByTotalScore      -> "by-total-score"
      ByPercentIdentity -> "by-percent-identity"
      ByQueryCoverage   -> "by-query-coverage"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastHitSorting {
      if (!js.isTextual)
        throw NonStringEnumException(BlastHitSorting::class)

      return when (val v = js.textValue()) {
        "by-evalue"           -> ByEValue
        "by-bit-score"        -> ByBitScore
        "by-total-score"      -> ByTotalScore
        "by-percent-identity" -> ByPercentIdentity
        "by-query-coverage"   -> ByQueryCoverage
        else                  -> throw UnrecognizedEnumException(BlastHitSorting::class, v)
      }
    }
  }
}