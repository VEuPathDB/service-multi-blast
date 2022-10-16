package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSumStats
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = false


internal fun ParseSumStats(js: ObjectNode) =
  js.optBool(FlagSumStats) { SumStats(it) } ?: SumStats()


/**
 * -sum_stats `<Boolean>`
 *
 * Use sum statistics
 */
@JvmInline
value class SumStats(val value: Boolean = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagSumStats

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSumStats, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSumStats, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSumStats, value)
}