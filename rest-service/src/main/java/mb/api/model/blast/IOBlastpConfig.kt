package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.impl.IOBlastpConfigImpl
import mb.api.model.io.JsonKeys
import mb.lib.blast.model.CompositionBasedStats
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.field.BlastPTask
import org.veupathdb.lib.blast.field.ScoringMatrix
import org.veupathdb.lib.blast.field.Seg

@JsonTypeName("blastp")
@JsonDeserialize(`as` = IOBlastpConfigImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOBlastpConfig : IOBlastWithLists {
  @get:JsonProperty(JsonKeys.Task)
  @set:JsonProperty(JsonKeys.Task)
  var task: BlastPTask?

  @get:JsonProperty(JsonKeys.WordSize)
  @set:JsonProperty(JsonKeys.WordSize)
  var wordSize: Int?

  @get:JsonProperty(JsonKeys.GapOpen)
  @set:JsonProperty(JsonKeys.GapOpen)
  var gapOpen: Int?

  @get:JsonProperty(JsonKeys.GapExtend)
  @set:JsonProperty(JsonKeys.GapExtend)
  var gapExtend: Int?

  @get:JsonProperty(JsonKeys.Matrix)
  @set:JsonProperty(JsonKeys.Matrix)
  var matrix: ScoringMatrix?

  @get:JsonProperty(JsonKeys.Threshold)
  @set:JsonProperty(JsonKeys.Threshold)
  var threshold: Double?

  @get:JsonProperty(JsonKeys.CompositionBasedStats)
  @set:JsonProperty(JsonKeys.CompositionBasedStats)
  var compBasedStats: CompositionBasedStats?

  @get:JsonProperty(JsonKeys.Seg)
  @set:JsonProperty(JsonKeys.Seg)
  var seg: Seg?

  @get:JsonProperty(JsonKeys.SoftMasking)
  @set:JsonProperty(JsonKeys.SoftMasking)
  var softMasking: Boolean?

  @get:JsonProperty(JsonKeys.DBSoftMask)
  @set:JsonProperty(JsonKeys.DBSoftMask)
  var dbSoftMask: String?

  @get:JsonProperty(JsonKeys.DBHardMask)
  @set:JsonProperty(JsonKeys.DBHardMask)
  var dbHardMask: String?

  @get:JsonProperty(JsonKeys.CullingLimit)
  @set:JsonProperty(JsonKeys.CullingLimit)
  var cullingLimit: Int?

  @get:JsonProperty(JsonKeys.BestHitOverhang)
  @set:JsonProperty(JsonKeys.BestHitOverhang)
  var bestHitOverhang: Double?

  @get:JsonProperty(JsonKeys.BestHitScoreEdge)
  @set:JsonProperty(JsonKeys.BestHitScoreEdge)
  var bestHitScoreEdge: Double?

  @get:JsonProperty(JsonKeys.SubjectBestHit)
  @set:JsonProperty(JsonKeys.SubjectBestHit)
  var subjectBestHit: Boolean?

  @get:JsonProperty(JsonKeys.XDropGap)
  @set:JsonProperty(JsonKeys.XDropGap)
  var xDropGap: Double?

  @get:JsonProperty(JsonKeys.XDropGapFinal)
  @set:JsonProperty(JsonKeys.XDropGapFinal)
  var xDropGapFinal: Double?

  @get:JsonProperty(JsonKeys.MultiHitWindowSize)
  @set:JsonProperty(JsonKeys.MultiHitWindowSize)
  var windowSize: Int?

  @get:JsonProperty(JsonKeys.Ungapped)
  @set:JsonProperty(JsonKeys.Ungapped)
  var ungapped: Boolean?

  @get:JsonProperty(JsonKeys.UseSmithWatermanAligns)
  @set:JsonProperty(JsonKeys.UseSmithWatermanAligns)
  var useSWTraceback: Boolean?

  companion object {
    val _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastP
  }
}