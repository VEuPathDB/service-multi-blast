package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagBestHitScoreEdge
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optDub
import org.veupathdb.lib.blast.util.put


/**
 * Default Value
 */
private const val Def = -1.0

/**
 * Exclusive Minimum
 */
private const val EMin = 0.0

/**
 * Exclusive Maximum
 */
private const val EMax = 0.5


internal fun ParseBestHitScoreEdge(js: ObjectNode) =
  js.optDub(FlagBestHitScoreEdge) { BestHitScoreEdge(it)} ?: BestHitScoreEdge()


/**
 * -best_hit_score_edge `<Real, (>0 and <0.5)>`
 *
 * Best Hit algorithm score edge value (recommended value: 0.1)
 */
@JvmInline
value class BestHitScoreEdge(val value: Double = Def) : BlastField {
  init {
    if (value != Def)
      value.inESet(FlagBestHitScoreEdge, EMin, EMax)
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagBestHitScoreEdge

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagBestHitScoreEdge, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagBestHitScoreEdge, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagBestHitScoreEdge, value)
}