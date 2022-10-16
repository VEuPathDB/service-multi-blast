package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMultiThreadingMode
import org.veupathdb.lib.blast.common.FlagNumThreads
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*

private val Def = MultiThreadingModeValue.SplitByDatabase

internal fun ParseMultiThreadingMode(js: ObjectNode) =
  js.optInt(FlagMultiThreadingMode) { MultiThreadingMode(parse(it)) }
    ?: MultiThreadingMode()

/**
 * -mt_mode `<Integer, (>=0 and =<1)>`
 *
 * Multi-thread mode to use in RPS BLAST search:
 * * 0 (auto) split by database vols
 * * 1 split by queries
 *
 * Default = `0`
 */
@JvmInline
value class MultiThreadingMode(val value: MultiThreadingModeValue = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagNumThreads

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMultiThreadingMode, value.ordinal)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMultiThreadingMode, value.ordinal)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMultiThreadingMode, value.ordinal)
}

@Suppress("NOTHING_TO_INLINE")
private inline fun parse(v: Int) =
  MultiThreadingModeValue.values()[v.inSet(FlagMultiThreadingMode, 0, 1)]

enum class MultiThreadingModeValue {
  SplitByDatabase,
  SplitByQuery
}