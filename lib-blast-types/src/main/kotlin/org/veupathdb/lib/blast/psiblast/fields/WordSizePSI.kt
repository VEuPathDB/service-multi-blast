package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagWordSize
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = UInt.MAX_VALUE


internal fun ParseWordSizePSI(js: ObjectNode) =
  js.optUInt(FlagWordSize) { WordSizePSI(it) } ?: WordSizePSI()


/**
 * -word_size `<Integer, >=2>`
 *
 * Word size for wordfinder algorithm
 */
@JvmInline
value class WordSizePSI(val value: UInt = Def) : BlastField {
  init {
    if (value < 2u)
      throw IllegalArgumentException("$FlagWordSize must be >= 2")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagWordSize

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagWordSize, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagWordSize, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagWordSize, value)
}