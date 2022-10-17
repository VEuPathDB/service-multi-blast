package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.psiblast.PSIBlast
import org.veupathdb.lib.blast.psiblast.fields.*
import org.veupathdb.service.mblast.query.generated.model.*

fun PSIBlastConfig.toInternal() = Blast.psiblast().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizePSI(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingPSI(softMasking)
  if (lowercaseMasking        != null) it.lowercaseMasking             = LowercaseMasking(lowercaseMasking)
  if (queryCoverageHSPPercent != null) it.queryCoverageHSPPercent      = QueryCoverageHSPPercent(queryCoverageHSPPercent)
  if (maxHSPs                 != null) it.maxHSPs                      = MaxHSPs(maxHSPs.toUInt())
  if (cullingLimit            != null) it.cullingLimit                 = CullingLimit(cullingLimit.toUInt())
  if (bestHitOverhang         != null) it.bestHitOverhang              = BestHitOverhang(bestHitOverhang)
  if (bestHitScoreEdge        != null) it.bestHitScoreEdge             = BestHitScoreEdge(bestHitScoreEdge)
  if (subjectBestHit          != null) it.subjectBestHit               = SubjectBestHit(subjectBestHit)
  if (maxTargetSequences      != null) it.maxTargetSeqs                = MaxTargetSeqs(maxTargetSequences.toUInt())
  if (dbSize                  != null) it.dbSize                       = DBSize(dbSize)
  if (searchSpace             != null) it.searchSpace                  = SearchSpace(searchSpace)
  if (sumStats                != null) it.sumStats                     = SumStats(sumStats)
  if (xDropoffUngapped        != null) it.extensionDropoffUngapped     = ExtensionDropoffUngapped(xDropoffUngapped)
  if (xDropoffPrelimGapped    != null) it.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(xDropoffPrelimGapped)
  if (xDropoffFinalGapped     != null) it.extensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(xDropoffFinalGapped)
  if (windowSize              != null) it.windowSize                   = WindowSize(windowSize.toUInt())
  if (gapTrigger              != null) it.gapTrigger                   = GapTrigger(gapTrigger)
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
}

fun PSIBlast.toExternal(): PSIBlastConfig = PSIBlastConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
  if (!wordSize.isDefault)                     it.wordSize                = wordSize.value.toInt()
  if (!gapOpen.isDefault)                      it.gapOpen                 = gapOpen.value
  if (!gapExtend.isDefault)                    it.gapExtend               = gapExtend.value
  if (!matrix.isDefault)                       it.matrix                  = matrix.toExternal()
  if (!threshold.isDefault)                    it.threshold               = threshold.value
  if (!compBasedStats.isDefault)               it.compBasedStats          = compBasedStats.toExternal()
  if (!seg.isDefault)                          it.seg                     = seg.toExternal()
  if (!softMasking.isDefault)                  it.softMasking             = softMasking.value
  if (!lowercaseMasking.isDefault)             it.lowercaseMasking        = lowercaseMasking.value
  if (!queryCoverageHSPPercent.isDefault)      it.queryCoverageHSPPercent = queryCoverageHSPPercent.value
  if (!maxHSPs.isDefault)                      it.maxHSPs                 = maxHSPs.value.toInt()
  if (!cullingLimit.isDefault)                 it.cullingLimit            = cullingLimit.value.toInt()
  if (!bestHitOverhang.isDefault)              it.bestHitOverhang         = bestHitOverhang.value
  if (!bestHitScoreEdge.isDefault)             it.bestHitScoreEdge        = bestHitScoreEdge.value
  if (!subjectBestHit.isDefault)               it.subjectBestHit          = subjectBestHit.value
  if (!maxTargetSeqs.isDefault)                it.maxTargetSequences      = maxTargetSeqs.value.toInt()
  if (!dbSize.isDefault)                       it.dbSize                  = dbSize.value
  if (!searchSpace.isDefault)                  it.searchSpace             = searchSpace.value
  if (!sumStats.isDefault)                     it.sumStats                = sumStats.value
  if (!extensionDropoffUngapped.isDefault)     it.xDropoffUngapped        = extensionDropoffUngapped.value
  if (!extensionDropoffPrelimGapped.isDefault) it.xDropoffPrelimGapped    = extensionDropoffPrelimGapped.value
  if (!extensionDropoffFinalGapped.isDefault)  it.xDropoffFinalGapped     = extensionDropoffFinalGapped.value
  if (!windowSize.isDefault)                   it.windowSize              = windowSize.value.toInt()
  if (!gapTrigger.isDefault)                   it.gapTrigger              = gapTrigger.value
  if (!parseDefLines.isDefault)                it.parseDefLines           = parseDefLines.value
}

