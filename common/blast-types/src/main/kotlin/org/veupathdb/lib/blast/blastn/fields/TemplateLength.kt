package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagTemplateLength
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.add
import org.veupathdb.lib.blast.util.append
import org.veupathdb.lib.blast.util.put


internal fun ParseTemplateLength(js: ObjectNode): TemplateLength {
  val tmp = js[FlagTemplateLength] ?: return TemplateLength()

  if (!tmp.isIntegralNumber)
    throw IllegalArgumentException("$FlagTemplateLength must be one of 16, 18, 21")

  return TemplateLength(parseTemplateLength(js.intValue()))
}


/**
 * -template_length `<Integer>`
 *
 * Permissible values:
 * * 16
 * * 18
 * * 21
 *
 * Discontiguous MegaBLAST template length
 */
@JvmInline
value class TemplateLength(
  val value: TemplateLengthValue = TemplateLengthValue.None
) : BlastField {
  override val isDefault get() = value == TemplateLengthValue.None

  override val name: String
    get() = FlagTemplateLength

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagTemplateLength, value.value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagTemplateLength, value.value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagTemplateLength, value.value)
}


private fun parseTemplateLength(value: Int): TemplateLengthValue {
  return when(value) {
    16   -> TemplateLengthValue.Sixteen
    18   -> TemplateLengthValue.Eighteen
    21   -> TemplateLengthValue.TwentyOne
    else -> throw IllegalArgumentException("$FlagTemplateLength must be one of 16, 18, 21")
  }
}


enum class TemplateLengthValue {
  Sixteen,
  Eighteen,
  TwentyOne,
  None;

  val value get() = when(this) {
    Sixteen   -> 16
    Eighteen  -> 18
    TwentyOne -> 21
    None      -> 0
  }
}