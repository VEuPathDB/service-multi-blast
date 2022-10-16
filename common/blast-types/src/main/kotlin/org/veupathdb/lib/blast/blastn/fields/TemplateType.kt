package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTemplateType
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseTemplateType(js: ObjectNode) =
  js.optString(FlagTemplateType) { TemplateType(parseEnum(it)) }
    ?: TemplateType()


/**
 * -template_type `<String>`
 *
 * Permissible Values:
 * * coding
 * * coding_and_optimal
 * * optimal
 *
 * Discontiguous MegaBLAST template type
 */
@JvmInline
value class TemplateType(val value: TemplateTypeType = TemplateTypeType.None)
  : BlastField
{
  override val isDefault get() = value == TemplateTypeType.None

  override val name: String
    get() = FlagTemplateType

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTemplateType, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTemplateType, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTemplateType, value.value)
}


private fun parseEnum(value: String): TemplateTypeType {
  return when(value) {
    "coding"             -> TemplateTypeType.Coding
    "coding_and_optimal" -> TemplateTypeType.CodingAndOptimal
    "optimal"            -> TemplateTypeType.Optimal
    else                 -> throw IllegalArgumentException("Invalid $FlagTemplateType value: $value")
  }
}


enum class TemplateTypeType {
  Coding,
  CodingAndOptimal,
  Optimal,
  None;

  val value get() = when(this) {
    Coding           -> "coding"
    CodingAndOptimal -> "coding_and_optimal"
    Optimal          -> "optimal"
    None             -> "none"
  }
}
