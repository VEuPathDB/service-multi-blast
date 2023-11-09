package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagTemplateType
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.optString
import org.veupathdb.lib.blast.util.put


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
value class TemplateType(val value: TemplateTypeValue = TemplateTypeValue.None)
  : BlastField
{
  override val isDefault get() = value == TemplateTypeValue.None

  override val name: String
    get() = FlagTemplateType

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTemplateType, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTemplateType, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTemplateType, value.value)

  override fun clone() = this
}


private fun parseEnum(value: String): TemplateTypeValue {
  return when(value) {
    "coding"             -> TemplateTypeValue.Coding
    "coding_and_optimal" -> TemplateTypeValue.CodingAndOptimal
    "optimal"            -> TemplateTypeValue.Optimal
    else                 -> throw IllegalArgumentException("Invalid $FlagTemplateType value: $value")
  }
}

typealias TemplateTypeValue = TemplateTypeType

@Deprecated("this will be removed in favor of the name \"TemplateTypeValue\" in a future release")
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
