package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSoftMasking
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


internal fun ParseSoftMaskingPSI(js: ObjectNode) =
  js.optBool(FlagSoftMasking) { SoftMaskingPSI(it) } ?: SoftMaskingPSI()


/**
 * -soft_masking `<Boolean>`
 *
 * Apply filtering locations as soft masks
 *
 * Default = `false`
 */
@JvmInline
value class SoftMaskingPSI(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagSoftMasking

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSoftMasking, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSoftMasking, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSoftMasking, value)

  override fun clone() = this
}