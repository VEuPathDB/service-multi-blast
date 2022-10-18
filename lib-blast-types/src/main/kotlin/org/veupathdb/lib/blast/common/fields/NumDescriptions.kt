package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNumDescriptions
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val DefaultNumDescriptions = 500u


internal fun ParseNumDescriptions(js: ObjectNode) =
  js.optUInt(FlagNumDescriptions) { NumDescriptions(it) } ?: NumDescriptions()


/**
 * -num_descriptions `<Integer, >=0>`
 *
 * Number of database sequences to show one-line descriptions for
 *
 * Not applicable for outfmt > 4
 *
 * Default = `500`
 */
@JvmInline
value class NumDescriptions(val value: UInt = DefaultNumDescriptions)
  : BlastField
{
  override val isDefault get() = value == DefaultNumDescriptions

  override val name: String
    get() = FlagNumDescriptions

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNumDescriptions, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNumDescriptions, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNumDescriptions, value)
}