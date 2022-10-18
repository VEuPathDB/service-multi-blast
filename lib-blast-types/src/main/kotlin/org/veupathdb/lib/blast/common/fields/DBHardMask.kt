package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDBHardMask
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseDBHardMask(js: ObjectNode) =
  js.optString(FlagDBHardMask) { DBHardMask(it) } ?: DBHardMask()


/**
 * -db_hard_mask `<String>`
 *
 * Filtering algorithm ID to apply to the BLAST database as hard masking
 */
@JvmInline
value class DBHardMask(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagDBHardMask

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagDBHardMask, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagDBHardMask, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagDBHardMask, value)
}