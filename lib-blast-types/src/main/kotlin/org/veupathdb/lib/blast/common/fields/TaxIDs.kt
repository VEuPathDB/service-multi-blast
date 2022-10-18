package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTaxIDs
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.optArray
import org.veupathdb.lib.blast.util.toStrList


internal fun ParseTaxIDs(js: ObjectNode) =
  js.optArray(FlagTaxIDs) { TaxIDs(it.toStrList(FlagTaxIDs)) } ?: TaxIDs()


/**
 * -taxids `<String>`
 *
 * Restrict search of database to include only the specified taxonomy IDs
 * (multiple IDs delimited by ',')
 */
@JvmInline
value class TaxIDs(val value: List<String> = emptyList()) : BlastField {
  override val isDefault get() = value.isEmpty()

  override val name: String
    get() = FlagTaxIDs

  override fun appendJson(js: ObjectNode) {
    if (!isDefault)
      with(js.putArray(FlagTaxIDs)) { value.forEach { add(it) } }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault) {
      cli.append(' ').append(FlagTaxIDs).append(" '")
      value.join(cli)
      cli.append('\'')
    }
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagTaxIDs)

      val sb = StringBuilder(2048)
      value.join(sb)

      cli.add(sb.toString())
    }
  }
}


private fun List<String>.join(sb: StringBuilder) {
  sb.append(this[0])

  for (i in 1 until size) {
    sb.append(',').append(this[i])
  }
}
