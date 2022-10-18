package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNegativeSeqIDList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseNegSeqIDList(js: ObjectNode) =
  js.optString(FlagNegativeSeqIDList) { NegativeSeqIDList(it) }
    ?: NegativeSeqIDList()


/**
 * -negative_seqidlist `<String>`
 *
 * Restrict search of database to everything except the specified SeqIDs
 */
@JvmInline
value class NegativeSeqIDList(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagNegativeSeqIDList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNegativeSeqIDList, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNegativeSeqIDList, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNegativeSeqIDList, file)
}
