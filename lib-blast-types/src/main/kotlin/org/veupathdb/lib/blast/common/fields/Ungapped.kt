package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagUngapped
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


internal fun ParseUngappedAlignmentsOnly(js: ObjectNode) =
  js.optBool(FlagUngapped) { UngappedAlignmentsOnly(it) }
    ?: UngappedAlignmentsOnly()


/**
 * -ungapped
 *
 * Perform ungapped alignment only?
 */
@JvmInline
value class UngappedAlignmentsOnly(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagUngapped

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagUngapped, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagUngapped)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagUngapped)

  override fun clone() = this
}