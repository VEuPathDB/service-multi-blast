package mb.lib.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.NumericNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.TBlastX
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.Location
import org.veupathdb.lib.blast.field.ScoringMatrix
import org.veupathdb.lib.blast.field.Seg
import org.veupathdb.lib.blast.field.Strand


fun XTBlastX.fromLegacyJSON(raw: ArrayNode) {
  // skip first node as it is just the tool name
  for (i in 1 until raw.size()) {
    val key   = raw[i][0].textValue()
    val value = raw[i][1]

    when (value.nodeType) {
      JsonNodeType.NUMBER  -> fromNumberJSON(key, value as NumericNode)
      JsonNodeType.STRING  -> fromTextJSON(key, value.textValue())
      JsonNodeType.BOOLEAN -> fromBoolJSON(key, value.booleanValue())
      else                 -> {/* do nothing */}
    }
  }
}

fun XTBlastX.fromTextJSON(key: String, value: String) = when (key) {
  Flag.Strand -> strand = Strand.fromString(value)
  Flag.Matrix           -> matrix = ScoringMatrix.fromString(value)
  Flag.SubjectFile      -> subjectFile = value
  Flag.SubjectLocation  -> subjectLocation = Location.fromString(value)
  Flag.Seg              -> seg = Seg.fromString(value)
  Flag.DBSoftMask       -> dbSoftMask = value
  Flag.DBHardMask       -> dbHardMask = value
  else        -> (this as TBlastX).fromTextJSON(key, value)
}

fun XTBlastX.fromNumberJSON(key: String, value: NumericNode) = when (key) {
  Flag.QueryGenCode     -> queryGenCode = value.shortValue()
  Flag.WordSize         -> wordSize = value.longValue()
  Flag.MaxIntronLength  -> maxIntronLength = value.longValue()
  Flag.Threshold        -> threshold = value.doubleValue()
  Flag.DBGenCode        -> dbGenCode = value.shortValue()
  Flag.CullingLimit     -> cullingLimit = value.longValue()
  Flag.BestHitOverhang  -> bestHitOverhang = value.doubleValue()
  Flag.BestHitScoreEdge -> bestHitScoreEdge = value.doubleValue()
  Flag.NumThreads       -> numThreads = value.shortValue()
  else                  -> (this as TBlastX).fromNumberJSON(key, value)
}

fun XTBlastX.fromBoolJSON(key: String, value: Boolean) = when (key) {
  Flag.SumStats         -> sumStats = value
  Flag.SubjectBestHit   -> subjectBestHit = value
  else -> (this as TBlastX).fromBoolJSON(key, value)
}

class XTBlastX : TBlastX() {
  @JsonGetter(JsonKeys.Tool)
  fun tool() = super.getTool()!!

  // Added to avoid having to set up a custom serialization config for this class.
  @Suppress("UNUSED_PARAMETER")
  fun setTool(ignored: BlastTool) {}
}