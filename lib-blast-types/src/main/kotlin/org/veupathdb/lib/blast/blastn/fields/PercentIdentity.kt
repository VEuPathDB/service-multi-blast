package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagPercentIdentity
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optDub
import org.veupathdb.lib.blast.util.put


private const val Def = -1.0


internal fun ParsePercentIdentity(js: ObjectNode) =
  js.optDub(FlagPercentIdentity) { PercentIdentity(it) } ?: PercentIdentity()


/**
 * -perc_identity `<Real, 0..100>`
 *
 * Percent identity
 */
@JvmInline
value class PercentIdentity(val value: Double = Def)
  : BlastField
{
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagPercentIdentity

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagPercentIdentity, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagPercentIdentity, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagPercentIdentity, value)

  override fun clone() = this
}