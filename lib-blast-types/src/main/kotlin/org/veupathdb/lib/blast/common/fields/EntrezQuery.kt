package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagEntrezQuery
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


internal fun ParseEntrezQuery(js: ObjectNode) =
  js.optString(FlagEntrezQuery) { EntrezQuery(it) } ?: EntrezQuery()


/**
 * -entrez_query `<String>`
 *
 * Restrict search with the given Entrez query
 */
@JvmInline
value class EntrezQuery(val value: String = "") : BlastField {
  override val isDefault get() = value.isBlank()

  override val name: String
    get() = FlagEntrezQuery

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagEntrezQuery, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagEntrezQuery, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagEntrezQuery, value)

  override fun clone() = this
}