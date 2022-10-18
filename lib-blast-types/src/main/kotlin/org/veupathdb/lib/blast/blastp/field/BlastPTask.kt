package org.veupathdb.lib.blast.blastp.field

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTask
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseBlastPTask(js: ObjectNode) =
  js.optString(FlagTask) { BlastPTask(parseTask(it)) } ?: BlastPTask()


/**
 * -task `<String>`
 *
 * Permissible values:
 * * blastp
 * * blastp-fast
 * * blastp-short
 *
 * Task to execute
 *
 * Default = `blastp`
 */
@JvmInline
value class BlastPTask(val value: BlastPTaskType = BlastPTaskType.BlastP)
  : BlastField
{
  override val isDefault get() = value == BlastPTaskType.BlastP

  override val name: String
    get() = FlagTask

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTask, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTask, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTask, value.value)
}


private fun parseTask(value: String) = when(value) {
  "blastp"       -> BlastPTaskType.BlastP
  "blastp-fast"  -> BlastPTaskType.BlastPFast
  "blastp-short" -> BlastPTaskType.BlastPShort
  else           -> throw IllegalArgumentException("Invalid value $value for enum BlastPTaskType")
}


enum class BlastPTaskType {
  BlastP,
  BlastPFast,
  BlastPShort;

  val value get() = when(this) {
    BlastP      -> "blastp"
    BlastPFast  -> "blastp-fast"
    BlastPShort -> "blastp-short"
  }
}