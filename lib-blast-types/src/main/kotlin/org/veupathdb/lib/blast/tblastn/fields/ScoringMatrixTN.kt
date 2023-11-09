package org.veupathdb.lib.blast.tblastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagMatrix
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put
import kotlin.text.StringBuilder
import kotlin.text.lowercase
import kotlin.text.uppercase


internal fun ParseScoringMatrixTN(js: ObjectNode) =
  js.optString(FlagMatrix) { ScoringMatrixTN(parseMatrix(it)) }
    ?: ScoringMatrixTN()


/**
 * -matrix `<String>`
 *
 * Scoring matrix name (normally BLOSUM62)
 */
@JvmInline
value class ScoringMatrixTN(val value: ScoringMatrixTNType = ScoringMatrixTNType.None)
  : BlastField
{
  override val isDefault get() = value == ScoringMatrixTNType.None

  override val name: String
    get() = FlagMatrix

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagMatrix, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagMatrix, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagMatrix, value.value)

  override fun clone() = this
}


private fun parseMatrix(value: String) =
  when(value.lowercase()) {
    "blosum45" -> ScoringMatrixTNType.Blosum45
    "blosum50" -> ScoringMatrixTNType.Blosum50
    "blosum62" -> ScoringMatrixTNType.Blosum62
    "blosum80" -> ScoringMatrixTNType.Blosum80
    "blosum90" -> ScoringMatrixTNType.Blosum90
    "pam30"    -> ScoringMatrixTNType.Pam30
    "pam70"    -> ScoringMatrixTNType.Pam70
    "pam250"   -> ScoringMatrixTNType.Pam250
    "identity" -> ScoringMatrixTNType.Identity
    "none"     -> ScoringMatrixTNType.None
    else       -> throw IllegalArgumentException("Invalid value $value for enum BlastPMatrixType")
  }


enum class ScoringMatrixTNType {
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