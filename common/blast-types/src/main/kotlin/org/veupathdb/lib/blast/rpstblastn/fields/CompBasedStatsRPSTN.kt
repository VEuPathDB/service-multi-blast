package org.veupathdb.lib.blast.rpstblastn.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCompBasedStats
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseCompBasedStatsRPSTN(js: ObjectNode) =
  js[FlagCompBasedStats]?.let { CompBasedStatsRPSTN(parseEnum(it)) }
    ?: CompBasedStatsRPSTN()


/**
 * -comp_based_stats `<String>`
 *
 * Use composition-based statistics:
 * * `D` or `d`: default (equivalent to 1)
 * * `0` or `F` or `f`: No composition-based statistics
 * * `1` or `T` or `t`: Composition-based statistics as in NAR 29:2994-3005,
 *   2001
 *
 * Default = `1`
 */
@JvmInline
value class CompBasedStatsRPSTN(
  val value: CompBasedStatsRPSTNValue = CompBasedStatsRPSTNValue.CompBasedStats
) : BlastField {
  override val isDefault
    get() = value == CompBasedStatsRPSTNValue.CompBasedStats

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
private inline fun parseEnum(js: JsonNode): CompBasedStatsRPSTNValue {
  if (js.isTextual)
    return when(val v = js.textValue()) {
      "D", "d"      -> CompBasedStatsRPSTNValue.CompBasedStats
      "0", "F", "f" -> CompBasedStatsRPSTNValue.NoCompBasedStats
      "1", "T", "t" -> CompBasedStatsRPSTNValue.CompBasedStats
      else          -> throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $v")
    }

  if (js.isIntegralNumber)
    return when(val v = js.intValue()) {
      0    -> CompBasedStatsRPSTNValue.NoCompBasedStats
      1    -> CompBasedStatsRPSTNValue.CompBasedStats
      else -> throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $v")
    }

  throw IllegalArgumentException("Invalid $FlagCompBasedStats value: $js")
}


enum class CompBasedStatsRPSTNValue {
  NoCompBasedStats,
  CompBasedStats;

  inline val value get() = ordinal
}