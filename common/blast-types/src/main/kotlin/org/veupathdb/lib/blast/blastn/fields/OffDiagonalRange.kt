package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagOffDiagonalRange
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = 0u


internal fun ParseOffDiagonalRange(js: ObjectNode) =
  js.optUInt(FlagOffDiagonalRange) { OffDiagonalRange(it) }
    ?: OffDiagonalRange()


/**
 * -off_diagonal_range `<Integer, >=0>`
 *
 * Number of off-diagonals to search for the 2nd hit, use 0 to turn off
 *
 * Default = `0`
 */
@JvmInline
value class OffDiagonalRange(val value: UInt = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagOffDiagonalRange

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagOffDiagonalRange, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagOffDiagonalRange, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagOffDiagonalRange, value)
}