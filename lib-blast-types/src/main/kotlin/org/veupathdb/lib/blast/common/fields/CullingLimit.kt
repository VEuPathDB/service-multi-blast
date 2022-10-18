package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCullingLimit
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optUInt
import org.veupathdb.lib.blast.util.put


private const val Def = UInt.MAX_VALUE


internal fun ParseCullingLimit(js: ObjectNode) =
  js.optUInt(FlagCullingLimit) { CullingLimit(it) } ?: CullingLimit()


/**
 * -culling_limit `<Integer, >=0>`
 *
 * If the query range of a hit is enveloped by that of at least this many
 * higher-scoring hits, delete the hit
 */
@JvmInline
value class CullingLimit(val value: UInt = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagCullingLimit

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagCullingLimit, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagCullingLimit, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagCullingLimit, value)
}