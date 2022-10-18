package org.veupathdb.lib.blast.formatter.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagRID
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put

/**
 * Parses a [RID] value from the [FlagRID] property set on the
 * given JSON [ObjectNode].
 *
 * If no such property exists, returns a defaulted instance.
 *
 * @param js JSON [ObjectNode] from which to parse the [RID] value (if
 * present).
 *
 * @return A new [RID] instance.
 */
internal fun ParseRID(js: ObjectNode) =
  js.optString(FlagRID) { RID(it) } ?: RID()


/**
 * -rid `<String>`
 *
 * BLAST Request ID (RID)
 */
@JvmInline
value class RID(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagRID

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagRID, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagRID, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagRID, value)
}