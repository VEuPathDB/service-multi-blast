package org.veupathdb.lib.blast.tblastn.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCompBasedStats
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


private val Def = CompBasedStatsTNValue.ConditionalScoreAdjustment


internal fun ParseCompBasedStatsTN(js: ObjectNode) =
  js[FlagCompBasedStats]?.let { CompBasedStatsTN(parseEnum(it)) } ?: CompBasedStatsTN()


/**
 * -comp_based_stats `<String>`
 *
 * Use composition-based statistics:
 * * `D` or `d`: default (equivalent to 2)
 * * `0` or `F` or `f`: No composition-based statistics
 * * `1`: Composition-based statistics as in NAR 29:2994-3005, 2001
 * * `2` or `T` or `t` : Composition-based score adjustment as in Bioinformatics
 *   21:902-911, 2005, conditioned on sequence properties
 * * `3`: Composition-based score adjustment as in Bioinformatics 21:902-911,
 *   2005, unconditionally
 *
 * Default = `2`
 */
@JvmInline
value class CompBasedStatsTN(val value: CompBasedStatsTNValue = Def)
  : BlastField
{
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagCompBasedStats

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagCompBasedStats, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagCompBasedStats, value.value)
}


private fun parseEnum(j: JsonNode): CompBasedStatsTNValue {
  if (j.isTextual)
    return when (val v = j.textValue()) {
      "d", "D"      -> CompBasedStatsTNValue.ConditionalScoreAdjustment
      "0", "f", "F" -> CompBasedStatsTNValue.None
      "1"           -> CompBasedStatsTNValue.Statistics
      "2", "t", "T" -> CompBasedStatsTNValue.ConditionalScoreAdjustment
      "3"           -> CompBasedStatsTNValue.UnconditionalScoreAdjustment
      else          -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  if (j.isIntegralNumber)
    return when (val v = j.intValue()) {
      0    -> CompBasedStatsTNValue.None
      1    -> CompBasedStatsTNValue.Statistics
      2    -> CompBasedStatsTNValue.ConditionalScoreAdjustment
      3    -> CompBasedStatsTNValue.UnconditionalScoreAdjustment
      else -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  throw IllegalArgumentException("$FlagCompBasedStats must be a string or int value.")
}


enum class CompBasedStatsTNValue {
  None,
  Statistics,
  ConditionalScoreAdjustment,
  UnconditionalScoreAdjustment;

  val value
    get() = when (this) {
      None                         -> 0
      Statistics                   -> 1
      ConditionalScoreAdjustment   -> 2
      UnconditionalScoreAdjustment -> 3
    }
}