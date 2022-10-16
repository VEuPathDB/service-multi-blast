package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSortHits
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseSortHits(js: ObjectNode) =
  js.optInt(FlagSortHits) { SortHits(parse(it)) } ?: SortHits()


/**
 * -sorthits `<Integer, (>=0 and =<4)>`
 *
 * Sorting option for hits.
 *
 * Alignment view options:
 * * `0` = Sort by evalue
 * * `1` = Sort by bit score
 * * `2` = Sort by total score
 * * `3` = Sort by percent identity
 * * `4` = Sort by query coverage
 *
 * Not applicable for -outfmt > `4`
 */
@JvmInline
value class SortHits(val value: HitSorting = HitSorting.None) : BlastField {
  override val isDefault get() = value == HitSorting.None

  override val name: String
    get() = FlagSortHits

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSortHits, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSortHits, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSortHits, value.value)
}


////////////////////////////////////////////////////////////////////////////////


private inline fun parse(i: Int): HitSorting {
  try {
    return HitSorting.values()[i]
  } catch (e: IndexOutOfBoundsException) {
    throw IllegalArgumentException("Invalid $FlagSortHits value: $i")
  }
}


enum class HitSorting {
  ByExpectValue,
  ByBitScore,
  ByTotalScore,
  ByPercentIdentity,
  ByQueryCoverage,
  None;

  val value = ordinal
}