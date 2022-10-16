package org.veupathdb.lib.blast.rpsblast.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCompBasedStats
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseCompBasedStatsRPS(js: ObjectNode) =
  js[FlagCompBasedStats]?.let { CompBasedStatsRPS(parseEnum(it)) }
    ?: CompBasedStatsRPS()


/**
 * -comp_based_stats <String>
 *
 * Use composition-based statistics:
 * * D or d: default (equivalent to 1)
 * * 0 or F or f: Simplified Composition-based statistics as in Bioinformatics
 *   15:1000-1011, 1999
 * * 1 or T or t: Composition-based statistics as in NAR 29:2994-3005, 2001
 *
 * Default = `1`
 */
@JvmInline
value class CompBasedStatsRPS(
  val value: CompBasedStatsRPSValue = CompBasedStatsRPSValue.CompBasedStats
) : BlastField {
  override val isDefault
    get() = value == CompBasedStatsRPSValue.CompBasedStats

  override val name: String
    get() = FlagCompBasedStats

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagCompBasedStats, value.value)
}


@Suppress("NOTHING_TO_INLINE")
private inline fun parseEnum(js: JsonNode): CompBasedStatsRPSValue {
  if (js.isTextual)
    return when(val v = js.textValue()) {
      "D", "d"      -> CompBasedStatsRPSValue.CompBasedStats
      "0", "F", "f" -> CompBasedStatsRPSValue.SimplifiedCompBasedStats
      "1", "T", "t" -> CompBasedStatsRPSValue.CompBasedStats
      else          -> throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $v")
    }

  if (js.isIntegralNumber)
    return when(val v = js.intValue()) {
      0    -> CompBasedStatsRPSValue.SimplifiedCompBasedStats
      1    -> CompBasedStatsRPSValue.CompBasedStats
      else -> throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $v")
    }

  throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $js")
}


enum class CompBasedStatsRPSValue {
  SimplifiedCompBasedStats,
  CompBasedStats;

  inline val value get() = ordinal
}