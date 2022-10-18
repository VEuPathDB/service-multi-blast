package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagFilteringDB
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseFilteringDB(js: ObjectNode): FilteringDB =
  js.optString(FlagFilteringDB) { FilteringDB(it) } ?: FilteringDB()


/**
 * -filtering_db `<String>`
 *
 * BLAST database containing filtering elements (i.e.: repeats)
 */
@JvmInline
value class FilteringDB(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagFilteringDB

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagFilteringDB, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagFilteringDB, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagFilteringDB, value)
}