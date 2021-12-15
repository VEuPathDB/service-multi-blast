package mb.api.model.blast.impl

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeName
import mb.api.model.blast.IOBlastReportFormat
import mb.api.model.blast.IOBlastxConfig
import mb.api.model.blast.IOBlastxConfig.Companion._DISCRIMINATOR_TYPE_NAME
import mb.lib.blast.model.CompositionBasedStats
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import org.veupathdb.lib.blast.field.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastx")
data class IOBlastxConfigImpl(
  override var query:            String? = null,
  override var queryLoc:         Location? = null,
  override var eValue:           String? = null,
  override var outFormat:        IOBlastReportFormat? = null,
  override var numDescriptions:  Long? = null,
  override var numAlignments:    Long? = null,
  override var lineLength:       Int? = null,
  override var sortHits:         IOHitSorting? = null,
  override var sortHSPs:         IOHSPSorting? = null,
  override var lcaseMasking:     Boolean? = null,
  override var qCovHSPPerc:      Double? = null,
  override var maxHSPs:          Long? = null,
  override var maxTargetSeqs:    Long? = null,
  override var dbSize:           Byte? = null,
  override var searchSpace:      Short? = null,
  override var xDropUngap:       Double? = null,
  override var parseDefLines:    Boolean? = null,
  override var strand:           Strand? = null,
  override var queryGeneticCode: Short? = null,
  override var task:             BlastXTask? = null,
  override var wordSize:         Int? = null,
  override var gapOpen:          Int? = null,
  override var gapExtend:        Int? = null,
  override var maxIntronLength:  Int? = null,
  override var matrix:           ScoringMatrix? = null,
  override var threshold:        Double? = null,
  override var compBasedStats:   CompositionBasedStats? = null,
  override var seg:              Seg? = null,
  override var softMasking:      Boolean? = null,
  override var taxIds:           List<Int>? = null,
  override var negativeTaxIds:   List<Int>? = null,
  override var dbSoftMask:       String? = null,
  override var dbHardMask:       String? = null,
  override var cullingLimit:     Int? = null,
  override var bestHitOverhang:  Double? = null,
  override var bestHitScoreEdge: Double? = null,
  override var subjectBestHit:   Boolean? = null,
  override var sumStats:         Boolean? = null,
  override var xDropGap:         Double? = null,
  override var xDropGapFinal:    Double? = null,
  override var windowSize:       Int? = null,
  override var ungapped:         Boolean? = null,
  override var useSWTraceback:   Boolean? = null,
) : IOBlastConfigImpl(), IOBlastxConfig {
  override val tool = _DISCRIMINATOR_TYPE_NAME
}