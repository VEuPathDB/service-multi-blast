package org.veupathdb.lib.blast.blastp.field

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMatrix
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseScoringMatrixP(js: ObjectNode) =
  js.optString(FlagMatrix) { ScoringMatrixP(parseMatrix(it)) }
    ?: ScoringMatrixP()


/**
 * -matrix `<String>`
 *
 * Scoring matrix name (normally BLOSUM62)
 */
@JvmInline
value class ScoringMatrixP(val value: ScoringMatrixPType = ScoringMatrixPType.None)
  : BlastField
{
  override val isDefault get() = value == ScoringMatrixPType.None

  override val name: String
    get() = FlagMatrix

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMatrix, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMatrix, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMatrix, value.value)
}


private fun parseMatrix(value: String) =
  when(value.lowercase()) {
    "blosum45" -> ScoringMatrixPType.Blosum45
    "blosum50" -> ScoringMatrixPType.Blosum50
    "blosum62" -> ScoringMatrixPType.Blosum62
    "blosum80" -> ScoringMatrixPType.Blosum80
    "blosum90" -> ScoringMatrixPType.Blosum90
    "pam30"    -> ScoringMatrixPType.Pam30
    "pam70"    -> ScoringMatrixPType.Pam70
    "pam250"   -> ScoringMatrixPType.Pam250
    "identity" -> ScoringMatrixPType.Identity
    "none"     -> ScoringMatrixPType.None
    else       -> throw IllegalArgumentException("Invalid value $value for enum BlastPMatrixType")
  }


enum class ScoringMatrixPType {
  Blosum45,
  Blosum50,
  Blosum62,
  Blosum80,
  Blosum90,
  Pam30,
  Pam70,
  Pam250,
  Identity,
  None;

  val value get() = name.uppercase()
}