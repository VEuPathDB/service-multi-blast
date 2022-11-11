package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpstblastn.RPSTBlastN
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTNValue
import org.veupathdb.lib.blast.rpstblastn.fields.SegRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.SoftMaskingRPSTN
import org.veupathdb.service.mblast.query.generated.model.*

fun RPSTBlastNConfig.toInternal() = Blast.rpstblastn().also {
  if (queryGenCode            != null) it.queryGenCode                 = QueryGenCode(queryGenCode.toUByte())
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (strand                  != null) it.strand                       = strand.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (compBasedStats          != null) it.compBasedStats               = compBasedStats.toInternal()
  if (seg                     != null) it.seg                          = seg.toInternal() ?: it.seg
  if (softMasking             != null) it.softMasking                  = SoftMaskingRPSTN(softMasking)
  if (queryCoverageHSPPercent != null) it.queryCoverageHSPPercent      = QueryCoverageHSPPercent(queryCoverageHSPPercent)
  if (maxHSPs                 != null) it.maxHSPs                      = MaxHSPs(maxHSPs.toUInt())
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

fun RPSTBlastN.toExternal(): RPSTBlastNConfig = RPSTBlastNConfigImpl().also {
  if (!queryGenCode.isDefault)                 it.queryGenCode            = queryGenCode.value.toByte()
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!strand.isDefault)                       it.strand                  = strand.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
  if (!compBasedStats.isDefault)               it.compBasedStats          = compBasedStats.toExternal()
  if (!seg.isDefault)                          it.seg                     = seg.toExternal()
  if (!softMasking.isDefault)                  it.softMasking             = softMasking.value
  if (!queryCoverageHSPPercent.isDefault)      it.queryCoverageHSPPercent = queryCoverageHSPPercent.value
  if (!maxHSPs.isDefault)                      it.maxHSPs                 = maxHSPs.value.toInt()
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

private fun BlastSeg.toInternal(): SegRPSTN? {
  if (enabled == false)
    return SegRPSTN.no()

  val allNull = window == null && locut == null && hicut == null

  if (enabled == true) {
    return if (allNull)
      SegRPSTN.yes()
    else
      SegRPSTN.of(
        window ?: DefSegWindow,
        locut  ?: DefSegLocut,
        hicut  ?: DefSegHicut,
      )
  }

  return if (allNull)
    null
  else
    SegRPSTN.of(
      window ?: DefSegWindow,
      locut  ?: DefSegLocut,
      hicut  ?: DefSegHicut,
    )
}

private fun SegRPSTN.toExternal(): BlastSeg =
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

private fun RPSTBlastNCompBasedStats.toInternal() = CompBasedStatsRPSTN(when (this) {
  RPSTBlastNCompBasedStats.NONE           -> CompBasedStatsRPSTNValue.NoCompBasedStats
  RPSTBlastNCompBasedStats.COMPBASEDSTATS -> CompBasedStatsRPSTNValue.CompBasedStats
})

private fun CompBasedStatsRPSTN.toExternal() = when (this.value) {
  CompBasedStatsRPSTNValue.NoCompBasedStats -> RPSTBlastNCompBasedStats.NONE
  CompBasedStatsRPSTNValue.CompBasedStats   -> RPSTBlastNCompBasedStats.COMPBASEDSTATS
}