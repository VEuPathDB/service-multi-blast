package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagHTML
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


private const val Def = false


internal fun ParseHTML(js: ObjectNode) =
  js.optBool(FlagHTML) { HTML(it) } ?: HTML()


/**
 * -html
 *
 * Produce HTML output
 */
@JvmInline
value class HTML(val value: Boolean = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagHTML

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagHTML, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagHTML)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagHTML)

  override fun clone() = this
}