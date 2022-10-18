package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagExportSearchStrategy
import org.veupathdb.lib.blast.common.FlagImportSearchStrategy
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseImportSearchStrategy(js: ObjectNode) =
  js.optString(FlagImportSearchStrategy) { ImportSearchStrategy(it) }
    ?: ImportSearchStrategy()


/**
 * -import_search_strategy `<File_In>`
 *
 * Search strategy to use
 */
@JvmInline
value class ImportSearchStrategy(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagImportSearchStrategy

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagImportSearchStrategy, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagImportSearchStrategy, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagImportSearchStrategy, file)
}


internal fun ParseExportSearchStrategy(js: ObjectNode) =
  js.optString(FlagExportSearchStrategy) { ExportSearchStrategy(it) }
    ?: ExportSearchStrategy()


@JvmInline
value class ExportSearchStrategy(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagExportSearchStrategy

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagExportSearchStrategy, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagExportSearchStrategy, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagExportSearchStrategy, file)
}