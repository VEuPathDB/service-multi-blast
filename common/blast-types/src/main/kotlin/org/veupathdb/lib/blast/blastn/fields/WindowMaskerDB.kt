package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagWindowMaskerDB
import org.veupathdb.lib.blast.common.FlagWindowMaskerTaxID
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseWindowMaskerDB(js: ObjectNode) =
  js.optString(FlagWindowMaskerDB) { WindowMaskerDB(it) } ?: WindowMaskerDB()


/**
 * -window_masker_db `<String>`
 *
 * Enable WindowMasker filtering using this repeats database.
 */
@JvmInline
value class WindowMaskerDB(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagWindowMaskerDB

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagWindowMaskerDB, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagWindowMaskerDB, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagWindowMaskerDB, file)
}