package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSavePSSMAfterLastRound
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


internal fun ParseSavePSSMAfterLastRound(js: ObjectNode) =
  js.optBool(FlagSavePSSMAfterLastRound) { SavePSSMAfterLastRound(it) }
    ?: SavePSSMAfterLastRound()


/**
 * -save_pssm_after_last_round
 *
 * Save PSSM after the last database search
 */
@JvmInline
value class SavePSSMAfterLastRound(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagSavePSSMAfterLastRound

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSavePSSMAfterLastRound, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSavePSSMAfterLastRound)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSavePSSMAfterLastRound)

  override fun clone() = this
}