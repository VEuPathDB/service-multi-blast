package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagHelpLong
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseHelpLong(js: ObjectNode) =
  js.optBool(FlagHelpLong) { HelpLong(it) } ?: HelpLong()


/**
 * -help
 *
 * Print USAGE, DESCRIPTION and ARGUMENTS; ignore all other parameters.
 */
@JvmInline
value class HelpLong(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagHelpLong

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagHelpLong, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagHelpLong)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagHelpLong)
}