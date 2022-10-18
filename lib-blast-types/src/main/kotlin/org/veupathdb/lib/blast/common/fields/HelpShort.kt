package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagHelpShort
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseHelpShort(js: ObjectNode) =
  js.optBool(FlagHelpShort) { HelpShort(it) } ?: HelpShort()


/**
 * -h
 *
 * Print USAGE and DESCRIPTION;  ignore all other parameters.
 */
@JvmInline
value class HelpShort(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagHelpShort

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagHelpShort, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagHelpShort)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagHelpShort)
}
