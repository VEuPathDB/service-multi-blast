package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagWindowSize
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = UInt.MAX_VALUE


internal fun ParseWindowSize(js: ObjectNode) =
  js.optUInt(FlagWindowSize) { WindowSize(it) } ?: WindowSize()


/**
 * -window_size `<Integer, >=0>`
 *
 * Multiple hits window size, use 0 to specify 1-hit algorithm
 */
@JvmInline
value class WindowSize(val value: UInt = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagWindowSize

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagWindowSize, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagWindowSize, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagWindowSize, value)
}