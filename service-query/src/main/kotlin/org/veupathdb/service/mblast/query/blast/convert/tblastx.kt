package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastx.TBlastX
import org.veupathdb.lib.blast.tblastx.fields.*
import org.veupathdb.service.mblast.query.generated.model.*

fun TBlastXConfig.toInternal() = Blast.tblastx().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (strand                  != null) it.strand                       = strand.toInternal()
  if (queryGenCode            != null) it.queryGenCode                 = QueryGenCode(queryGenCode.toUByte())
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeTX(wordSize.toUInt())
  if (maxIntronLength         != null) it.maxIntronLength              = MaxIntronLength(maxIntronLength.toUInt())
  if (matrix                  != null) it.matrix                       = matrix.toInternal()
  if (threshold               != null) it.threshold                    = Threshold(threshold)
  if (dbGenCode               != null) it.dbGenCode                    = DBGenCode(dbGenCode.toUByte())
  if (seg                     != null) it.seg                          = seg.toInternal() ?: it.seg
  if (softMasking             != null) it.softMasking                  = SoftMaskingTX(softMasking)
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
  if (windowSize              != null) it.windowSize                   = WindowSize(windowSize.toUInt())
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
}

fun TBlastX.toExternal(): TBlastXConfig = TBlastXConfigImpl().also {
  if (!queryLocation.isDefault)            it.queryLocation           = queryLocation.toExternal()
  if (!strand.isDefault)                   it.strand                  = strand.toExternal()
  if (!queryGenCode.isDefault)             it.queryGenCode            = queryGenCode.value.toByte()
  if (!expectValue.isDefault)              it.eValue                  = expectValue.value
  if (!wordSize.isDefault)                 it.wordSize                = wordSize.value.toInt()
  if (!maxIntronLength.isDefault)          it.maxIntronLength         = maxIntronLength.value.toInt()
  if (!matrix.isDefault)                   it.matrix                  = matrix.toExternal()
  if (!threshold.isDefault)                it.threshold               = threshold.value
  if (!dbGenCode.isDefault)                it.dbGenCode               = dbGenCode.value.toByte()
  if (!seg.isDefault)                      it.seg                     = seg.toExternal()
  if (!softMasking.isDefault)              it.softMasking             = softMasking.value
  if (!lowercaseMasking.isDefault)         it.lowercaseMasking        = lowercaseMasking.value
  if (!dbSoftMask.isDefault)               it.dbSoftMask              = dbSoftMask.value
  if (!dbHardMask.isDefault)               it.dbHardMask              = dbHardMask.value
  if (!queryCoverageHSPPercent.isDefault)  it.queryCoverageHSPPercent = queryCoverageHSPPercent.value
  if (!maxHSPs.isDefault)                  it.maxHSPs                 = maxHSPs.value.toInt()
  if (!cullingLimit.isDefault)             it.cullingLimit            = cullingLimit.value.toInt()
  if (!bestHitOverhang.isDefault)          it.bestHitOverhang         = bestHitOverhang.value
  if (!bestHitScoreEdge.isDefault)         it.bestHitScoreEdge        = bestHitScoreEdge.value
  if (!subjectBestHit.isDefault)           it.subjectBestHit          = subjectBestHit.value
  if (!maxTargetSeqs.isDefault)            it.maxTargetSequences      = maxTargetSeqs.value.toInt()
  if (!dbSize.isDefault)                   it.dbSize                  = dbSize.value
  if (!searchSpace.isDefault)              it.searchSpace             = searchSpace.value
  if (!sumStats.isDefault)                 it.sumStats                = sumStats.value
  if (!extensionDropoffUngapped.isDefault) it.xDropoffUngapped        = extensionDropoffUngapped.value
  if (!windowSize.isDefault)               it.windowSize              = windowSize.value.toInt()
  if (!parseDefLines.isDefault)            it.parseDefLines           = parseDefLines.value
}

private const val DefSegWindow = 12
private const val DefSegLocut  = 2.2
private const val DefSegHicut  = 2.5

private fun BlastSeg.toInternal(): SegTX? {
  if (enabled == false)
    return SegTX.no()

  val allNull = window == null && locut == null && hicut == null

  if (enabled == true) {
    return if (allNull)
      SegTX.yes()
    else
      SegTX.of(
        window ?: DefSegWindow,
        locut  ?: DefSegLocut,
        hicut  ?: DefSegHicut,
      )
  }

  return if (allNull)
    null
  else
    SegTX.of(
      window ?: DefSegWindow,
      locut  ?: DefSegLocut,
      hicut  ?: DefSegHicut,
    )
}

private fun SegTX.toExternal(): BlastSeg =
  BlastSegImpl().also {
    if (isYes) {
      it.enabled = true
    } else if (isNo) {
      it.enabled = false
    } else {
      it.window = window
      it.locut = locut
      it.hicut = hicut
    }
  }

private fun TBlastXMatrix.toInternal() = ScoringMatrixTX(when (this) {
  TBlastXMatrix.BLOSUM45 -> ScoringMatrixTXType.Blosum45
  TBlastXMatrix.BLOSUM50 -> ScoringMatrixTXType.Blosum50
  TBlastXMatrix.BLOSUM62 -> ScoringMatrixTXType.Blosum62
  TBlastXMatrix.BLOSUM80 -> ScoringMatrixTXType.Blosum80
  TBlastXMatrix.BLOSUM90 -> ScoringMatrixTXType.Blosum90
  TBlastXMatrix.PAM30    -> ScoringMatrixTXType.Pam30
  TBlastXMatrix.PAM70    -> ScoringMatrixTXType.Pam70
  TBlastXMatrix.PAM250   -> ScoringMatrixTXType.Pam250
})

private fun ScoringMatrixTX.toExternal() = when (this.value) {
  ScoringMatrixTXType.Blosum45 -> TBlastXMatrix.BLOSUM45
  ScoringMatrixTXType.Blosum50 -> TBlastXMatrix.BLOSUM50
  ScoringMatrixTXType.Blosum62 -> TBlastXMatrix.BLOSUM62
  ScoringMatrixTXType.Blosum80 -> TBlastXMatrix.BLOSUM80
  ScoringMatrixTXType.Blosum90 -> TBlastXMatrix.BLOSUM90
  ScoringMatrixTXType.Pam30    -> TBlastXMatrix.PAM30
  ScoringMatrixTXType.Pam70    -> TBlastXMatrix.PAM70
  ScoringMatrixTXType.Pam250   -> TBlastXMatrix.PAM250
  ScoringMatrixTXType.None     -> throw IllegalStateException()
}
