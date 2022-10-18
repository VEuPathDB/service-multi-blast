package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagGIList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseGIList(js: ObjectNode) =
  js.optString(FlagGIList) { GIList(it) } ?: GIList()


/**
 * -gilist `<String>`
 *
 * Restrict search of database to list of GIs
 */
@JvmInline
value class GIList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagGIList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagGIList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagGIList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagGIList, file)
}
