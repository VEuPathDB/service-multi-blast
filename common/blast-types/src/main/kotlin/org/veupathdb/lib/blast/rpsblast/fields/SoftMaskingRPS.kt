package org.veupathdb.lib.blast.rpsblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSoftMasking
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseSoftMaskingRPS(js: ObjectNode) =
  js.optBool(FlagSoftMasking) { SoftMaskingRPS(it) } ?: SoftMaskingRPS()


/**
 * -soft_masking <Boolean>
 *
 * Apply filtering locations as soft masks
 *
 * Default = `false`
 */
@JvmInline
value class SoftMaskingRPS(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagSoftMasking

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSoftMasking, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSoftMasking, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSoftMasking, value)
}