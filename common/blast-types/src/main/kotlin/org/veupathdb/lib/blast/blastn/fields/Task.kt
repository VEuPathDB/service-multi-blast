package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTask
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseBlastNTask(js: ObjectNode) =
  BlastNTask(js[FlagTask].parseTask())


/**
 * -task `<String>`
 *
 * Permissible values:
 * * 'blastn'
 * * 'blastn-short'
 * * 'dc-megablast'
 * * 'megablast'
 * * 'rmblastn'
 *
 * Task to execute
 *
 * Default = `megablast`
 */
@JvmInline
value class BlastNTask(val value: BlastNTaskType = BlastNTaskType.Megablast)
  : BlastField
{
  override val isDefault get() = value == BlastNTaskType.Megablast

  override val name: String
    get() = FlagTask

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTask, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTask, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTask, value.value)
}


////////////////////////////////////////////////////////////////////////////////


private fun JsonNode?.parseTask(): BlastNTaskType {
  if (this == null)
    return BlastNTaskType.Megablast

  if (!isTextual)
    throw IllegalArgumentException("$FlagTask must be a string value.")

  val tmp = textValue()

  for (v in BlastNTaskType.values())
    if (tmp == v.value)
      return v

  throw IllegalArgumentException("Invalid value $tmp for enum BlastNTaskType")
}


enum class BlastNTaskType(val value: String) {
  BlastN("blastn"),
  BlastNShort("blastn-short"),
  DiscontiguousMegablast("dc-megablast"),
  Megablast("megablast"),
  RMBlastN("rmblastn");
}