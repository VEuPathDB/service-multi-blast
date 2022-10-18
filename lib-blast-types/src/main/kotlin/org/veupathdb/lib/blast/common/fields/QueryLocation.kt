package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagQueryLocation
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


private const val KeyStart = "start"
private const val KeyStop  = "stop"
private const val DefStart = 0u
private const val DefStop  = 0u


internal fun ParseQueryLocation(js: ObjectNode) =
  js.optObject(FlagQueryLocation) {
    QueryLocation(
      it[KeyStart]?.reqUInt { "$FlagQueryLocation.$KeyStart" } ?: DefStart,
      it[KeyStop]?.reqUInt { "$FlagQueryLocation.$KeyStop" } ?: DefStop
    )
  } ?: QueryLocation()


/**
 * -query_loc `<String>`
 *
 * Location on the query sequence in 1-based offsets (Format: start-stop)
 */
data class QueryLocation(val start: UInt = DefStart, val stop: UInt = DefStop)
  : BlastField
{
  init {
    if (stop < start)
      throw IllegalArgumentException("$FlagQueryLocation stop cannot be greater than the start.  Given value: $start-$stop")
  }

  override val isDefault get() = start == DefStart && stop == DefStop

  override val name: String
    get() = FlagQueryLocation

  override fun appendJson(js: ObjectNode) {
    if (!isDefault) {
      with(js.putObject(FlagQueryLocation)) {
        put(KeyStart, start.toLong())
        put(KeyStop, stop.toLong())
      }
    }
  }

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagQueryLocation) { "$start-$stop" }

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagQueryLocation) { "$start-$stop" }
}
