package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMaxHSPs
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = UInt.MAX_VALUE


internal fun ParseMaxHSPs(js: ObjectNode) =
  js.optUInt(FlagMaxHSPs) { MaxHSPs(it) } ?: MaxHSPs()


/**
 * -max_hsps `<Integer, >=1>`
 *
 * Set maximum number of HSPs per subject sequence to save for each query.
 */
@JvmInline
value class MaxHSPs(val value: UInt = Def) : BlastField {

  init {
    if (value < 1u)
      throw IllegalArgumentException("$FlagMaxHSPs must be greater than or equal to 1")
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagMaxHSPs

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMaxHSPs, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMaxHSPs, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMaxHSPs, value)
}

