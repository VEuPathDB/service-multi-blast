package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagQueryLocation
import org.veupathdb.lib.blast.common.FlagSubjectLocation
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*

private const val DefStart = 0u
private const val DefStop  = 0u
private const val KeyStart = "start"
private const val KeyStop  = "stop"



internal fun ParseSubjectLocation(js: ObjectNode) =
  js.optObject(FlagSubjectLocation) {
    SubjectLocation(
      it[KeyStart]?.reqUInt { "$FlagSubjectLocation.$KeyStart" } ?: DefStart,
      it[KeyStop]?.reqUInt { "$FlagSubjectLocation.$KeyStop" } ?: DefStop
    )
  } ?: SubjectLocation()


/**
 * -subject_loc `<String>`
 *
 * Location on the subject sequence in 1-based offsets (Format: start-stop)
 */
data class SubjectLocation(val start: UInt = DefStart, val stop: UInt = DefStop)
  : BlastField
{
  init {
    if (stop < start)
      throw IllegalArgumentException("$FlagQueryLocation stop cannot be greater than the start.  Given value: $start-$stop")
  }

  override val isDefault get() = start == DefStart && stop == DefStop

  override val name: String
    get() = FlagSubjectLocation

  override fun appendJson(js: ObjectNode) {
    if(!isDefault) {
      with(js.putObject(FlagSubjectLocation)) {
        put(KeyStart, start.toLong())
        put(KeyStop, stop.toLong())
      }
    }
  }

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSubjectLocation) { "$start-$stop" }

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSubjectLocation) { "$start-$stop" }
}
