package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagWindowMaskerDB
import org.veupathdb.lib.blast.common.FlagWindowMaskerTaxID
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optInt
import org.veupathdb.lib.blast.util.put

private const val Def = -1


internal fun ParseWindowMaskerTaxID(js: ObjectNode) =
  js.optInt(FlagWindowMaskerDB) { WindowMaskerTaxID(it) } ?: WindowMaskerTaxID()


/**
 * -window_masker_taxid `<Integer>`
 *
 * Enable WindowMasker filtering using a Taxonomic ID
 */
@JvmInline
value class WindowMaskerTaxID(val value: Int = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagWindowMaskerTaxID

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagWindowMaskerTaxID, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagWindowMaskerTaxID, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagWindowMaskerTaxID, value)

  override fun clone() = this
}