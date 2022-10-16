package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagInPSSM
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseInPSSM(js: ObjectNode) =
  js.optString(FlagInPSSM) { InPSSMFile(it) } ?: InPSSMFile()


/**
 * -in_pssm `<File_In>`
 *
 * PSI-BLAST checkpoint file
 */
@JvmInline
value class InPSSMFile(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagInPSSM

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagInPSSM, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagInPSSM, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagInPSSM, file)
}