package org.veupathdb.lib.mblast.sdk.report.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.NonStringEnumException
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException

/**
 * High Scoring Pair Sorting
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
enum class BlastHSPSorting {
  ByHSPEValue,
  ByHSPScore,
  ByHSPQueryStart,
  ByHSPPercentIdentity,
  ByHSPSubjectStart,
  ;

  @JsonValue
  fun toJson(): JsonNode =
    TextNode(
      when (this) {
        ByHSPEValue          -> "by-hsp-evalue"
        ByHSPScore           -> "by-hsp-score"
        ByHSPQueryStart      -> "by-hsp-query-start"
        ByHSPPercentIdentity -> "by-hsp-percent-identity"
        ByHSPSubjectStart    -> "by-hsp-subject-start"
      }
    )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastHSPSorting {
      if (!js.isTextual)
        throw NonStringEnumException(BlastHSPSorting::class)

      return when (val v = js.textValue()) {
        "by-hsp-evalue"           -> ByHSPEValue
        "by-hsp-score"            -> ByHSPScore
        "by-hsp-query-start"      -> ByHSPQueryStart
        "by-hsp-percent-identity" -> ByHSPPercentIdentity
        "by-hsp-subject-start"    -> ByHSPSubjectStart
        else                      -> throw UnrecognizedEnumException(BlastHSPSorting::class, v)
      }
    }
  }
}