package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagGapOpen
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optInt
import org.veupathdb.lib.blast.util.put


private const val Def = Integer.MIN_VALUE


internal fun ParseGapOpen(js: ObjectNode) =
  js.optInt(FlagGapOpen) { GapOpen(it) } ?: GapOpen()


/**
 * -gapopen `<Integer>`
 *
 * Cost to open a gap
 */
@JvmInline
value class GapOpen(val value: Int = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagGapOpen

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagGapOpen, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagGapOpen, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagGapOpen, value)
}
