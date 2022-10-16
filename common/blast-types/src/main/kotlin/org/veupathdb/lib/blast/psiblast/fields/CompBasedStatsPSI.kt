package org.veupathdb.lib.blast.psiblast.fields

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagCompBasedStats
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


private val Def = CompBasedStatsPSIValue.ConditionalScoreAdjustment


internal fun ParseCompBasedStatsPSI(js: ObjectNode) =
  js[FlagCompBasedStats]?.let { CompBasedStatsPSI(parseEnum(it)) } ?: CompBasedStatsPSI()


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
 * Default = `2'
 */
@JvmInline
value class CompBasedStatsPSI(val value: CompBasedStatsPSIValue = Def)
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


private fun parseEnum(j: JsonNode): CompBasedStatsPSIValue {
  if (j.isTextual)
    return when (val v = j.textValue()) {
      "d", "D"      -> CompBasedStatsPSIValue.ConditionalScoreAdjustment
      "0", "f", "F" -> CompBasedStatsPSIValue.None
      "1"           -> CompBasedStatsPSIValue.Statistics
      "2", "t", "T" -> CompBasedStatsPSIValue.ConditionalScoreAdjustment
      "3"           -> CompBasedStatsPSIValue.UnconditionalScoreAdjustment
      else          -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  if (j.isIntegralNumber)
    return when (val v = j.intValue()) {
      0    -> CompBasedStatsPSIValue.None
      1    -> CompBasedStatsPSIValue.Statistics
      2    -> CompBasedStatsPSIValue.ConditionalScoreAdjustment
      3    -> CompBasedStatsPSIValue.UnconditionalScoreAdjustment
      else -> throw IllegalArgumentException("Invalid value \"$v\" for $FlagCompBasedStats.")
    }

  throw IllegalArgumentException("$FlagCompBasedStats must be a string or int value.")
}


enum class CompBasedStatsPSIValue {
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