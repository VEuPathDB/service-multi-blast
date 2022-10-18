package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.blastx.BlastX
import org.veupathdb.lib.blast.blastx.field.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.service.mblast.query.generated.model.*
import org.veupathdb.service.mblast.query.generated.model.BlastXTask as IOBlastXTask

fun BlastXConfig.toInternal(): BlastX = Blast.blastx().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (strand                  != null) it.strand                       = strand.toInternal()
  if (queryGenCode            != null) it.queryGenCode                 = QueryGenCode(queryGenCode.toUByte())
  if (task                    != null) it.task                         = task.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeX(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (maxIntronLength         != null) it.maxIntronLength              = MaxIntronLength(maxIntronLength.toUInt())
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingX(softMasking)
  if (lowercaseMasking        != null) it.lowercaseMasking             = LowercaseMasking(lowercaseMasking)
  if (dbSoftMask              != null) it.dbSoftMask                   = DBSoftMask(dbSoftMask)
  if (dbHardMask              != null) it.dbHardMask                   = DBHardMask(dbHardMask)
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
  if (ungappedOnly            != null) it.ungappedAlignmentsOnly       = UngappedAlignmentsOnly(ungappedOnly)
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
  if (useSWTraceback          != null) it.useSmithWatermanTraceback    = UseSmithWatermanTraceback(useSWTraceback)
}

fun BlastX.toExternal(): BlastXConfig = BlastXConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!strand.isDefault)                       it.strand                  = strand.toExternal()
  if (!queryGenCode.isDefault)                 it.queryGenCode            = queryGenCode.value.toByte()
  if (!task.isDefault)                         it.task                    = task.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
  if (!wordSize.isDefault)                     it.wordSize                = wordSize.value.toInt()
  if (!gapOpen.isDefault)                      it.gapOpen                 = gapOpen.value
  if (!gapExtend.isDefault)                    it.gapExtend               = gapExtend.value
  if (!maxIntronLength.isDefault)              it.maxIntronLength         = maxIntronLength.value.toInt()
  if (!matrix.isDefault)                       it.matrix                  = matrix.toExternal()
  if (!threshold.isDefault)                    it.threshold               = threshold.value
  if (!compBasedStats.isDefault)               it.compBasedStats          = compBasedStats.toExternal()
  if (!seg.isDefault)                          it.seg                     = seg.toExternal()
  if (!softMasking.isDefault)                  it.softMasking             = softMasking.value
  if (!lowercaseMasking.isDefault)             it.lowercaseMasking        = lowercaseMasking.value
  if (!dbSoftMask.isDefault)                   it.dbSoftMask              = dbSoftMask.value
  if (!dbHardMask.isDefault)                   it.dbHardMask              = dbHardMask.value
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
  if (!ungappedAlignmentsOnly.isDefault)       it.ungappedOnly            = ungappedAlignmentsOnly.value
  if (!parseDefLines.isDefault)                it.parseDefLines           = parseDefLines.value
  if (!useSmithWatermanTraceback.isDefault)    it.useSWTraceback          = useSmithWatermanTraceback.value
}

private const val DefSegWindow = 12
private const val DefSegLocut  = 2.2
private const val DefSegHicut  = 2.5

private fun BlastSeg.toInternal() = SegX.of(
  window ?: DefSegWindow,
  locut  ?: DefSegLocut,
  hicut  ?: DefSegHicut,
)

private fun SegX.toExternal(): BlastSeg =
  BlastSegImpl().also {
    it.window = window
    it.locut  = locut
    it.hicut  = hicut
  }

private fun BlastXCompBasedStats.toInternal() = CompBasedStatsX(when (this) {
  BlastXCompBasedStats.NONE                                  -> CompBasedStatsXValue.None
  BlastXCompBasedStats.COMPBASEDSTATS                        -> CompBasedStatsXValue.Statistics
  BlastXCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL   -> CompBasedStatsXValue.ConditionalScoreAdjustment
  BlastXCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL -> CompBasedStatsXValue.UnconditionalScoreAdjustment
})

private fun CompBasedStatsX.toExternal() = when (this.value) {
  CompBasedStatsXValue.None                         -> BlastXCompBasedStats.NONE
  CompBasedStatsXValue.Statistics                   -> BlastXCompBasedStats.COMPBASEDSTATS
  CompBasedStatsXValue.ConditionalScoreAdjustment   -> BlastXCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL
  CompBasedStatsXValue.UnconditionalScoreAdjustment -> BlastXCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL
}

private fun BlastXMatrix.toInternal() = ScoringMatrixX(when (this) {
  BlastXMatrix.BLOSUM45 -> ScoringMatrixXType.Blosum45
  BlastXMatrix.BLOSUM50 -> ScoringMatrixXType.Blosum50
  BlastXMatrix.BLOSUM62 -> ScoringMatrixXType.Blosum62
  BlastXMatrix.BLOSUM80 -> ScoringMatrixXType.Blosum80
  BlastXMatrix.BLOSUM90 -> ScoringMatrixXType.Blosum90
  BlastXMatrix.PAM30    -> ScoringMatrixXType.Pam30
  BlastXMatrix.PAM70    -> ScoringMatrixXType.Pam70
  BlastXMatrix.PAM250   -> ScoringMatrixXType.Pam250
})

private fun ScoringMatrixX.toExternal() = when (this.value) {
  ScoringMatrixXType.Blosum45 -> BlastXMatrix.BLOSUM45
  ScoringMatrixXType.Blosum50 -> BlastXMatrix.BLOSUM50
  ScoringMatrixXType.Blosum62 -> BlastXMatrix.BLOSUM62
  ScoringMatrixXType.Blosum80 -> BlastXMatrix.BLOSUM80
  ScoringMatrixXType.Blosum90 -> BlastXMatrix.BLOSUM90
  ScoringMatrixXType.Pam30    -> BlastXMatrix.PAM30
  ScoringMatrixXType.Pam70    -> BlastXMatrix.PAM70
  ScoringMatrixXType.Pam250   -> BlastXMatrix.PAM250
  ScoringMatrixXType.None     -> throw IllegalStateException()
}

private fun IOBlastXTask.toInternal() = BlastXTask(when (this) {
  IOBlastXTask.BLASTX     -> BlastXTaskType.BlastX
  IOBlastXTask.BLASTXFAST -> BlastXTaskType.BlastXFast
})

private fun BlastXTask.toExternal() = when (this.value) {
  BlastXTaskType.BlastX     -> IOBlastXTask.BLASTX
  BlastXTaskType.BlastXFast -> IOBlastXTask.BLASTXFAST
}
