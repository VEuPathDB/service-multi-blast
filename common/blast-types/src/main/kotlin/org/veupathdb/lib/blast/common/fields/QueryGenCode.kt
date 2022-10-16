package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagQueryGenCode
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def: UByte  = 1u


internal fun ParseQueryGenCode(js: ObjectNode) =
  js.optUByte(FlagQueryGenCode) { QueryGenCode(it) } ?: QueryGenCode()


/**
 * -query_gencode `<Integer>`
 *
 * Permissible values between:
 * * [1..6]
 * * [9..16]
 * * [21..31]
 * * [33]
 *
 * Genetic code to use to translate query (see
 * https://www.ncbi.nlm.nih.gov/Taxonomy/taxonomyhome.html/index.cgi?chapter=cgencodes
 * for details)
 *
 * Default = `1`
 */
@JvmInline
value class QueryGenCode(val value: UByte = Def) : BlastField {
  init {
    when {
      inRange(value, 1u, 6u)   -> {}
      inRange(value, 9u, 16u)  -> {}
      inRange(value, 21u, 31u) -> {}
      value == 33u.toUByte()   -> {}
      else                     ->
        throw IllegalArgumentException("$FlagQueryGenCode must be an int value equal to 33 or in one of the following ranges: 1-6, 9-16, 21-31")
    }
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagQueryGenCode

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagQueryGenCode, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagQueryGenCode, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagQueryGenCode, value)
}


@Suppress("NOTHING_TO_INLINE")
private inline fun inRange(v: UByte, n: UByte, x: UByte) = (v in n .. x)