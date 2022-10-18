package org.veupathdb.lib.blast.deltablast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagShowDomainHits
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseShowDomainHits(js: ObjectNode) =
  js.optBool(FlagShowDomainHits) { ShowDomainHits(it) } ?: ShowDomainHits()


/**
 * -show_domain_hits
 *
 * Show domain hits
 */
@JvmInline
value class ShowDomainHits(val value: Boolean = false): BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagShowDomainHits

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagShowDomainHits, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagShowDomainHits)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagShowDomainHits)
}