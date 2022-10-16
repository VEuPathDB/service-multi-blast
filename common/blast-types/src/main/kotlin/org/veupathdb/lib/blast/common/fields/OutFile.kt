package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagOut
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


private const val Def = "-"


internal fun ParseOutFile(js: ObjectNode) =
  js.optString(FlagOut) { OutFile(it) } ?: OutFile()


/**
 * -out `<File_Out>`
 *
 * Output file name
 *
 * Default = `-`
 */
@JvmInline
value class OutFile(val file: String = Def): BlastField {
  override val isDefault get() = file == Def || file.isBlank()

  override val name: String
    get() = FlagOut

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagOut, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagOut, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagOut, file)
}