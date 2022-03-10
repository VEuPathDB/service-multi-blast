package mb.lib.blast.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import mb.api.service.util.ErrorText
import org.veupathdb.lib.blast.field.CompBasedStatsLong
import org.veupathdb.lib.blast.field.CompBasedStatsShort
import java.lang.IllegalStateException

enum class CompositionBasedStats(val publicValue: String) {
  None("none"),
  CompBasedStats("comp-based-stats"),
  ConditionalScoreAdjustment("conditional-comp-based-score-adjustment"),
  UnconditionalScoreAdjustment("unconditional-comp-based-score-adjustment"),
  ;

  @JsonValue
  fun toJSON(): JsonNode = TextNode(publicValue)

  fun toInternalLong() = when(this) {
    None                         -> CompBasedStatsLong.None
    CompBasedStats               -> CompBasedStatsLong.Statistics
    ConditionalScoreAdjustment   -> CompBasedStatsLong.ScoreAdjustment
    UnconditionalScoreAdjustment -> CompBasedStatsLong.UnconditionalScoreAdjustment
  }

  override fun toString() = ordinal.toString()


  companion object {


    @JvmStatic
    fun fromValue(value: CompBasedStatsLong) = when (value) {
      CompBasedStatsLong.ScoreAdjustment              -> ConditionalScoreAdjustment
      CompBasedStatsLong.None                         -> None
      CompBasedStatsLong.Statistics                   -> CompBasedStats
      CompBasedStatsLong.UnconditionalScoreAdjustment -> UnconditionalScoreAdjustment
    }

    @JvmStatic
    @JsonCreator
    fun fromValue(value: String): CompositionBasedStats {
      val tmp = value.lowercase()

      for (v in values())
        if (v.publicValue == tmp)
          return v

      return when (tmp) {
        "0", "f", "false"     -> None
        "1"                   -> CompBasedStats
        "d", "2", "t", "true" -> ConditionalScoreAdjustment
        "3"                   -> UnconditionalScoreAdjustment
        else                  -> throw IllegalArgumentException(String.format(
          ErrorText.InvalidEnumValue,
          value,
          CompositionBasedStats::class.simpleName))
      }
    }
  }
}
