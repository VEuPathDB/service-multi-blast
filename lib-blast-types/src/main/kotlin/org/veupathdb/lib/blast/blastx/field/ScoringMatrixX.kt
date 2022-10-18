package org.veupathdb.lib.blast.blastx.field

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagMatrix
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseScoringMatrixX(js: ObjectNode) =
  js.optString(FlagMatrix) { ScoringMatrixX(parseEnum(it)) } ?: ScoringMatrixX()


/**
 * -matrix `<String>`
 *
 * Scoring matrix name (normally BLOSUM62)
 */
@JvmInline
value class ScoringMatrixX(
  val value: ScoringMatrixXType = ScoringMatrixXType.None
): BlastField {
  override val isDefault get() = value == ScoringMatrixXType.None

  override val name: String
    get() = FlagMatrix

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMatrix, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMatrix, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMatrix, value.value)
}


private fun parseEnum(value: String): ScoringMatrixXType {
  return when (value.uppercase()) {
    "BLOSUM45" -> ScoringMatrixXType.Blosum45
    "BLOSUM50" -> ScoringMatrixXType.Blosum50
    "BLOSUM62" -> ScoringMatrixXType.Blosum62
    "BLOSUM80" -> ScoringMatrixXType.Blosum80
    "BLOSUM90" -> ScoringMatrixXType.Blosum90
    "PAM30"    -> ScoringMatrixXType.Pam30
    "PAM70"    -> ScoringMatrixXType.Pam70
    "PAM250"   -> ScoringMatrixXType.Pam250
    else       -> throw IllegalArgumentException("Invalid $FlagMatrix value: $value")
  }
}


enum class ScoringMatrixXType {
  Blosum45,
  Blosum50,
  Blosum62,
  Blosum80,
  Blosum90,
  Pam30,
  Pam70,
  Pam250,
  None;

  val value
    get() = when (this) {
      Blosum45 -> "BLOSUM45"
      Blosum50 -> "BLOSUM50"
      Blosum62 -> "BLOSUM62"
      Blosum80 -> "BLOSUM80"
      Blosum90 -> "BLOSUM90"
      Pam30    -> "PAM30"
      Pam70    -> "PAM70"
      Pam250   -> "PAM250"
      else     -> "NONE"
    }
}