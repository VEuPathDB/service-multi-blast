package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSubjectBestHit
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optBool
import org.veupathdb.lib.blast.util.put


private const val Def = false


internal fun ParseSubjectBestHit(js: ObjectNode) =
  js.optBool(FlagSubjectBestHit) { SubjectBestHit(it) } ?: SubjectBestHit()


/**
 * -subject_besthit
 *
 * Turn on best hit per subject sequence
 */
@JvmInline
value class SubjectBestHit(val value: Boolean = Def) : BlastField {
  override val isDefault get() = value == Def

  override val name: String
    get() = FlagSubjectBestHit

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagSubjectBestHit, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagSubjectBestHit)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagSubjectBestHit)
}