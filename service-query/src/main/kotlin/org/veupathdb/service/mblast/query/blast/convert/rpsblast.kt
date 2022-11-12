package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpsblast.RPSBlast
import org.veupathdb.lib.blast.rpsblast.fields.CompBasedStatsRPS
import org.veupathdb.lib.blast.rpsblast.fields.CompBasedStatsRPSValue
import org.veupathdb.lib.blast.rpsblast.fields.SegRPS
import org.veupathdb.lib.blast.rpsblast.fields.SoftMaskingRPS
import org.veupathdb.service.mblast.query.generated.model.*

fun RPSBlastConfig.toInternal() = Blast.rpsblast().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal() ?: it.seg
  if (softMasking             != null) it.softMasking                  = SoftMaskingRPS(softMasking)
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
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
  if (useSWTraceback          != null) it.useSmithWatermanTraceback    = UseSmithWatermanTraceback(useSWTraceback)
}

fun RPSBlast.toExternal(): RPSBlastConfig = RPSBlastConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
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
  if (!parseDefLines.isDefault)                it.parseDefLines           = parseDefLines.value
  if (!useSmithWatermanTraceback.isDefault)    it.useSWTraceback          = useSmithWatermanTraceback.value
}

private const val DefSegWindow = 12
private const val DefSegLocut  = 2.2
private const val DefSegHicut  = 2.5

private fun BlastSeg.toInternal(): SegRPS? {
  if (enabled == false)
    return SegRPS.no()

  val allNull = window == null && locut == null && hicut == null

  if (enabled == true) {
    return if (allNull)
      SegRPS.yes()
    else
      SegRPS.of(
        window ?: DefSegWindow,
        locut  ?: DefSegLocut,
        hicut  ?: DefSegHicut,
      )
  }

  return if (allNull)
    null
  else
    SegRPS.of(
      window ?: DefSegWindow,
      locut  ?: DefSegLocut,
      hicut  ?: DefSegHicut,
    )
}

private fun SegRPS.toExternal(): BlastSeg =
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

private fun RPSBlastCompBasedStats.toInternal() = CompBasedStatsRPS(when (this) {
  RPSBlastCompBasedStats.SIMPLIFIED     -> CompBasedStatsRPSValue.SimplifiedCompBasedStats
  RPSBlastCompBasedStats.COMPBASEDSTATS -> CompBasedStatsRPSValue.CompBasedStats
})

private fun CompBasedStatsRPS.toExternal() = when (this.value) {
  CompBasedStatsRPSValue.SimplifiedCompBasedStats -> RPSBlastCompBasedStats.SIMPLIFIED
  CompBasedStatsRPSValue.CompBasedStats           -> RPSBlastCompBasedStats.COMPBASEDSTATS
}