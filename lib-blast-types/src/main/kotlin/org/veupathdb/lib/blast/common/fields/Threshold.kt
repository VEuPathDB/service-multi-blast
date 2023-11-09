package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagThreshold
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optDub
import org.veupathdb.lib.blast.util.put


private const val Def = Double.NEGATIVE_INFINITY


internal fun ParseThreshold(js: ObjectNode) =
  js.optDub(FlagThreshold) { Threshold(it) } ?: Threshold()


/**
 * -threshold `<Real, >=0>`
 *
 * Minimum word score such that the word is added to the BLAST lookup table
 */
@JvmInline
value class Threshold(val value: Double = Def) : BlastField {
  init {
    if (value < 0 && value != Def)
      throw IllegalArgumentException("$FlagThreshold must be >= 0")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagThreshold

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagThreshold, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagThreshold, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagThreshold, value)

  override fun clone() = this
}