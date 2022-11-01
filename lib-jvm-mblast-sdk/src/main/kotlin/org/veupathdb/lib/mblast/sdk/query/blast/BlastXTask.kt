package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastXTask {
  BlastX,
  BlastXFast,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(when (this) {
    BlastX     -> "blastx"
    BlastXFast -> "blastx-fast"
  })

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastXTask {
      js.dEnumRequireTextual(BlastXTask::class)

      return when (val v = js.textValue()) {
        "blastx"      -> BlastX
        "blastx-fast" -> BlastXFast
        else          -> throw UnrecognizedEnumException(BlastXTask::class, v)
      }
    }
  }
}