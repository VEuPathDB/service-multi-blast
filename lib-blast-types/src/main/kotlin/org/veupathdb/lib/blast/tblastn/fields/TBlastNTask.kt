package org.veupathdb.lib.blast.tblastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagTask
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


/**
 * Default Value
 */
private val Def = TBlastNTaskType.TBlastN


/**
 * Parses a TBlastNTask value from the [FlagTask] property set on the given
 * JSON [ObjectNode].
 *
 * If no such property exists, returns a defaulted instance.
 *
 * @param js JSON [ObjectNode] from which to parse the [TBlastNTask] value (if
 * present).
 *
 * @return A new [TBlastNTask] instance.
 */
internal fun ParseTBlastNTask(js: ObjectNode) =
  js.optString(FlagTask) { TBlastNTask(parse(it)) } ?: TBlastNTask()


/**
 * -task `<String>`
 *
 * Permissible values:
 * * tblastn
 * * tblastn-fast
 *
 * Task to execute.
 *
 * Default = `tblastn`
 */
@JvmInline
value class TBlastNTask(val value: TBlastNTaskType = Def) : BlastField {

  override val isDefault get() = value == Def

  override val name: String
    get() = FlagTask

  override fun appendJson(js: ObjectNode) = js.put(isDefault, FlagTask, value.value)

  override fun appendCliSegment(cli: StringBuilder) = cli.append(isDefault, FlagTask, value.value)

  override fun appendCliParts(cli: MutableList<String>) = cli.add(isDefault, FlagTask, value.value)

  override fun clone() = this
}


/**
 * Parse [TBlastNTaskType] from string.
 */
private inline fun parse(value: String) =
  when (value.lowercase()) {
    "tblastn"      -> TBlastNTaskType.TBlastN
    "tblastn-fast" -> TBlastNTaskType.TBlastNFast
    else           -> throw IllegalArgumentException("Invalid $FlagTask value: $value")
  }


enum class TBlastNTaskType {
  TBlastN,
  TBlastNFast;

  val value get() = when(this) {
    TBlastN     -> "tblastn"
    TBlastNFast -> "tblastn-fast"
  }
}