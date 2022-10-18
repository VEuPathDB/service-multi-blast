package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagGapTrigger
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


/**
 * Default Value
 */
private const val Def = 22.0


internal fun ParseGapTrigger(js: ObjectNode) =
  js.optDub(FlagGapTrigger) { GapTrigger(it) } ?: GapTrigger()


/**
 * -gap_trigger `<Real>`
 *
 * Number of bits to trigger gapping
 *
 * Default = `22`
 */
@JvmInline
value class GapTrigger(val value: Double = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagGapTrigger

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagGapTrigger, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagGapTrigger, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagGapTrigger, value)
}