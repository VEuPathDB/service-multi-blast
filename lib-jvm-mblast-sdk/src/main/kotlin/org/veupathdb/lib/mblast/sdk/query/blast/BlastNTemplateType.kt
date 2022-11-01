package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastNTemplateType {
  Coding,
  CodingAndOptimal,
  Optimal,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(
    when (this) {
      Coding           -> "coding"
      CodingAndOptimal -> "coding-and-optimal"
      Optimal          -> "optimal"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastNTemplateType {
      js.dEnumRequireTextual(BlastNTemplateType::class)

      return when (val v = js.textValue()) {
        "coding"             -> Coding
        "coding-and-optimal" -> CodingAndOptimal
        "optimal"            -> Optimal
        else                 -> throw UnrecognizedEnumException(BlastNTemplateType::class, v)
      }
    }
  }
}