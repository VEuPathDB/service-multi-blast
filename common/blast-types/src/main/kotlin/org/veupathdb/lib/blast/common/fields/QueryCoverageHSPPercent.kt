package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagQueryCoverageHSPPercent
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = -1.0


internal fun ParseQueryCoverageHSPPercent(js: ObjectNode) =
  js.optDub(FlagQueryCoverageHSPPercent) { QueryCoverageHSPPercent(it) }
    ?: QueryCoverageHSPPercent()

/**
 * -qcov_hsp_perc `<Real, 0..100>`
 *
 * Percent query coverage per hsp
 */
@JvmInline
value class QueryCoverageHSPPercent(val value: Double = Def)
  : BlastField
{
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagQueryCoverageHSPPercent

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagQueryCoverageHSPPercent, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagQueryCoverageHSPPercent, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagQueryCoverageHSPPercent, value)
}