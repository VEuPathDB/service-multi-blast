package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagPHIPattern
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParsePhiPatternFile(js: ObjectNode) =
  js.optString(FlagPHIPattern) { PHIPatternFile(it) } ?: PHIPatternFile()


/**
 * -phi_pattern `<File_In>`
 *
 * File name containing pattern to search
 */
@JvmInline
value class PHIPatternFile(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagPHIPattern

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagPHIPattern, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagPHIPattern, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagPHIPattern, value)
}