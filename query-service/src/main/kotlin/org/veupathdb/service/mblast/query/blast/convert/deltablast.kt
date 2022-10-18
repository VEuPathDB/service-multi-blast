package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.deltablast.DeltaBlast
import org.veupathdb.lib.blast.deltablast.fields.*
import org.veupathdb.service.mblast.query.generated.model.*

fun DeltaBlastConfig.toInternal() = Blast.deltablast().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeDelta(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingDelta(softMasking)
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
  if (useSWTraceback          != null) it.useSmithWatermanTraceback    = UseSmithWatermanTraceback(useSWTraceback)
}

fun DeltaBlast.toExternal(): DeltaBlastConfig = DeltaBlastConfigImpl().also {
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
  if (!useSmithWatermanTraceback.isDefault)    it.useSWTraceback          = useSmithWatermanTraceback.value
}

private const val DefSegWindow = 12
private const val DefSegLocut  = 2.2
private const val DefSegHicut  = 2.5

private fun BlastSeg.toInternal() = SegDelta.of(
  window ?: DefSegWindow,
  locut  ?: DefSegLocut,
  hicut  ?: DefSegHicut,
)

private fun SegDelta.toExternal(): BlastSeg =
  BlastSegImpl().also {
    it.window = window
    it.locut  = locut
    it.hicut  = hicut
  }


private fun DeltaBlastCompBasedStats.toInternal() = CompBasedStatsDelta(when (this) {
  DeltaBlastCompBasedStats.NONE           -> CompBasedStatsDeltaValue.NoCompBasedStats
  DeltaBlastCompBasedStats.COMPBASEDSTATS -> CompBasedStatsDeltaValue.CompBasedStats
})

private fun CompBasedStatsDelta.toExternal() = when (this.value) {
  CompBasedStatsDeltaValue.NoCompBasedStats -> DeltaBlastCompBasedStats.NONE
  CompBasedStatsDeltaValue.CompBasedStats   -> DeltaBlastCompBasedStats.COMPBASEDSTATS
}

private fun DeltaBlastMatrix.toInternal() = ScoringMatrixDelta(when (this) {
  DeltaBlastMatrix.BLOSUM45 -> ScoringMatrixDeltaType.Blosum45
  DeltaBlastMatrix.BLOSUM50 -> ScoringMatrixDeltaType.Blosum50
  DeltaBlastMatrix.BLOSUM62 -> ScoringMatrixDeltaType.Blosum62
  DeltaBlastMatrix.BLOSUM80 -> ScoringMatrixDeltaType.Blosum80
  DeltaBlastMatrix.BLOSUM90 -> ScoringMatrixDeltaType.Blosum90
  DeltaBlastMatrix.PAM30    -> ScoringMatrixDeltaType.Pam30
  DeltaBlastMatrix.PAM70    -> ScoringMatrixDeltaType.Pam70
  DeltaBlastMatrix.PAM250   -> ScoringMatrixDeltaType.Pam250
})

private fun ScoringMatrixDelta.toExternal() = when (this.value) {
  ScoringMatrixDeltaType.Blosum45 -> DeltaBlastMatrix.BLOSUM45
  ScoringMatrixDeltaType.Blosum50 -> DeltaBlastMatrix.BLOSUM50
  ScoringMatrixDeltaType.Blosum62 -> DeltaBlastMatrix.BLOSUM62
  ScoringMatrixDeltaType.Blosum80 -> DeltaBlastMatrix.BLOSUM80
  ScoringMatrixDeltaType.Blosum90 -> DeltaBlastMatrix.BLOSUM90
  ScoringMatrixDeltaType.Pam30    -> DeltaBlastMatrix.PAM30
  ScoringMatrixDeltaType.Pam70    -> DeltaBlastMatrix.PAM70
  ScoringMatrixDeltaType.Pam250   -> DeltaBlastMatrix.PAM250
  ScoringMatrixDeltaType.None     -> throw IllegalStateException()
}