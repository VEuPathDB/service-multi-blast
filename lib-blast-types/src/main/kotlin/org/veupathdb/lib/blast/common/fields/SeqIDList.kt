package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSeqIDList
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


internal fun ParseSeqIDList(js: ObjectNode) =
  js.optString(FlagSeqIDList) { SeqIDList(it) } ?: SeqIDList()


/**
 * -seqidlist `<String>`
 *
 * Restrict search of database to list of SeqIDs
 */
@JvmInline
value class SeqIDList(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagSeqIDList

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSeqIDList, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSeqIDList, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSeqIDList, value)

  override fun clone() = this
}

