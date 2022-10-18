package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagIgnoreMSAMaster
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseIgnoreMSAMaster(js: ObjectNode) =
  js.optBool(FlagIgnoreMSAMaster) { IgnoreMSAMaster(it) } ?: IgnoreMSAMaster()


/**
 * -ignore_msa_master
 *
 * Ignore the master sequence when creating PSSM
 *
 * Requires: -in_msa
 */
@JvmInline
value class IgnoreMSAMaster(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagIgnoreMSAMaster

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagIgnoreMSAMaster, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagIgnoreMSAMaster)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagIgnoreMSAMaster)
}