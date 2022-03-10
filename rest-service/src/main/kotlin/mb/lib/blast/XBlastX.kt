package mb.lib.blast


import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.NumericNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.BlastX
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.*

fun XBlastX.fromLegacyJSON(node: ArrayNode) {
  for (i in 1 until node.size()) {
    val key   = node[i][0].textValue()
    val value = node[i][1]

    when (value.nodeType) {
      JsonNodeType.STRING  -> fromTextJSON(key, value.textValue())
      JsonNodeType.NUMBER  -> fromNumberJSON(key, value as NumericNode)
      JsonNodeType.BOOLEAN -> fromBoolJSON(key, value.booleanValue())
      else                 -> { /* do nothing */ }
    }
  }
}

fun XBlastX.fromTextJSON(key: String, value: String) = when (key) {
  Flag.Strand          -> strand          = Strand.fromString(value)
  Flag.Task            -> task            = BlastXTask.fromString(value)
  Flag.Matrix          -> matrix          = ScoringMatrix.fromString(value)
  Flag.CompBasedStats  -> compBasedStats  = CompBasedStatsLong.fromString(value)
  Flag.SubjectFile     -> subjectFile     = value
  Flag.SubjectLocation -> subjectLocation = Location.fromString(value)
  Flag.Seg             -> seg             = Seg.fromString(value)
  Flag.DBSoftMask      -> dbSoftMask      = value
  Flag.DBHardMask      -> dbHardMask      = value
  else                 -> (this as BlastX).fromTextJSON(key, value)
}

fun XBlastX.fromNumberJSON(key: String, value: NumericNode) = when (key) {
  Flag.QueryGenCode                 -> queryGenCode                 = value.shortValue()
  Flag.WordSize                     -> wordSize                     = value.longValue()
  Flag.GapOpen                      -> gapOpen                      = value.intValue()
  Flag.GapExtend                    -> gapExtend                    = value.intValue()
  Flag.MaxIntronLength              -> maxIntronLength              = value.longValue()
  Flag.Threshold                    -> threshold                    = value.doubleValue()
  Flag.CullingLimit                 -> cullingLimit                 = value.longValue()
  Flag.ExtensionDropoffPrelimGapped -> extensionDropoffPrelimGapped = value.doubleValue()
  Flag.ExtensionDropoffFinalGapped  -> extensionDropoffFinalGapped  = value.doubleValue()
  Flag.NumThreads                   -> numThreads                   = value.shortValue()
  Flag.BestHitOverhang              -> bestHitOverhang              = value.doubleValue()
  Flag.BestHitScoreEdge             -> bestHitScoreEdge             = value.doubleValue()
  else                              -> (this as BlastX).fromNumberJSON(key, value)
}

fun XBlastX.fromBoolJSON(key: String, value: Boolean) = when (key) {
  Flag.SumStats                  -> sumStats                  = value
  Flag.UngappedAlignmentsOnly    -> ungappedAlignmentsOnly    = value
  Flag.UseSmithWatermanTraceback -> useSmithWatermanTraceback = value
  Flag.SubjectBestHit            -> subjectBestHit            = value
  else                           -> (this as BlastX).fromBoolJSON(key, value)
}

/**
 * Beta BlastX compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
class XBlastX: BlastX() {
  @JsonGetter(JsonKeys.Tool)
  fun tool(): BlastTool {
    return super.tool
  }

  // Added to avoid having to set up a custom serialization config for this class.
  @Suppress("UNUSED_PARAMETER")
  fun setTool(ignored: BlastTool) {}
}
