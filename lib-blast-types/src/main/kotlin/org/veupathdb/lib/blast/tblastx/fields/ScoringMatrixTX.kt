package org.veupathdb.lib.blast.tblastx.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagMatrix
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseScoringMatrixTX(js: ObjectNode) =
  js.optString(FlagMatrix) { ScoringMatrixTX(parseMatrix(it)) }
    ?: ScoringMatrixTX()


/**
 * -matrix `<String>`
 *
 * Scoring matrix name (normally BLOSUM62)
 */
@JvmInline
value class ScoringMatrixTX(val value: ScoringMatrixTXType = ScoringMatrixTXType.None)
  : BlastField
{
  override val isDefault get() = value == ScoringMatrixTXType.None

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
    "blosum45" -> ScoringMatrixTXType.Blosum45
    "blosum50" -> ScoringMatrixTXType.Blosum50
    "blosum62" -> ScoringMatrixTXType.Blosum62
    "blosum80" -> ScoringMatrixTXType.Blosum80
    "blosum90" -> ScoringMatrixTXType.Blosum90
    "pam30"    -> ScoringMatrixTXType.Pam30
    "pam70"    -> ScoringMatrixTXType.Pam70
    "pam250"   -> ScoringMatrixTXType.Pam250
    "none"     -> ScoringMatrixTXType.None
    else       -> throw IllegalArgumentException("Invalid value $value for enum BlastPMatrixType")
  }


enum class ScoringMatrixTXType {
  Blosum45,
  Blosum50,
  Blosum62,
  Blosum80,
  Blosum90,
  Pam30,
  Pam70,
  Pam250,
  None;

  val value get() = name.uppercase()
}