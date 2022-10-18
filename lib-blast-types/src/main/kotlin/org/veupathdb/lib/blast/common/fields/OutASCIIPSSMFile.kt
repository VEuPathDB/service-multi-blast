package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagOutASCIIPSSM
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseOutASCIIPSSMFile(js: ObjectNode) =
  js.optString(FlagOutASCIIPSSM) { OutASCIIPSSMFile(it) } ?: OutASCIIPSSMFile()


/**
 * -out_ascii_pssm `<File_Out>`
 *
 * File name to store ASCII version of PSSM
 */
@JvmInline
value class OutASCIIPSSMFile(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagOutASCIIPSSM

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagOutASCIIPSSM, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagOutASCIIPSSM, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagOutASCIIPSSM, file)
}