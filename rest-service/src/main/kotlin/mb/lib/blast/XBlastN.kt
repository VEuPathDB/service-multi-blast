@file:Suppress("NOTHING_TO_INLINE")

package mb.lib.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.NumericNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastN
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.BlastWithLists
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.field.*

inline fun BlastN.fromLegacyJSON(raw: ArrayNode) {
  val it = raw.elements()

  // Skip the first entry as it is just the blast tool name
  it.next()

  while (it.hasNext()) {
    val arr = it.next()
    val key = arr.get(0).textValue()
    val value = arr.get(1)

    fromLegacyJSON(key, value)
  }
}

inline fun BlastN.fromLegacyJSON(key: String, value: JsonNode) = when(value.nodeType) {
  JsonNodeType.NUMBER  -> fromNumberJSON(key, value as NumericNode)
  JsonNodeType.STRING  -> fromTextJSON(key, value.textValue())
  JsonNodeType.BOOLEAN -> fromBoolJSON(key, value.booleanValue())
  else                 -> { /* Invalid type, ignore it */ }
}

inline fun BlastN.fromBoolJSON(key: String, value: Boolean) = when(key) {
  Flag.UseIndex               -> useIndex               = value
  Flag.SumStats               -> sumStats               = value
  Flag.NonGreedy              -> nonGreedy              = value
  Flag.UngappedAlignmentsOnly -> ungappedAlignmentsOnly = value
  Flag.SubjectBestHit         -> subjectBestHit         = value
  else                        -> (this as BlastWithLists).fromBoolJSON(key, value)
}

inline fun BlastN.fromTextJSON(key: String, value: String) = when(key) {
  Flag.Task            -> task            = BlastNTask.fromString(value)
  Flag.Strand          -> strand          = Strand.fromString(value)
  Flag.Dust            -> dust            = Dust.fromString(value)
  Flag.SubjectLocation -> subjectLocation = Location.fromString(value)
  Flag.DBSoftMask      -> dbSoftMask      = value
  Flag.DBHardMask      -> dbHardMask      = value
  Flag.IndexName       -> indexName       = value
  Flag.SubjectFile     -> subjectFile     = value
  Flag.FilteringDB     -> filteringDB     = value
  Flag.WindowMaskerDB  -> windowMaskerDB  = value
  Flag.TemplateType    -> templateType    = TemplateType.fromString(value)
  else                 -> (this as BlastWithLists).fromTextJSON(key, value)
}

inline fun BlastN.fromNumberJSON(key: String, value: NumericNode) = when(key) {
  Flag.WordSize                     -> wordSize                     = value.longValue()
  Flag.GapOpen                      -> gapOpen                      = value.intValue()
  Flag.GapExtend                    -> gapExtend                    = value.intValue()
  Flag.Penalty                      -> penalty                      = value.intValue()
  Flag.Reward                       -> reward                       = value.longValue()
  Flag.PercentIdentity              -> percentIdentity              = value.doubleValue()
  Flag.WindowMaskerTaxID            -> windowMaskerTaxID            = value.intValue()
  Flag.CullingLimit                 -> cullingLimit                 = value.longValue()
  Flag.TemplateLength               -> templateLength               = TemplateLength.fromIntValue(value.intValue())
  Flag.ExtensionDropoffPrelimGapped -> extensionDropoffPrelimGapped = value.doubleValue()
  Flag.ExtensionDropoffFinalGapped  -> extensionDropoffFinalGapped  = value.doubleValue()
  Flag.MinRawGappedScore            -> minRawGappedScore            = value.intValue()
  Flag.OffDiagonalRange             -> offDiagonalRange             = value.longValue()
  Flag.BestHitOverhang              -> bestHitOverhang              = value.doubleValue()
  Flag.BestHitScoreEdge             -> bestHitScoreEdge             = value.doubleValue()
  Flag.NumThreads                   -> numThreads                   = value.shortValue()
  else                              -> (this as BlastWithLists).fromNumberJSON(key, value)
}

/**
 * Beta BlastN compatibility overlay.
 * <p>
 * Contains Jackson annotations mapping the legacy config
 */
class XBlastN: BlastN()
{
  @JsonGetter(JsonKeys.Tool)
  fun tool(): BlastTool = super.getTool()

  // Added to avoid having to set up a custom serialization config for this class.
  @Suppress("UNUSED_PARAMETER")
  fun setTool(ignored: BlastTool) {}
}
