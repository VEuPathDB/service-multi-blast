package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNumAlignments
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val DefaultNumAlignments = 250u


internal fun ParseNumAlignments(js: ObjectNode) =
  js.optUInt(FlagNumAlignments) { NumAlignments(it) } ?: NumAlignments()


/**
 * -num_alignments `<Integer, >=0>`
 *
 * Number of database sequences to show alignments for
 *
 * Default = `250`
 */
@JvmInline
value class NumAlignments(val value: UInt = DefaultNumAlignments) : BlastField {
  override val isDefault get() = value == DefaultNumAlignments

  override val name: String
    get() = FlagNumAlignments

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNumAlignments, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNumAlignments, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNumAlignments, value)
}