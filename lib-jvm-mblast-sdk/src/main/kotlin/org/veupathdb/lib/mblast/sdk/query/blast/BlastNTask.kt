package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastNTask {
  BlastN,
  BlastNShort,
  DCMegaBlast,
  MegaBlast,
  RMBlastN,
  ;

  @JsonValue
  fun toJson() = TextNode(
    when (this) {
      BlastN      -> "blastn"
      BlastNShort -> "blastn-short"
      DCMegaBlast -> "dc-megablast"
      MegaBlast   -> "megablast"
      RMBlastN    -> "rmblastn"
    }
  )

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastNTask {
      js.dEnumRequireTextual(BlastNTask::class)

      return when (val v = js.textValue()) {
        "blastn"       -> BlastN
        "blastn-short" -> BlastNShort
        "dc-megablast" -> DCMegaBlast
        "megablast"    -> MegaBlast
        "rmblastn"     -> RMBlastN
        else           -> throw UnrecognizedEnumException(BlastNTask::class, v)
      }
    }
  }
}