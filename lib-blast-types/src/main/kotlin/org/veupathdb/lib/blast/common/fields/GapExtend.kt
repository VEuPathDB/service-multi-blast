package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagGapExtend
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optInt
import org.veupathdb.lib.blast.util.put


private const val Def = Integer.MIN_VALUE


internal fun ParseGapExtend(js: ObjectNode) =
  js.optInt(FlagGapExtend) { GapExtend(it) } ?: GapExtend()


/**
 * -gapextend `<Integer>`
 *
 * Cost to extend a gap
 */
@JvmInline
value class GapExtend(val value: Int = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagGapExtend

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagGapExtend, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagGapExtend, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagGapExtend, value)
}