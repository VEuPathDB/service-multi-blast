package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNumThreads
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def: UByte = 1u


/**
 * -num_threads `<Integer, >=1>`
 *
 * Number of threads (CPUs) to use in the BLAST search
 *
 * Default = `1`
 */
internal fun ParseNumCPUCores(js: ObjectNode) =
  js.optUByte(FlagNumThreads) { NumCPUCores(it.inSet(FlagNumThreads, 1u, 10u)) }
    ?: NumCPUCores()


@JvmInline
value class NumCPUCores(val value: UByte = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagNumThreads

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNumThreads, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNumThreads, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNumThreads, value)
}