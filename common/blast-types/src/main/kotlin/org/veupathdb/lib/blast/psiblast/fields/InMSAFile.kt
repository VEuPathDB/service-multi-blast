package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagInMSA
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseInMSA(js: ObjectNode) =
  js.optString(FlagInMSA) { InMSAFile(it) } ?: InMSAFile()


/**
 * -in_msa `<File_In>`
 *
 * File name of multiple sequence alignment to restart PSI-BLAST
 */
@JvmInline
value class InMSAFile(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagInMSA

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagInMSA, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagInMSA, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagInMSA, file)
}