package org.veupathdb.lib.blast.formatter.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagArchive
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


/**
 * Parses a [Archive] value from the [FlagArchive] property set on the
 * given JSON [ObjectNode].
 *
 * If no such property exists, returns a defaulted instance.
 *
 * @param js JSON [ObjectNode] from which to parse the [Archive] value (if
 * present).
 *
 * @return A new [Archive] instance.
 */
internal fun ParseArchive(js: ObjectNode) =
  js.optString(FlagArchive) { Archive(it) } ?: Archive()


/**
 * -archive `<File_In>`
 *
 * File containing BLAST Archive format in ASN.1 (i.e.: output format 11)
 */
@JvmInline
value class Archive(val file: String = "") : BlastField {
  override val isDefault get() = file.isBlank()

  override val name: String
    get() = FlagArchive

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagArchive, file)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagArchive, file)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagArchive, file)
}