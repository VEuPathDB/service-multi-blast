package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNegativeGIList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseNegGIList(js: ObjectNode) =
  js.optString(FlagNegativeGIList) { NegativeGIList(it) } ?: NegativeGIList()


/**
 * -negative_gilist `<String>`
 *
 * Restrict search of database to everything except the specified GIs
 */
@JvmInline
value class NegativeGIList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagNegativeGIList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNegativeGIList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNegativeGIList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNegativeGIList, file)
}
