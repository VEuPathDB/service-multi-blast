package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.blastp.BlastP
import org.veupathdb.lib.blast.blastp.field.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.service.mblast.query.generated.model.BlastPCompBasedStats
import org.veupathdb.service.mblast.query.generated.model.BlastPConfig
import org.veupathdb.service.mblast.query.generated.model.BlastPConfigImpl
import org.veupathdb.service.mblast.query.generated.model.BlastPMatrix
import org.veupathdb.service.mblast.query.generated.model.BlastSeg
import org.veupathdb.service.mblast.query.generated.model.BlastSegImpl
import org.veupathdb.service.mblast.query.generated.model.BlastPTask as IOBlastPTask

fun BlastPConfig.toInternal() = Blast.blastp().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (task                    != null) it.task                         = task.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeP(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingP(softMasking)
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
  if (xDropoffUngapped        != null) it.extensionDropoffUngapped     = ExtensionDropoffUngapped(xDropoffUngapped)
  if (xDropoffPrelimGapped    != null) it.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(xDropoffPrelimGapped)
  if (xDropoffFinalGapped     != null) it.extensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(xDropoffFinalGapped)
  if (windowSize              != null) it.windowSize                   = WindowSize(windowSize.toUInt())
  if (ungappedOnly            != null) it.ungappedAlignmentsOnly       = UngappedAlignmentsOnly(ungappedOnly)
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
  if (useSWTraceback          != null) it.useSmithWatermanTraceback    = UseSmithWatermanTraceback(useSWTraceback)
}

fun BlastP.toExternal(): BlastPConfig = BlastPConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!task.isDefault)                         it.task                    = task.toExternal()
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

private fun BlastSeg.toInternal() = SegP.of(
  window ?: DefSegWindow,
  locut  ?: DefSegLocut,
  hicut  ?: DefSegHicut,
)

private fun SegP.toExternal(): BlastSeg =
  BlastSegImpl().also {
    it.window = window
    it.locut  = locut
    it.hicut  = hicut
  }

private fun BlastPCompBasedStats.toInternal() = CompBasedStatsP(when(this) {
  BlastPCompBasedStats.NONE                                  -> CompBasedStatsPValue.None
  BlastPCompBasedStats.COMPBASEDSTATS                        -> CompBasedStatsPValue.Statistics
  BlastPCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL   -> CompBasedStatsPValue.ConditionalScoreAdjustment
  BlastPCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL -> CompBasedStatsPValue.UnconditionalScoreAdjustment
})

private fun CompBasedStatsP.toExternal() = when(this.value) {
  CompBasedStatsPValue.None                         -> BlastPCompBasedStats.NONE
  CompBasedStatsPValue.Statistics                   -> BlastPCompBasedStats.COMPBASEDSTATS
  CompBasedStatsPValue.ConditionalScoreAdjustment   -> BlastPCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL
  CompBasedStatsPValue.UnconditionalScoreAdjustment -> BlastPCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL
}

private fun BlastPMatrix.toInternal() = ScoringMatrixP(when(this) {
  BlastPMatrix.BLOSUM45 -> ScoringMatrixPType.Blosum45
  BlastPMatrix.BLOSUM50 -> ScoringMatrixPType.Blosum50
  BlastPMatrix.BLOSUM62 -> ScoringMatrixPType.Blosum62
  BlastPMatrix.BLOSUM80 -> ScoringMatrixPType.Blosum80
  BlastPMatrix.BLOSUM90 -> ScoringMatrixPType.Blosum90
  BlastPMatrix.PAM30    -> ScoringMatrixPType.Pam30
  BlastPMatrix.PAM70    -> ScoringMatrixPType.Pam70
  BlastPMatrix.PAM250   -> ScoringMatrixPType.Pam250
  BlastPMatrix.IDENTITY -> ScoringMatrixPType.Identity
})

private fun ScoringMatrixP.toExternal() = when(this.value) {
  ScoringMatrixPType.Blosum45 -> BlastPMatrix.BLOSUM45
  ScoringMatrixPType.Blosum50 -> BlastPMatrix.BLOSUM50
  ScoringMatrixPType.Blosum62 -> BlastPMatrix.BLOSUM62
  ScoringMatrixPType.Blosum80 -> BlastPMatrix.BLOSUM80
  ScoringMatrixPType.Blosum90 -> BlastPMatrix.BLOSUM90
  ScoringMatrixPType.Pam30    -> BlastPMatrix.PAM30
  ScoringMatrixPType.Pam70    -> BlastPMatrix.PAM70
  ScoringMatrixPType.Pam250   -> BlastPMatrix.PAM250
  ScoringMatrixPType.Identity -> BlastPMatrix.IDENTITY
  ScoringMatrixPType.None     -> throw IllegalStateException()
}

private fun IOBlastPTask.toInternal() = BlastPTask(when(this) {
  IOBlastPTask.BLASTP      -> BlastPTaskType.BlastP
  IOBlastPTask.BLASTPFAST  -> BlastPTaskType.BlastPFast
  IOBlastPTask.BLASTPSHORT -> BlastPTaskType.BlastPShort
})

private fun BlastPTask.toExternal() = when(this.value) {
  BlastPTaskType.BlastP      -> IOBlastPTask.BLASTP
  BlastPTaskType.BlastPFast  -> IOBlastPTask.BLASTPFAST
  BlastPTaskType.BlastPShort -> IOBlastPTask.BLASTPSHORT
}