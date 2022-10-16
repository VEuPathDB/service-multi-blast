package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTaxIDList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseTaxIDList(js: ObjectNode) =
  js.optString(FlagTaxIDList) { TaxIDList(it) } ?: TaxIDList()


/**
 * -taxidlist `<String>`
 *
 * Restrict search of database to include only the specified taxonomy IDs
 */
@JvmInline
value class TaxIDList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagTaxIDList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTaxIDList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTaxIDList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTaxIDList, file)
}

