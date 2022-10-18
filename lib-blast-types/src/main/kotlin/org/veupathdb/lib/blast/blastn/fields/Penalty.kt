package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagPenalty
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = Int.MIN_VALUE


internal fun ParsePenalty(js: ObjectNode) =
  js.optInt(FlagPenalty) { Penalty(it) } ?: Penalty()


/**
 * -penalty `<Integer, <=0>`
 *
 * Penalty for a nucleotide mismatch
 */
@JvmInline
value class Penalty(val value: Int = Def) : BlastField {
  init {
    if (value > 0)
      throw IllegalArgumentException("$FlagPenalty must be <= 0")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagPenalty

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagPenalty, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagPenalty, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagPenalty, value)
}