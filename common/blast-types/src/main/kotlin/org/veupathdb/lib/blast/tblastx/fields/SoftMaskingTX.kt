package org.veupathdb.lib.blast.tblastx.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.common.FlagSoftMasking
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*

/**
 * Default Value
 */
private const val Def = false


/**
 * Parses a [SoftMaskingTX] value from the [FlagSoftMasking] property set on the
 * given JSON [ObjectNode].
 *
 * If no such property exists, returns a defaulted instance.
 *
 * @param js JSON [ObjectNode] from which to parse the [SoftMaskingTX] value (if
 * present).
 *
 * @return A new [SoftMaskingTX] instance.
 */
internal fun ParseSoftMaskingTX(js: ObjectNode) =
  js.optBool(FlagSoftMasking) { SoftMaskingTX(it) } ?: SoftMaskingTX()


/**
 * -soft_masking `<Boolean>`
 *
 * Apply filtering locations as soft masks
 *
 * Default = `false`
 */
@JvmInline
value class SoftMaskingTX(val value: Boolean = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagSoftMasking

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSoftMasking, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSoftMasking, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSoftMasking, value)
}