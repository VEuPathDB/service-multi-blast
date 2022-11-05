package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode

enum class TargetSite {
  AmoebaDB,
  CryptoDB,
  FungiDB,
  GiardiaDB,
  HostDB,
  MicrosporidiaDB,
  PiroplasmaDB,
  PlasmoDB,
  ToxoDB,
  TrichDB,
  TriTrypDB,
  VectorBase,
  VEuPathDB,
  ;

  @JsonValue
  fun toJson() = TextNode(name)

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): TargetSite {
      if (!js.isTextual)
        throw IllegalArgumentException()

      for (v in values())
        if (v.name == js.textValue())
          return v

      throw IllegalArgumentException()
    }
  }
}