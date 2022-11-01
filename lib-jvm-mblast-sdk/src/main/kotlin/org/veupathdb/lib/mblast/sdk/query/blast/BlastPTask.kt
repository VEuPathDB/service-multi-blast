package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastPTask {
  BlastP,
  BlastPFast,
  BlastPShort,
  ;

  @JsonValue
  fun toJson(): JsonNode = TextNode(when (this) {
    BlastP      -> "blastp"
    BlastPFast  -> "blastp-fast"
    BlastPShort -> "blastp-short"
  })

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastPTask {
      js.dEnumRequireTextual(BlastPTask::class)

      return when (val v = js.textValue()) {
        "blastp"       -> BlastP
        "blastp-fast"  -> BlastPFast
        "blastp-short" -> BlastPShort
        else           -> throw UnrecognizedEnumException(BlastPTask::class, v)
      }
    }
  }
}