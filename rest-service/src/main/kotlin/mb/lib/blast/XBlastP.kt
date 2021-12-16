package mb.lib.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.NumericNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastP
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.*
import org.veupathdb.lib.blast.field.CompBasedStatsLong.Companion

fun XBlastP.fromLegacyJSON(node: ArrayNode) {
  val it  = node.elements()

  // Skip the first entry as it is just the blast tool name
  it.next()

  while (it.hasNext()) {
    val arr = it.next()
    val key = arr.get(0).textValue()
    val value = arr.get(1)

    when (value.nodeType) {
      JsonNodeType.STRING  -> fromTextJSON(key, value.textValue())
      JsonNodeType.NUMBER  -> fromNumberJSON(key, value as NumericNode)
      JsonNodeType.BOOLEAN -> fromBoolJSON(key, value.booleanValue())
      else                 -> { /* Do nothing. */ }
    }
  }
}

fun XBlastP.fromNumberJSON(key: String, value: NumericNode) = when (key) {
  Flag.WordSize                     -> wordSize                     = value.longValue()
  Flag.GapOpen                      -> gapOpen                      = value.intValue()
  Flag.GapExtend                    -> gapExtend                    = value.intValue()
  Flag.Threshold                    -> threshold                    = value.doubleValue()
  Flag.CullingLimit                 -> cullingLimit                 = value.longValue()
  Flag.ExtensionDropoffPrelimGapped -> extensionDropoffPrelimGapped = value.doubleValue()
  Flag.ExtensionDropoffFinalGapped  -> extensionDropoffFinalGapped  = value.doubleValue()
  Flag.NumThreads                   -> numThreads                   = value.shortValue()
  Flag.BestHitOverhang              -> bestHitOverhang              = value.doubleValue()
  Flag.BestHitScoreEdge             -> bestHitScoreEdge             = value.doubleValue()
  else                              -> (this as BlastP).fromNumberJSON(key, value)
}

fun XBlastP.fromTextJSON(key: String, value: String) = when (key) {
  Flag.Task            -> task            = BlastPTask.fromString(value)
  Flag.Matrix          -> matrix          = ScoringMatrix.fromString(value)
  Flag.CompBasedStats  -> compBasedStats  = CompBasedStatsLong.fromString(value)
  Flag.SubjectFile     -> subjectFile     = value
  Flag.SubjectLocation -> subjectLocation = Location.fromString(value)
  Flag.Seg             -> seg             = Seg.fromString(value)
  Flag.DBSoftMask      -> dbSoftMask      = value
  Flag.DBHardMask      -> dbHardMask      = value
  else                 -> (this as BlastP).fromTextJSON(key, value)
}

fun XBlastP.fromBoolJSON(key: String, value: Boolean) = when (key) {
  Flag.UngappedAlignmentsOnly    -> ungappedAlignmentsOnly    = value
  Flag.UseSmithWatermanTraceback -> useSmithWatermanTraceback = value
  Flag.SubjectBestHit            -> subjectBestHit            = value
  else                           -> (this as BlastP).fromBoolJSON(key, value)
}

/**
 * Beta BlastP compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
class XBlastP: BlastP()
{
  @JsonGetter(JsonKeys.Tool)
  fun tool(): BlastTool = super.tool

  // Added to avoid having to set up a custom serialization config for this class.
  @Suppress("UNUSED_PARAMETER")
  fun setTool(ignored: BlastTool) {}
}
