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
data class IOBlastxConfigImpl private constructor(
  override var query:            String?,
  override var queryLoc:         Location?,
  override var eValue:           String?,
  override var outFormat:        IOBlastReportFormat?,
  override var numDescriptions:  Long?,
  override var numAlignments:    Long?,
  override var lineLength:       Int?,
  override var sortHits:         IOHitSorting?,
  override var sortHSPs:         IOHSPSorting?,
  override var lcaseMasking:     Boolean?,
  override var qCovHSPPerc:      Double?,
  override var maxHSPs:          Long?,
  override var maxTargetSeqs:    Long?,
  override var dbSize:           Byte?,
  override var searchSpace:      Short?,
  override var xDropUngap:       Double?,
  override var parseDefLines:    Boolean?,
  override var strand:           Strand?,
  override var queryGeneticCode: Short?,
  override var task:             BlastXTask?,
  override var wordSize:         Int?,
  override var gapOpen:          Int?,
  override var gapExtend:        Int?,
  override var maxIntronLength:  Int,
  override var matrix:           ScoringMatrix?,
  override var threshold:        Double?,
  override var compBasedStats:   CompositionBasedStats?,
  override var seg:              Seg?,
  override var softMasking:      Boolean,
  override var taxIds:           List<Int>?,
  override var negativeTaxIds:   List<Int>?,
  override var dbSoftMask:       String?,
  override var dbHardMask:       String?,
  override var cullingLimit:     Int?,
  override var bestHitOverhang:  Double?,
  override var bestHitScoreEdge: Double?,
  override var subjectBestHit:   Boolean,
  override var sumStats:         Boolean?,
  override var xDropGap:         Double,
  override var xDropGapFinal:    Double,
  override var windowSize:       Int,
  override var ungapped:         Boolean?,
  override var useSWTraceback:   Boolean?,
) : IOBlastConfigImpl(), IOBlastxConfig {
  override val tool = _DISCRIMINATOR_TYPE_NAME

  constructor(
    query:            String?                = null,
    queryLoc:         Location?              = null,
    eValue:           String?                = null,
    outFormat:        IOBlastReportFormat?   = null,
    numDescriptions:  Long?                  = null,
    numAlignments:    Long?                  = null,
    lineLength:       Int?                   = null,
    sortHits:         IOHitSorting?          = null,
    sortHSPs:         IOHSPSorting?          = null,
    lcaseMasking:     Boolean?               = null,
    qCovHSPPerc:      Double?                = null,
    maxHSPs:          Long?                  = null,
    maxTargetSeqs:    Long?                  = null,
    dbSize:           Byte?                  = null,
    searchSpace:      Short?                 = null,
    xDropUngap:       Double?                = null,
    parseDefLines:    Boolean?               = null,
    strand:           Strand?                = null,
    queryGeneticCode: Short?                 = null,
    task:             BlastXTask?            = null,
    wordSize:         Int?                   = null,
    gapOpen:          Int?                   = null,
    gapExtend:        Int?                   = null,
    maxIntronLength:  Int?                   = null,
    matrix:           ScoringMatrix?         = null,
    threshold:        Double?                = null,
    compBasedStats:   CompositionBasedStats? = null,
    seg:              Seg?                   = null,
    softMasking:      Boolean?               = null,
    taxIds:           List<Int>?             = null,
    negativeTaxIds:   List<Int>?             = null,
    dbSoftMask:       String?                = null,
    dbHardMask:       String?                = null,
    cullingLimit:     Int?                   = null,
    bestHitOverhang:  Double?                = null,
    bestHitScoreEdge: Double?                = null,
    subjectBestHit:   Boolean?               = null,
    sumStats:         Boolean?               = null,
    xDropGap:         Double?                = null,
    xDropGapFinal:    Double?                = null,
    windowSize:       Int?                   = null,
    ungapped:         Boolean?               = null,
    useSWTraceback:   Boolean?               = null,
  ): this(
    query, queryLoc, eValue ?: "10", outFormat, numDescriptions ?: 500,
    numAlignments ?: 250, lineLength ?: 60, sortHits, sortHSPs,
    lcaseMasking ?: false, qCovHSPPerc, maxHSPs, maxTargetSeqs ?: 500, dbSize,
    searchSpace, xDropUngap, parseDefLines ?: false, strand, queryGeneticCode,
    task, wordSize, gapOpen, gapExtend, maxIntronLength ?: 0, matrix, threshold,
    compBasedStats, seg, softMasking ?: false, taxIds, negativeTaxIds,
    dbSoftMask, dbHardMask, cullingLimit, bestHitOverhang, bestHitScoreEdge,
    subjectBestHit ?: false, sumStats, xDropGap ?: 30.0, xDropGapFinal ?: 100.0,
    windowSize ?: 40, ungapped, useSWTraceback
  )
}