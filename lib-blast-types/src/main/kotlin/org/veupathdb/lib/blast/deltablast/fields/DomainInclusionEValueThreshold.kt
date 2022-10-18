package org.veupathdb.lib.blast.deltablast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDomainInclusionEThresh
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseDomainInclusionEValueThreshold(js: ObjectNode) =
  js.optDub(FlagDomainInclusionEThresh) { DomainInclusionEValueThreshold(it) }
    ?: DomainInclusionEValueThreshold()


/**
 * -domain_inclusion_ethresh `<Real>`
 *
 * E-value inclusion threshold for alignments with conserved domains
 *
 * Default = `0.05`
 */
@JvmInline
value class DomainInclusionEValueThreshold(val value: Double = 0.05)
  : BlastField
{
  override val isDefault get() = value == 0.05

  override val name: String
    get() = FlagDomainInclusionEThresh

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagDomainInclusionEThresh, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagDomainInclusionEThresh, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagDomainInclusionEThresh, value)
}