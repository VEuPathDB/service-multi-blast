package mb.api.model.blast.impl

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeName
import mb.api.model.blast.IOBlastReportFormat
import mb.api.model.blast.IOBlastnConfig
import mb.api.model.blast.IOBlastnConfig.Companion._DISCRIMINATOR_TYPE_NAME
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import org.veupathdb.lib.blast.field.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
data class IOBlastnConfigImpl(
  override var query:             String?              = null,
  override var queryLoc:          Location?            = null,
  override var eValue:            String?              = null,
  override var outFormat:         IOBlastReportFormat? = null,
  override var numDescriptions:   Long?                = null,
  override var numAlignments:     Long?                = null,
  override var lineLength:        Int?                 = null,
  override var sortHits:          IOHitSorting?        = null,
  override var sortHSPs:          IOHSPSorting?        = null,
  override var lcaseMasking:      Boolean?             = null,
  override var qCovHSPPerc:       Double?              = null,
  override var maxHSPs:           Long?                = null,
  override var maxTargetSeqs:     Long?                = null,
  override var dbSize:            Byte?                = null,
  override var searchSpace:       Short?               = null,
  override var xDropUngap:        Double?              = null,
  override var parseDefLines:     Boolean?             = null,
  override var strand:            Strand?              = null,
  override var task:              BlastNTask?          = null,
  override var wordSize:          Long?                = null,
  override var gapOpen:           Int?                 = null,
  override var gapExtend:         Int?                 = null,
  override var penalty:           Int?                 = null,
  override var reward:            Long?                = null,
  override var useIndex:          Boolean?             = null,
  override var indexName:         String?              = null,
  override var dust:              Dust?                = null,
  override var windowMaskerTaxID: Int?                 = null,
  override var softMasking:       Boolean?             = null,
  override var taxIds:            List<Int>?           = null,
  override var negativeTaxIds:    List<Int>?           = null,
  override var dbSoftMask:        String?              = null,
  override var dbHardMask:        String?              = null,
  override var percIdentity:      Double?              = null,
  override var cullingLimit:      Long?                = null,
  override var bestHitOverhang:   Double?              = null,
  override var bestHitScoreEdge:  Double?              = null,
  override var subjectBestHit:    Boolean?             = null,
  override var templateType:      TemplateType?        = null,
  override var templateLength:    TemplateLength?      = null,
  override var sumStats:          Boolean?             = null,
  override var xDropGap:          Double?              = null,
  override var xDropGapFinal:     Double?              = null,
  override var noGreedy:          Boolean?             = null,
  override var minRawGappedScore: Int?                 = null,
  override var ungapped:          Boolean?             = null,
  override var windowSize:        Long?                = null,
  override var offDiagonalRange:  Long?                = null,
) : IOBlastConfigImpl(), IOBlastnConfig {
  override val tool = _DISCRIMINATOR_TYPE_NAME
}
