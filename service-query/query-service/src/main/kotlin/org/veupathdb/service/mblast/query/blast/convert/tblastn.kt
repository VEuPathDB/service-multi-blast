package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastn.TBlastN
import org.veupathdb.lib.blast.tblastn.fields.*
import org.veupathdb.service.mblast.query.generated.model.*
import org.veupathdb.service.mblast.query.generated.model.TBlastNTask as IOTBlastNTask

fun TBlastNConfig.toInternal() = Blast.tblastn().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (task                    != null) it.task                         = task.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeTN(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (dbGenCode               != null) it.dbGenCode                    = DBGenCode(dbGenCode.toUByte())
  if (maxIntronLength         != null) it.maxIntronLength              = MaxIntronLength(maxIntronLength.toUInt())
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingTN(softMasking)
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

fun TBlastN.toExternal(): TBlastNConfig = TBlastNConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!task.isDefault)                         it.task                    = task.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
  if (!wordSize.isDefault)                     it.wordSize                = wordSize.value.toInt()
  if (!gapOpen.isDefault)                      it.gapOpen                 = gapOpen.value
  if (!gapExtend.isDefault)                    it.gapExtend               = gapExtend.value
  if (!dbGenCode.isDefault)                    it.dbGenCode               = dbGenCode.value.toByte()
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

private fun BlastSeg.toInternal() = SegTN.of(
  window ?: DefSegWindow,
  locut  ?: DefSegLocut,
  hicut  ?: DefSegHicut,
)

private fun SegTN.toExternal(): BlastSeg =
  BlastSegImpl().also {
    it.window = window
    it.locut  = locut
    it.hicut  = hicut
  }

private fun TBlastNCompBasedStats.toInternal() = CompBasedStatsTN(when(this) {
  TBlastNCompBasedStats.NONE                                  -> CompBasedStatsTNValue.None
  TBlastNCompBasedStats.COMPBASEDSTATS                        -> CompBasedStatsTNValue.Statistics
  TBlastNCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL   -> CompBasedStatsTNValue.ConditionalScoreAdjustment
  TBlastNCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL -> CompBasedStatsTNValue.UnconditionalScoreAdjustment
})

private fun CompBasedStatsTN.toExternal() = when(this.value) {
  CompBasedStatsTNValue.None                         -> TBlastNCompBasedStats.NONE
  CompBasedStatsTNValue.Statistics                   -> TBlastNCompBasedStats.COMPBASEDSTATS
  CompBasedStatsTNValue.ConditionalScoreAdjustment   -> TBlastNCompBasedStats.COMPBASEDSCOREADJUSTMENTCONDITIONAL
  CompBasedStatsTNValue.UnconditionalScoreAdjustment -> TBlastNCompBasedStats.COMPBASEDSCOREADJUSTMENTUNCONDITIONAL
}

private fun TBlastNMatrix.toInternal() = ScoringMatrixTN(when (this) {
  TBlastNMatrix.BLOSUM45 -> ScoringMatrixTNType.Blosum45
  TBlastNMatrix.BLOSUM50 -> ScoringMatrixTNType.Blosum50
  TBlastNMatrix.BLOSUM62 -> ScoringMatrixTNType.Blosum62
  TBlastNMatrix.BLOSUM80 -> ScoringMatrixTNType.Blosum80
  TBlastNMatrix.BLOSUM90 -> ScoringMatrixTNType.Blosum90
  TBlastNMatrix.PAM30    -> ScoringMatrixTNType.Pam30
  TBlastNMatrix.PAM70    -> ScoringMatrixTNType.Pam70
  TBlastNMatrix.PAM250   -> ScoringMatrixTNType.Pam250
  TBlastNMatrix.IDENTITY -> ScoringMatrixTNType.Identity
})

private fun ScoringMatrixTN.toExternal() = when (this.value) {
  ScoringMatrixTNType.Blosum45 -> TBlastNMatrix.BLOSUM45
  ScoringMatrixTNType.Blosum50 -> TBlastNMatrix.BLOSUM50
  ScoringMatrixTNType.Blosum62 -> TBlastNMatrix.BLOSUM62
  ScoringMatrixTNType.Blosum80 -> TBlastNMatrix.BLOSUM80
  ScoringMatrixTNType.Blosum90 -> TBlastNMatrix.BLOSUM90
  ScoringMatrixTNType.Pam30    -> TBlastNMatrix.PAM30
  ScoringMatrixTNType.Pam70    -> TBlastNMatrix.PAM70
  ScoringMatrixTNType.Pam250   -> TBlastNMatrix.PAM250
  ScoringMatrixTNType.Identity -> TBlastNMatrix.IDENTITY
  ScoringMatrixTNType.None     -> throw IllegalStateException()
}

private fun IOTBlastNTask.toInternal() = TBlastNTask(when (this) {
  IOTBlastNTask.TBLASTN     -> TBlastNTaskType.TBlastN
  IOTBlastNTask.TBLASTNFAST -> TBlastNTaskType.TBlastNFast
})

private fun TBlastNTask.toExternal() = when (this.value) {
  TBlastNTaskType.TBlastN     -> IOTBlastNTask.TBLASTN
  TBlastNTaskType.TBlastNFast -> IOTBlastNTask.TBLASTNFAST
}