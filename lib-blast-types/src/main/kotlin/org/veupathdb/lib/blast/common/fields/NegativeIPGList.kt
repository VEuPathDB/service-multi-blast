package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNegativeIPGList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseNegativeIPGList(js: ObjectNode) =
  js.optString(FlagNegativeIPGList) { NegativeIPGList(it) } ?: NegativeIPGList()


/**
 * -negative_ipglist `<String>`
 *
 * Restrict search of database to everything except the specified IPGs
 */
@JvmInline
value class NegativeIPGList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagNegativeIPGList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNegativeIPGList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNegativeIPGList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNegativeIPGList, file)
}