package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNumIterations
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def: UByte = 1u


internal fun ParseNumIterations(js: ObjectNode) =
  js.optUByte(FlagNumIterations) { NumIterations(it) } ?: NumIterations()


/**
 * -num_iterations `<Integer, >=0>`
 *
 * Number of iterations to perform (0 means run until convergence)
 *
 * Default = `1`
 */
@JvmInline
value class NumIterations(val value: UByte = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagNumIterations

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNumIterations, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNumIterations, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNumIterations, value)
}