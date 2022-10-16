package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagShowGIs
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseShowGIs(js: ObjectNode) =
  js.optBool(FlagShowGIs) { ShowGIs(it) } ?: ShowGIs()


/**
 * -show_gis
 *
 * Show NCBI GIs in deflines?
 */
@JvmInline
value class ShowGIs(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagShowGIs

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagShowGIs, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagShowGIs)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagShowGIs)
}