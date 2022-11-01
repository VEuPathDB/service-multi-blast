package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class PSIBlastMatrix {
  BLOSUM45,
  BLOSUM50,
  BLOSUM62,
  BLOSUM80,
  BLOSUM90,
  PAM30,
  PAM70,
  PAM250,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(name)

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): PSIBlastMatrix {
      js.dEnumRequireTextual(PSIBlastMatrix::class)

      for (v in values())
        if (js.textValue() == v.name)
          return v

      throw UnrecognizedEnumException(PSIBlastMatrix::class, js.textValue())
    }
  }
}