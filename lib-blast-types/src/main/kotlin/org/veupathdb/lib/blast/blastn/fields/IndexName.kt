package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagIndexName
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


internal fun ParseIndexName(js: ObjectNode) =
  js.optString(FlagIndexName) { IndexName(it) } ?: IndexName()


/**
 * -index_name `<String>`
 *
 * MegaBLAST database index name (deprecated; use only for old style indices)
 */
@JvmInline
value class IndexName(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagIndexName

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagIndexName, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagIndexName, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagIndexName, value)

  override fun clone() = this
}