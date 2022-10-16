package org.veupathdb.lib.blast.tblastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagWordSize
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


/**
 * Default Value
 */
private const val Def = UInt.MAX_VALUE


/**
 * Parses a [WordSizeTN] value from the [FlagWordSize] property set on the
 * given JSON [ObjectNode].
 *
 * If no such property exists, returns a defaulted instance.
 *
 * @param js JSON [ObjectNode] from which to parse the [WordSizeTN] value
 * (if present)
 *
 * @return A new [WordSizeTN] instance.
 */
internal fun ParseWordSizeTBlastN(js: ObjectNode) =
  js.optUInt(FlagWordSize) { WordSizeTN(it) } ?: WordSizeTN()


/**
 * -word_size `<Integer, >=2>`
 *
 * Word size for wordfinder algorithm
 */
@JvmInline
value class WordSizeTN(val value: UInt = Def) : BlastField {
  init {
    if (value < 2u)
      throw IllegalArgumentException("$FlagWordSize must be >= 2")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagWordSize

  override fun appendJson(js: ObjectNode) = js.put(isDefault, FlagWordSize, value)

  override fun appendCliSegment(cli: StringBuilder) = cli.append(isDefault, FlagWordSize, value)

  override fun appendCliParts(cli: MutableList<String>) = cli.add(isDefault, FlagWordSize, value)
}