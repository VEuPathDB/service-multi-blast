package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNegativeTaxIDList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseNegTaxIDList(js: ObjectNode) =
  js.optString(FlagNegativeTaxIDList) { NegativeTaxIDList(it) }
    ?: NegativeTaxIDList()


/**
 * -negative_taxidlist `<String>`
 *
 * Restrict search of database to everything except the specified taxonomy IDs
 */
@JvmInline
value class NegativeTaxIDList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagNegativeTaxIDList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNegativeTaxIDList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNegativeTaxIDList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNegativeTaxIDList, file)
}
