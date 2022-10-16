package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagBestHitOverhang
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optDub
import org.veupathdb.lib.blast.util.put

/**
 * Default value.
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


internal fun ParseBestHitOverhang(js: ObjectNode) =
  js.optDub(FlagBestHitOverhang) { BestHitOverhang(it) } ?: BestHitOverhang()


/**
 * -best_hit_overhang `<Real, (>0 and <0.5)>`
 *
 * Best Hit algorithm overhang value (recommended value: 0.1)
 */
@JvmInline
value class BestHitOverhang(val value: Double = Def) : BlastField {
  init {
    if (value != Def)
      value.inESet(FlagBestHitOverhang, EMin, EMax)
  }

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagBestHitOverhang

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagBestHitOverhang, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagBestHitOverhang, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagBestHitOverhang, value)
}