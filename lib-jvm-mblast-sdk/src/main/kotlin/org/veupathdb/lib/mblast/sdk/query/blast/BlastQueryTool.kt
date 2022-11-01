package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import org.veupathdb.lib.mblast.sdk.util.UnrecognizedEnumException
import org.veupathdb.lib.mblast.sdk.util.dEnumRequireTextual

enum class BlastQueryTool {
  BlastN,
  BlastP,
  BlastX,
  DeltaBlast,
  PSIBlast,
  RPSBlast,
  RPSTBlastN,
  TBlastN,
  TBlastX,
  ;

  @JsonValue
  fun toJson() = TextNode(name.lowercase())

  companion object {

    @JvmStatic
    @JsonCreator
    fun fromJson(js: JsonNode): BlastQueryTool {
      js.dEnumRequireTextual(BlastQueryTool::class)

      return when (val v = js.textValue()) {
        "blastn"     -> BlastN
        "blastp"     -> BlastP
        "blastx"     -> BlastX
        "deltablast" -> DeltaBlast
        "psiblast"   -> PSIBlast
        "rpsblast"   -> RPSBlast
        "rpstblastn" -> RPSTBlastN
        "tblastn"    -> TBlastN
        "tblastx"    -> TBlastX
        else         -> throw UnrecognizedEnumException(BlastQueryTool::class, v)
      }
    }
  }
}