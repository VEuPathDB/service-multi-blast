package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.impl.IOBlastnConfigImpl
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.field.*

@JsonTypeName("blastn")
@JsonDeserialize(`as` = IOBlastnConfigImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOBlastnConfig : IOBlastConfig {
  @get:JsonProperty(JsonKeys.Strand)
  @set:JsonProperty(JsonKeys.Strand)
  var strand: Strand?

  @get:JsonProperty(JsonKeys.Task)
  @set:JsonProperty(JsonKeys.Task)
  var task: BlastNTask?

  @get:JsonProperty(JsonKeys.WordSize)
  @set:JsonProperty(JsonKeys.WordSize)
  var wordSize: Long?

  @get:JsonProperty(JsonKeys.GapOpen)
  @set:JsonProperty(JsonKeys.GapOpen)
  var gapOpen: Int?

  @get:JsonProperty(JsonKeys.GapExtend)
  @set:JsonProperty(JsonKeys.GapExtend)
  var gapExtend: Int?

  @get:JsonProperty(JsonKeys.Penalty)
  @set:JsonProperty(JsonKeys.Penalty)
  var penalty: Int?

  @get:JsonProperty(JsonKeys.Reward)
  @set:JsonProperty(JsonKeys.Reward)
  var reward: Long?

  @get:JsonProperty(JsonKeys.UseIndex)
  @set:JsonProperty(JsonKeys.UseIndex)
  var useIndex: Boolean?

  @get:JsonProperty(JsonKeys.IndexName)
  @set:JsonProperty(JsonKeys.IndexName)
  var indexName: String?

  @get:JsonProperty(JsonKeys.Dust)
  @set:JsonProperty(JsonKeys.Dust)
  var dust: Dust?

  @get:JsonProperty(JsonKeys.WindowMaskerTaxID)
  @set:JsonProperty(JsonKeys.WindowMaskerTaxID)
  var windowMaskerTaxID: Int?

  @get:JsonProperty(JsonKeys.SoftMasking)
  @set:JsonProperty(JsonKeys.SoftMasking)
  var softMasking: Boolean?

  @get:JsonProperty(JsonKeys.TaxIDs)
  @set:JsonProperty(JsonKeys.TaxIDs)
  var taxIds: List<Int>?

  @get:JsonProperty(JsonKeys.NegativeTaxIDs)
  @set:JsonProperty(JsonKeys.NegativeTaxIDs)
  var negativeTaxIds: List<Int>?

  @get:JsonProperty(JsonKeys.DBSoftMask)
  @set:JsonProperty(JsonKeys.DBSoftMask)
  var dbSoftMask: String?

  @get:JsonProperty(JsonKeys.DBHardMask)
  @set:JsonProperty(JsonKeys.DBHardMask)
  var dbHardMask: String?

  @get:JsonProperty(JsonKeys.PercentIdentity)
  @set:JsonProperty(JsonKeys.PercentIdentity)
  var percIdentity: Double?

  @get:JsonProperty(JsonKeys.CullingLimit)
  @set:JsonProperty(JsonKeys.CullingLimit)
  var cullingLimit: Long?

  @get:JsonProperty(JsonKeys.BestHitOverhang)
  @set:JsonProperty(JsonKeys.BestHitOverhang)
  var bestHitOverhang: Double?

  @get:JsonProperty(JsonKeys.BestHitScoreEdge)
  @set:JsonProperty(JsonKeys.BestHitScoreEdge)
  var bestHitScoreEdge: Double?

  @get:JsonProperty(JsonKeys.SubjectBestHit)
  @set:JsonProperty(JsonKeys.SubjectBestHit)
  var subjectBestHit: Boolean?

  @get:JsonProperty(JsonKeys.TemplateType)
  @set:JsonProperty(JsonKeys.TemplateType)
  var templateType: TemplateType?

  @get:JsonProperty(JsonKeys.TemplateLength)
  @set:JsonProperty(JsonKeys.TemplateLength)
  var templateLength: TemplateLength?

  @get:JsonProperty(JsonKeys.SumStats)
  @set:JsonProperty(JsonKeys.SumStats)
  var sumStats: Boolean?

  @get:JsonProperty(JsonKeys.XDropGap)
  @set:JsonProperty(JsonKeys.XDropGap)
  var xDropGap: Double?

  @get:JsonProperty(JsonKeys.XDropGapFinal)
  @set:JsonProperty(JsonKeys.XDropGapFinal)
  var xDropGapFinal: Double?

  @get:JsonProperty(JsonKeys.NonGreedy)
  @set:JsonProperty(JsonKeys.NonGreedy)
  var noGreedy: Boolean?

  @get:JsonProperty(JsonKeys.MinRawGappedScore)
  @set:JsonProperty(JsonKeys.MinRawGappedScore)
  var minRawGappedScore: Int?

  @get:JsonProperty(JsonKeys.Ungapped)
  @set:JsonProperty(JsonKeys.Ungapped)
  var ungapped: Boolean?

  @get:JsonProperty(JsonKeys.MultiHitWindowSize)
  @set:JsonProperty(JsonKeys.MultiHitWindowSize)
  var windowSize: Long?

  @get:JsonProperty(JsonKeys.OffDiagonalRange)
  @set:JsonProperty(JsonKeys.OffDiagonalRange)
  var offDiagonalRange: Long?

  companion object {
    val _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastN
  }
}