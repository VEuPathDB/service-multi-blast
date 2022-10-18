package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagNonGreedy
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


private const val Def = false


internal fun ParseNonGreedy(js: ObjectNode) =
  js.optBool(FlagNonGreedy) { NonGreedy(it) } ?: NonGreedy()


/**
 * -no_greedy
 *
 * Use non-greedy dynamic programming extension
 */
@JvmInline
value class NonGreedy(val value: Boolean = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagNonGreedy

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagNonGreedy, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagNonGreedy)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagNonGreedy)
}