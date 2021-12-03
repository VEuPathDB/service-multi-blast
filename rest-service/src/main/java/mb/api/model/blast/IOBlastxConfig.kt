package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.impl.IOBlastxConfigImpl
import mb.lib.blast.model.CompositionBasedStats
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.field.BlastXTask
import org.veupathdb.lib.blast.field.ScoringMatrix
import org.veupathdb.lib.blast.field.Seg
import org.veupathdb.lib.blast.field.Strand

@JsonTypeName("blastx")
@JsonDeserialize(`as` = IOBlastxConfigImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOBlastxConfig : IOBlastWithLists {
  @get:JsonProperty("strand")
  @set:JsonProperty("strand")
  var strand: Strand?

  @get:JsonProperty("queryGeneticCode")
  @set:JsonProperty("queryGeneticCode")
  var queryGeneticCode: Short?

  @get:JsonProperty("task")
  @set:JsonProperty("task")
  var task: BlastXTask?

  @get:JsonProperty("wordSize")
  @set:JsonProperty("wordSize")
  var wordSize: Int?

  @get:JsonProperty("gapOpen")
  @set:JsonProperty("gapOpen")
  var gapOpen: Int?

  @get:JsonProperty("gapExtend")
  @set:JsonProperty("gapExtend")
  var gapExtend: Int?

  @get:JsonProperty("maxIntronLength")
  @set:JsonProperty("maxIntronLength")
  var maxIntronLength: Int?

  @get:JsonProperty("matrix")
  @set:JsonProperty("matrix")
  var matrix: ScoringMatrix?

  @get:JsonProperty("threshold")
  @set:JsonProperty("threshold")
  var threshold: Double?

  @get:JsonProperty("compBasedStats")
  @set:JsonProperty("compBasedStats")
  var compBasedStats: CompositionBasedStats?

  @get:JsonProperty("seg")
  @set:JsonProperty("seg")
  var seg: Seg?

  @get:JsonProperty("softMasking")
  @set:JsonProperty("softMasking")
  var softMasking: Boolean

  @get:JsonProperty("dbSoftMask")
  @set:JsonProperty("dbSoftMask")
  var dbSoftMask: String?

  @get:JsonProperty("dbHardMask")
  @set:JsonProperty("dbHardMask")
  var dbHardMask: String?

  @get:JsonProperty("cullingLimit")
  @set:JsonProperty("cullingLimit")
  var cullingLimit: Int?

  @get:JsonProperty("bestHitOverhang")
  @set:JsonProperty("bestHitOverhang")
  var bestHitOverhang: Double?

  @get:JsonProperty("bestHitScoreEdge")
  @set:JsonProperty("bestHitScoreEdge")
  var bestHitScoreEdge: Double?

  @get:JsonProperty("subjectBestHit")
  @set:JsonProperty("subjectBestHit")
  var subjectBestHit: Boolean

  @get:JsonProperty("sumStats")
  @set:JsonProperty("sumStats")
  var sumStats: Boolean?

  @get:JsonProperty("xDropGap")
  @set:JsonProperty("xDropGap")
  var xDropGap: Double

  @get:JsonProperty("xDropGapFinal")
  @set:JsonProperty("xDropGapFinal")
  var xDropGapFinal: Double

  @get:JsonProperty("windowSize")
  @set:JsonProperty("windowSize")
  var windowSize: Int?

  @get:JsonProperty("ungapped")
  @set:JsonProperty("ungapped")
  var ungapped: Boolean?

  @get:JsonProperty("useSWTraceback")
  @set:JsonProperty("useSWTraceback")
  var useSWTraceback: Boolean?

  companion object {
    val _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastX
  }
}