private const val DefSegWindow = 12
private const val DefSegLocut  = 2.2
private const val DefSegHicut  = 2.5

private fun BlastSeg.toInternal() = SegPSI.of(
  window ?: DefSegWindow,
  locut  ?: DefSegLocut,
  hicut  ?: DefSegHicut,
)

private fun SegPSI.toExternal(): BlastSeg =
  BlastSegImpl().also {
    it.window = window
    it.locut  = locut
    it.hicut  = hicut
  }

private fun PSIBlastCompBasedStats.toInternal() = CompBasedStatsPSI(when (this) {
  PSIBlastCompBasedStats.NONE                                  -> CompBasedStatsPSIValue.None
  PSIBlastCompBasedStats.COMPBASEDSTATS                        -> CompBasedStatsPSIValue.Statistics
  PSIBlastCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL   -> CompBasedStatsPSIValue.ConditionalScoreAdjustment
  PSIBlastCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL -> CompBasedStatsPSIValue.UnconditionalScoreAdjustment
})

private fun CompBasedStatsPSI.toExternal() = when (this.value) {
  CompBasedStatsPSIValue.None                         -> PSIBlastCompBasedStats.NONE
  CompBasedStatsPSIValue.Statistics                   -> PSIBlastCompBasedStats.COMPBASEDSTATS
  CompBasedStatsPSIValue.ConditionalScoreAdjustment   -> PSIBlastCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL
  CompBasedStatsPSIValue.UnconditionalScoreAdjustment -> PSIBlastCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL
}

private fun PSIBlastMatrix.toInternal() = ScoringMatrixPSI(when (this) {
  PSIBlastMatrix.BLOSUM45 -> ScoringMatrixPSIType.Blosum45
  PSIBlastMatrix.BLOSUM50 -> ScoringMatrixPSIType.Blosum50
  PSIBlastMatrix.BLOSUM62 -> ScoringMatrixPSIType.Blosum62
  PSIBlastMatrix.BLOSUM80 -> ScoringMatrixPSIType.Blosum80
  PSIBlastMatrix.BLOSUM90 -> ScoringMatrixPSIType.Blosum90
  PSIBlastMatrix.PAM30    -> ScoringMatrixPSIType.Pam30
  PSIBlastMatrix.PAM70    -> ScoringMatrixPSIType.Pam70
  PSIBlastMatrix.PAM250   -> ScoringMatrixPSIType.Pam250
})

private fun ScoringMatrixPSI.toExternal() = when (this.value) {
  ScoringMatrixPSIType.Blosum45 -> PSIBlastMatrix.BLOSUM45
  ScoringMatrixPSIType.Blosum50 -> PSIBlastMatrix.BLOSUM50
  ScoringMatrixPSIType.Blosum62 -> PSIBlastMatrix.BLOSUM62
  ScoringMatrixPSIType.Blosum80 -> PSIBlastMatrix.BLOSUM80
  ScoringMatrixPSIType.Blosum90 -> PSIBlastMatrix.BLOSUM90
  ScoringMatrixPSIType.Pam30    -> PSIBlastMatrix.PAM30
  ScoringMatrixPSIType.Pam70    -> PSIBlastMatrix.PAM70
  ScoringMatrixPSIType.Pam250   -> PSIBlastMatrix.PAM250
  ScoringMatrixPSIType.None     -> throw IllegalStateException()
}