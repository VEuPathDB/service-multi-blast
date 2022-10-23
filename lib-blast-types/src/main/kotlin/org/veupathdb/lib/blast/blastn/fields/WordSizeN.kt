package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.blastp.field.WordSizeP
import org.veupathdb.lib.blast.common.FlagWordSize
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optUInt
import org.veupathdb.lib.blast.util.put

private const val Default     = UInt.MAX_VALUE
private const val MinWordSize = 4u


internal fun ParseWordSizeN(js: ObjectNode) =
  js.optUInt(FlagWordSize) { WordSizeN(it) } ?: WordSizeN()


@JvmInline
value class WordSizeN(val value: UInt = Default) : BlastField {

  init {
    if (value < MinWordSize)
      throw IllegalArgumentException("$FlagWordSize must be a uint value >= $MinWordSize")
  }

  override val isDefault: Boolean
    get() = value == Default

  override val name: String
    get() = FlagWordSize

  override fun appendJson(js: ObjectNode) {
    js.put(isDefault, FlagWordSize, value)
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(isDefault, FlagWordSize, value)
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(isDefault, FlagWordSize, value)
  }

  override fun clone() = this
}
