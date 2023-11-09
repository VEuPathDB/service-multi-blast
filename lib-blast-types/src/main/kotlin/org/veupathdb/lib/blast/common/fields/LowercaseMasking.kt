package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagLowercaseMasking
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


internal fun ParseLowercaseMasking(js: ObjectNode) =
  js.optBool(FlagLowercaseMasking) { LowercaseMasking(it) }
    ?: LowercaseMasking()


/**
 * -lcase_masking
 *
 * Use lower case filtering in query and subject sequence(s)
 */
@JvmInline
value class LowercaseMasking(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagLowercaseMasking

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagLowercaseMasking, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagLowercaseMasking)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagLowercaseMasking)

  override fun clone() = this
}