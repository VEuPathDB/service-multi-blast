package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagParseDefLines
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseParseDefLines(js: ObjectNode) =
  js.optBool(FlagParseDefLines) { ParseDefLines(it) } ?: ParseDefLines()


/**
 * -parse_deflines
 *
 * Should the query and subject defline(s) be parsed?
 */
@JvmInline
value class ParseDefLines(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagParseDefLines

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagParseDefLines, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagParseDefLines)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagParseDefLines)
}