package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagReward
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = UInt.MAX_VALUE


internal fun ParseReward(js: ObjectNode) =
  js.optUInt(FlagReward) { Reward(it) } ?: Reward()


/**
 * -reward `<Integer, >=0>`
 *
 * Reward for a nucleotide match
 */
@JvmInline
value class Reward(val value: UInt = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagReward

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagReward, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagReward, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagReward, value)
}