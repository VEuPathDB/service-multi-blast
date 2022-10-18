package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagQueryFile
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def = "-"


internal fun ParseQueryFile(js: ObjectNode) =
  js.optString(FlagQueryFile) { QueryFile(it) } ?: QueryFile()


/**
 * -query `<File_In>`
 *
 * Input file name
 *
 * Default = `-`
 */
@JvmInline
value class QueryFile(val file: String = Def) : BlastField {
  override val isDefault get() = file == Def

  override val name: String
    get() = FlagQueryFile

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagQueryFile, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagQueryFile, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagQueryFile, file)
}