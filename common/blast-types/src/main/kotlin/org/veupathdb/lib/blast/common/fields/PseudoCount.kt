package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagPseudoCount
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParsePseudoCount(js: ObjectNode) =
  js.optInt(FlagPseudoCount) { PseudoCount(it) } ?: PseudoCount()


/**
 * -pseudocount `<Integer>`
 *
 * Pseudo-count value used when constructing PSSM
 *
 * Default = `0`
 */
@JvmInline
value class PseudoCount(val value: Int = 0) : BlastField {
  override val isDefault get() = value == 0

  override val name: String
    get() = FlagPseudoCount

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagPseudoCount, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagPseudoCount, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagPseudoCount, value)
}