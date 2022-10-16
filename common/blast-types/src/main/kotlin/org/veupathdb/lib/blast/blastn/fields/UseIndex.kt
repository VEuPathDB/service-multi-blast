package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagUseIndex
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseUseIndex(js: ObjectNode) =
  js.optBool(FlagUseIndex) { UseIndex(it) } ?: UseIndex()


/**
 * -use_index `<Boolean>`
 *
 * Use MegaBLAST database index
 *
 * Default = `false`
 */
@JvmInline
value class UseIndex(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagUseIndex

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagUseIndex, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagUseIndex, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagUseIndex, value)
}