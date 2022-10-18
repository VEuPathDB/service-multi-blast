package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNegativeTaxIDs
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.optArray
import org.veupathdb.lib.blast.util.toStrList


internal fun ParseNegTaxIDs(js: ObjectNode) =
  js.optArray(FlagNegativeTaxIDs) { NegativeTaxIDs(it.toStrList(FlagNegativeTaxIDs)) }
    ?: NegativeTaxIDs()


/**
 * -negative_taxids `<String>`
 *
 * Restrict search of database to everything except the specified taxonomy IDs
 * (multiple IDs delimited by ',')
 */
@JvmInline
value class NegativeTaxIDs(val value: List<String> = emptyList()) : BlastField {
  override val isDefault get() = value.isEmpty()

  override val name: String
    get() = FlagNegativeTaxIDs

  override fun appendJson(js: ObjectNode) {
    if (!isDefault)
      with(js.putArray(FlagNegativeTaxIDs)) { value.forEach { add(it) } }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault) {
      cli.append(' ').append(FlagNegativeTaxIDs).append(" '")
      value.join(cli)
      cli.append('\'')
    }
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagNegativeTaxIDs)

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
