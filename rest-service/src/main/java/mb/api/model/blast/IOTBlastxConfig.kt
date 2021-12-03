package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.impl.IOTBlastxConfigImpl
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.field.ScoringMatrix
import org.veupathdb.lib.blast.field.Seg
import org.veupathdb.lib.blast.field.Strand

@JsonTypeName("tblastx")
@JsonDeserialize(`as` = IOTBlastxConfigImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOTBlastxConfig : IOBlastWithLists {
  @get:JsonProperty(JsonKeys.Strand)
  @set:JsonProperty(JsonKeys.Strand)
  var strand: Strand?

  @get:JsonProperty(JsonKeys.QueryGeneticCode)
  @set:JsonProperty(JsonKeys.QueryGeneticCode)
  var queryGeneticCode: Byte?

  @get:JsonProperty(JsonKeys.WordSize)
  @set:JsonProperty(JsonKeys.WordSize)
  var wordSize: Int?

  @get:JsonProperty(JsonKeys.MaxIntronLength)
  @set:JsonProperty(JsonKeys.MaxIntronLength)
  var maxIntronLength: Int?

  @get:JsonProperty(JsonKeys.Matrix)
  @set:JsonProperty(JsonKeys.Matrix)
  var matrix: ScoringMatrix?

  @get:JsonProperty(JsonKeys.Threshold)
  @set:JsonProperty(JsonKeys.Threshold)
  var threshold: Double?

  @get:JsonProperty(JsonKeys.DBGeneticCode)
  @set:JsonProperty(JsonKeys.DBGeneticCode)
  var dbGencode: Byte?

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

  @get:JsonProperty(JsonKeys.SumStats)
  @set:JsonProperty(JsonKeys.SumStats)
  var sumStats: Boolean?

  @get:JsonProperty(JsonKeys.MultiHitWindowSize)
  @set:JsonProperty(JsonKeys.MultiHitWindowSize)
  var windowSize: Int?

  companion object {
    val _DISCRIMINATOR_TYPE_NAME = BlastTool.TBlastX
  }
}