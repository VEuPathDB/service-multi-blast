package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagRemote
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseRemote(js: ObjectNode) =
  js.optBool(FlagRemote) { Remote(it) } ?: Remote()


/**
 * -remote
 *
 * Execute search remotely?
 */
@JvmInline
value class Remote(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagRemote

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagRemote, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagRemote)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagRemote)
}