package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagOutPSSM
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseOutPSSMFile(js: ObjectNode) =
  js.optString(FlagOutPSSM) { OutPSSMFile(it) } ?: OutPSSMFile()


/**
 * -out_pssm `<File_Out>`
 *
 * File name to store checkpoint file
 */
@JvmInline
value class OutPSSMFile(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagOutPSSM

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagOutPSSM, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagOutPSSM, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagOutPSSM, file)
}