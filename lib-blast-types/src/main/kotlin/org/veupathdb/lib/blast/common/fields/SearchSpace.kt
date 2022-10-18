package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSearchSpace
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val DefaultSearchSpace: Byte = -1


internal fun ParseSearchSpace(js: ObjectNode) =
  js.optByte(FlagSearchSpace) { SearchSpace(it) } ?: SearchSpace()


/**
 * -searchsp `<Int8, >=0>`
 *
 * Effective length of the search space
 */
@JvmInline
value class SearchSpace(val value: Byte = DefaultSearchSpace) : BlastField {
  override val isDefault get() = value == DefaultSearchSpace

  override val name: String
    get() = FlagSearchSpace

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSearchSpace, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSearchSpace, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSearchSpace, value)
}