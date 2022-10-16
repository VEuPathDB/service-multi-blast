package org.veupathdb.lib.blast.blastx.field

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCompBasedStats
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


private val Def = CompBasedStatsXValue.ConditionalScoreAdjustment


internal fun ParseCompBasedStatsX(js: ObjectNode) =
  js[FlagCompBasedStats]?.let { CompBasedStatsX(parseEnum(it)) } ?: CompBasedStatsX()


/**
 * -comp_based_stats `<String>`
 *
 * Use composition-based statistics:
 * * `D` or `d`: default (equivalent to 2)
 * * `0` or `F` or `f`: No composition-based statistics
 * * `1`: Composition-based statistics as in NAR 29:2994-3005, 2001
 * * `2` or `T` or `t` : Composition-based score adjustment as in Bioinformatics
 *   21:902-911, 2005, conditioned on sequence properties
 * * `3`: Composition-based score adjustment as in Bioinformatics 21:902-911,
 *   2005, unconditionally
 *
 * Default = `2`
 */
@JvmInline
value class CompBasedStatsX(val value: CompBasedStatsXValue = Def)
  : BlastField
{
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagCompBasedStats

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagCompBasedStats, value.value)
}


private fun parseEnum(j: JsonNode): CompBasedStatsXValue {
  if (j.isTextual)
    return when (val v = j.textValue()) {
      "d", "D"      -> CompBasedStatsXValue.ConditionalScoreAdjustment
      "0", "f", "F" -> CompBasedStatsXValue.None
      "1"           -> CompBasedStatsXValue.Statistics
      "2", "t", "T" -> CompBasedStatsXValue.ConditionalScoreAdjustment
      "3"           -> CompBasedStatsXValue.UnconditionalScoreAdjustment
      else          -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  if (j.isIntegralNumber)
    return when (val v = j.intValue()) {
      0    -> CompBasedStatsXValue.None
      1    -> CompBasedStatsXValue.Statistics
      2    -> CompBasedStatsXValue.ConditionalScoreAdjustment
      3    -> CompBasedStatsXValue.UnconditionalScoreAdjustment
      else -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  throw IllegalArgumentException("$FlagCompBasedStats must be a string or int value.")
}


enum class CompBasedStatsXValue {
  None,
  Statistics,
  ConditionalScoreAdjustment,
  UnconditionalScoreAdjustment;

  val value
    get() = when (this) {
      None                         -> 0
      Statistics                   -> 1
      ConditionalScoreAdjustment   -> 2
      UnconditionalScoreAdjustment -> 3
    }
}