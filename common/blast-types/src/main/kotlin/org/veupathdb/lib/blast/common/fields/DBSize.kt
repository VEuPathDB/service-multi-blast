package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDBSize
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val Def: Byte = 0


internal fun ParseDBSize(js: ObjectNode) =
  js.optByte(FlagDBSize) { DBSize(it) } ?: DBSize()


/**
 * -dbsize `<Int8>`
 *
 * Effective length of the database
 */
@JvmInline
value class DBSize(val value: Byte = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagDBSize

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagDBSize, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagDBSize, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagDBSize, value)
}