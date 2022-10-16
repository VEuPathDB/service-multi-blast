package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMSAMasterIndex
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseMSAMasterIndex(js: ObjectNode) =
  js.optUInt(FlagMSAMasterIndex) { MSAMasterIndex(it) } ?: MSAMasterIndex()


/**
 * -msa_master_idx `<Integer, >=1>`
 *
 * Ordinal number (1-based index) of the sequence to use as a master in the
 * multiple sequence alignment. If not provided, the first sequence in the
 * multiple sequence alignment will be used
 *
 * Requires: -in_msa
 */
@JvmInline
value class MSAMasterIndex(val value: UInt = 0u) : BlastField {
  override val isDefault get() = value == 0u

  override val name: String
    get() = FlagMSAMasterIndex

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMSAMasterIndex, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMSAMasterIndex, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMSAMasterIndex, value)
}