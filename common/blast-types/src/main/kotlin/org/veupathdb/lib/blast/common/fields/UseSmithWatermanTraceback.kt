package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagUseSWTBack
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseUseSWTBack(js: ObjectNode) =
  js.optBool(FlagUseSWTBack) { UseSmithWatermanTraceback(it) }
    ?: UseSmithWatermanTraceback()


/**
 * -use_sw_tback
 *
 * Compute locally optimal Smith-Waterman alignments?
 */
@JvmInline
value class UseSmithWatermanTraceback(val value: Boolean = false) : BlastField {
  override val isDefault get() = !value

  override val name: String
    get() = FlagUseSWTBack

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagUseSWTBack, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagUseSWTBack)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagUseSWTBack)
}