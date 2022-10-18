package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagIPGList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseIPGList(js: ObjectNode) =
  js.optString(FlagIPGList) { IPGList(it) } ?: IPGList()


/**
 * -ipglist `<String>`
 *
 * Restrict search of database to list of IPGs
 */
@JvmInline
value class IPGList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagIPGList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagIPGList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagIPGList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagIPGList, file)
}