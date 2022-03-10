package mb.lib.blast;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.NumericNode
import mb.api.model.io.JsonKeys;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.TBlastN;
import org.veupathdb.lib.blast.consts.Flag;
import org.veupathdb.lib.blast.field.*

fun XTBlastN.fromLegacyJSON(raw: ArrayNode) {
  // skip first element as it is just the tool name
  for (i in 1 until raw.size()) {
    val key   = raw[i][0].textValue()
    val value = raw[i][1]

    when (value.nodeType) {
      JsonNodeType.STRING  -> fromTextJSON(key, value.textValue())
      JsonNodeType.NUMBER  -> fromNumberJSON(key, value as NumericNode)
      JsonNodeType.BOOLEAN -> fromBoolJSON(key, value.booleanValue())
      else                 -> { /* do nothing */ }
    }
  }
}

fun XTBlastN.fromNumberJSON(key: String, value: NumericNode) = when (key) {
  Flag.WordSize                     -> wordSize = value.longValue()
  Flag.GapOpen                      -> gapOpen = value.intValue()
  Flag.GapExtend                    -> gapExtend = value.intValue()
  Flag.DBGenCode                    -> dbGenCode = value.shortValue()
  Flag.MaxIntronLength              -> maxIntronLength = value.longValue()
  Flag.Threshold                    -> threshold = value.doubleValue()
  Flag.CullingLimit                 -> cullingLimit = value.longValue()
  Flag.ExtensionDropoffPrelimGapped -> extensionDropoffPrelimGapped = value.doubleValue()
  Flag.ExtensionDropoffFinalGapped  -> extensionDropoffFinalGapped = value.doubleValue()
  Flag.NumThreads                   -> numThreads = value.shortValue()
  Flag.BestHitOverhang              -> bestHitOverhang = value.doubleValue()
  Flag.BestHitScoreEdge             -> bestHitScoreEdge = value.doubleValue()
  else -> (this as TBlastN).fromNumberJSON(key, value)
}

fun XTBlastN.fromTextJSON(key: String, value: String) = when (key) {
  Flag.Task                         -> task = TBlastNTask.fromString(value)
  Flag.Matrix                       -> matrix = ScoringMatrix.fromString(value)
  Flag.CompBasedStats               -> compBasedStats = CompBasedStatsLong.fromString(value)
  Flag.SubjectFile                  -> subjectFile = value
  Flag.SubjectLocation              -> subjectLocation = Location.fromString(value)
  Flag.Seg                          -> seg = Seg.fromString(value)
  Flag.DBSoftMask                   -> dbSoftMask = value
  Flag.DBHardMask                   -> dbHardMask = value
  Flag.InPSSMFile                   -> inPSSMFile = value
  else -> (this as TBlastN).fromTextJSON(key, value)
}

fun XTBlastN.fromBoolJSON(key: String, value: Boolean) = when (key) {
  Flag.SumStats                     -> sumStats = value
  Flag.UngappedAlignmentsOnly       -> ungappedAlignmentsOnly = value
  Flag.UseSmithWatermanTraceback    -> useSmithWatermanTraceback = value
  Flag.SubjectBestHit               -> subjectBestHit = value
  else -> (this as TBlastN).fromBoolJSON(key, value)

}

class XTBlastN : TBlastN() {
  @JsonGetter(JsonKeys.Tool)
  fun tool(): BlastTool = super.tool
  // Added to avoid having to set up a custom serialization config for this class.
  @Suppress("UNUSED_PARAMETER")
  fun setTool(ignored: BlastTool) {}
}
