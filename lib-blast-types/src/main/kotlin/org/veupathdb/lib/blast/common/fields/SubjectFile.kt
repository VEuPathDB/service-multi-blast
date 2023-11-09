package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSubjectFile
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


internal fun ParseSubjectFile(js: ObjectNode) =
  js.optString(FlagSubjectFile) { SubjectFile(it) } ?: SubjectFile()


/**
 * -subject `<File_In>`
 *
 * Subject sequence(s) to search
 */
@JvmInline
value class SubjectFile(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagSubjectFile

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSubjectFile, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSubjectFile, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSubjectFile, file)

  override fun clone() = this
}