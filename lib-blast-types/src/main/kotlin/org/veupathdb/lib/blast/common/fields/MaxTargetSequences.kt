package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMaxTargetSeqs
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = 500u


internal fun ParseMaxTargetSeqs(js: ObjectNode) =
  js.optUInt(FlagMaxTargetSeqs) { MaxTargetSeqs(it) } ?: MaxTargetSeqs()


/**
 * -max_target_seqs `<Integer, >=1>`
 *
 * Maximum number of aligned sequences to keep (value of 5 or more is
 * recommended)
 *
 * Default = `500`
 */
@JvmInline
value class MaxTargetSeqs(val value: UInt = Def) : BlastField {

  init {
    if (value < 1u)
      throw IllegalArgumentException("$FlagMaxTargetSeqs must be >= 1")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagMaxTargetSeqs

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMaxTargetSeqs, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMaxTargetSeqs, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMaxTargetSeqs, value)
}