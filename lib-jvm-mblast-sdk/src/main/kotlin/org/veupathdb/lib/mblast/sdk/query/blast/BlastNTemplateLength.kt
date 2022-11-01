package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ShortNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastNTemplateLength {
  Sixteen,
  Eighteen,
  TwentyOne,
  ;

  @JsonValue
  fun toJson(): JsonNode = ShortNode(when (this) {
    Sixteen   -> 16
    Eighteen  -> 18
    TwentyOne -> 21
  })

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastNTemplateLength {
      js.dEnumRequireTextual(BlastNTemplateLength::class)

      return when (val v = js.asInt()) {
        16   -> Sixteen
        18   -> Eighteen
        21   -> TwentyOne
        else -> throw UnrecognizedEnumException(BlastNTemplateLength::class, v)
      }
    }
  }
}