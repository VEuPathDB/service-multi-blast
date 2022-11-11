package org.veupathdb.service.mblast.query.blast.convert

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.blastn.BlastN
import org.veupathdb.lib.blast.blastn.fields.*
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.service.mblast.query.generated.model.*
import org.veupathdb.service.mblast.query.generated.model.BlastNTask as IOBlastNTask

fun BlastNConfig.toInternal() = Blast.blastn().also {
  if (queryLocation           != null) it.queryLocation                = queryLocation.toInternal()
  if (strand                  != null) it.strand                       = strand.toInternal()
  if (task                    != null) it.task                         = task.toInternal()
  if (eValue                  != null) it.expectValue                  = ExpectValue(eValue)
  if (wordSize                != null) it.wordSize                     = WordSizeN(wordSize.toUInt())
  if (gapOpen                 != null) it.gapOpen                      = GapOpen(gapOpen)
  if (gapExtend               != null) it.gapExtend                    = GapExtend(gapExtend)
  if (penalty                 != null) it.penalty                      = Penalty(penalty)
  if (reward                  != null) it.reward                       = Reward(reward.toUInt())
  if (useIndex                != null) it.useIndex                     = UseIndex(useIndex)
  if (indexName               != null) it.indexName                    = IndexName(indexName)
  if (dust                    != null) it.dust                         = dust.toInternal()
  if (softMasking             != null) it.softMasking                  = SoftMaskingN(softMasking)
  if (lowercaseMasking        != null) it.lowercaseMasking             = LowercaseMasking(lowercaseMasking)
  if (dbSoftMask              != null) it.dbSoftMask                   = DBSoftMask(dbSoftMask)
  if (dbHardMask              != null) it.dbHardMask                   = DBHardMask(dbHardMask)
  if (percentIdentity         != null) it.percentIdentity              = PercentIdentity(percentIdentity)
  if (queryCoverageHSPPercent != null) it.queryCoverageHSPPercent      = QueryCoverageHSPPercent(queryCoverageHSPPercent)
  if (maxHSPs                 != null) it.maxHSPs                      = MaxHSPs(maxHSPs.toUInt())
  if (cullingLimit            != null) it.cullingLimit                 = CullingLimit(cullingLimit.toUInt())
  if (bestHitOverhang         != null) it.bestHitOverhang              = BestHitOverhang(bestHitOverhang)
  if (bestHitScoreEdge        != null) it.bestHitScoreEdge             = BestHitScoreEdge(bestHitScoreEdge)
  if (subjectBestHit          != null) it.subjectBestHit               = SubjectBestHit(subjectBestHit)
  if (maxTargetSequences      != null) it.maxTargetSeqs                = MaxTargetSeqs(maxTargetSequences.toUInt())
  if (templateType            != null) it.templateType                 = templateType.toInternal()
  if (templateLength          != null) it.templateLength               = templateLength.toTemplateLength()
  if (dbSize                  != null) it.dbSize                       = DBSize(dbSize)
  if (searchSpace             != null) it.searchSpace                  = SearchSpace(searchSpace)
  if (sumStats                != null) it.sumStats                     = SumStats(sumStats)
  if (xDropoffUngapped        != null) it.extensionDropoffUngapped     = ExtensionDropoffUngapped(xDropoffUngapped)
  if (xDropoffPrelimGapped    != null) it.extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(xDropoffPrelimGapped)
  if (xDropoffFinalGapped     != null) it.extensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(xDropoffFinalGapped)
  if (nonGreedy               != null) it.nonGreedy                    = NonGreedy(nonGreedy)
  if (minRawGappedScore       != null) it.minRawGappedScore            = MinRawGappedScore(minRawGappedScore)
  if (ungappedOnly            != null) it.ungappedAlignmentsOnly       = UngappedAlignmentsOnly(ungappedOnly)
  if (windowSize              != null) it.windowSize                   = WindowSize(windowSize.toUInt())
  if (offDiagonalRange        != null) it.offDiagonalRange             = OffDiagonalRange(offDiagonalRange.toUInt())
  if (parseDefLines           != null) it.parseDefLines                = ParseDefLines(parseDefLines)
}

fun BlastN.toExternal(): BlastNConfig = BlastNConfigImpl().also {
  if (!queryLocation.isDefault)                it.queryLocation           = queryLocation.toExternal()
  if (!strand.isDefault)                       it.strand                  = strand.toExternal()
  if (!task.isDefault)                         it.task                    = task.toExternal()
  if (!expectValue.isDefault)                  it.eValue                  = expectValue.value
  if (!wordSize.isDefault)                     it.wordSize                = wordSize.value.toInt()
  if (!gapOpen.isDefault)                      it.gapOpen                 = gapOpen.value
  if (!gapExtend.isDefault)                    it.gapExtend               = gapExtend.value
  if (!penalty.isDefault)                      it.penalty                 = penalty.value
  if (!reward.isDefault)                       it.reward                  = reward.value.toInt()
  if (!useIndex.isDefault)                     it.useIndex                = useIndex.value
  if (!indexName.isDefault)                    it.indexName               = indexName.value
  if (!dust.isDefault)                         it.dust                    = dust.toExternal()
  if (!softMasking.isDefault)                  it.softMasking             = softMasking.value
  if (!lowercaseMasking.isDefault)             it.lowercaseMasking        = lowercaseMasking.value
  if (!dbSoftMask.isDefault)                   it.dbSoftMask              = dbSoftMask.value
  if (!dbHardMask.isDefault)                   it.dbHardMask              = dbHardMask.value
  if (!percentIdentity.isDefault)              it.percentIdentity         = percentIdentity.value
  if (!queryCoverageHSPPercent.isDefault)      it.queryCoverageHSPPercent = queryCoverageHSPPercent.value
  if (!maxHSPs.isDefault)                      it.maxHSPs                 = maxHSPs.value.toInt()
  if (!cullingLimit.isDefault)                 it.cullingLimit            = cullingLimit.value.toInt()
  if (!bestHitOverhang.isDefault)              it.bestHitOverhang         = bestHitOverhang.value
  if (!bestHitScoreEdge.isDefault)             it.bestHitScoreEdge        = bestHitScoreEdge.value
  if (!subjectBestHit.isDefault)               it.subjectBestHit          = subjectBestHit.value
  if (!maxTargetSeqs.isDefault)                it.maxTargetSequences      = maxTargetSeqs.value.toInt()
  if (!templateType.isDefault)                 it.templateType            = templateType.toExternal()
  if (!templateLength.isDefault)               it.templateLength          = templateLength.toExternal()
  if (!dbSize.isDefault)                       it.dbSize                  = dbSize.value
  if (!searchSpace.isDefault)                  it.searchSpace             = searchSpace.value
  if (!sumStats.isDefault)                     it.sumStats                = sumStats.value
  if (!extensionDropoffUngapped.isDefault)     it.xDropoffUngapped        = extensionDropoffUngapped.value
  if (!extensionDropoffPrelimGapped.isDefault) it.xDropoffPrelimGapped    = extensionDropoffPrelimGapped.value
  if (!extensionDropoffFinalGapped.isDefault)  it.xDropoffFinalGapped     = extensionDropoffFinalGapped.value
  if (!nonGreedy.isDefault)                    it.nonGreedy               = nonGreedy.value
  if (!minRawGappedScore.isDefault)            it.minRawGappedScore       = minRawGappedScore.value
  if (!ungappedAlignmentsOnly.isDefault)       it.ungappedOnly            = ungappedAlignmentsOnly.value
  if (!windowSize.isDefault)                   it.windowSize              = windowSize.value.toInt()
  if (!offDiagonalRange.isDefault)             it.offDiagonalRange        = offDiagonalRange.value.toInt()
  if (!parseDefLines.isDefault)                it.parseDefLines           = parseDefLines.value
}

//
// Task
//

private fun IOBlastNTask.toInternal() = BlastNTask(when(this) {
  IOBlastNTask.BLASTN      -> BlastNTaskType.BlastN
  IOBlastNTask.BLASTNSHORT -> BlastNTaskType.BlastNShort
  IOBlastNTask.DCMEGABLAST -> BlastNTaskType.DiscontiguousMegablast
  IOBlastNTask.MEGABLAST   -> BlastNTaskType.Megablast
  IOBlastNTask.RMBLASTN    -> BlastNTaskType.RMBlastN
})

private fun BlastNTask.toExternal() = value.toExternal()

private fun BlastNTaskType.toExternal() = when(this) {
  BlastNTaskType.BlastN                 -> IOBlastNTask.BLASTN
  BlastNTaskType.BlastNShort            -> IOBlastNTask.BLASTNSHORT
  BlastNTaskType.DiscontiguousMegablast -> IOBlastNTask.DCMEGABLAST
  BlastNTaskType.Megablast              -> IOBlastNTask.MEGABLAST
  BlastNTaskType.RMBlastN               -> IOBlastNTask.RMBLASTN
}


//
// Dust
//

private const val DefDustLevel  = 20
private const val DefDustWindow = 64
private const val DefDustLinker = 1
private fun BlastNDust.toInternal(): Dust {
  if (enable == false)
    return Dust.no()

  return Dust.of(
    level  ?: DefDustLevel,
    window ?: DefDustWindow,
    linker ?: DefDustLinker,
  )
}

private fun Dust.toExternal() : BlastNDust = BlastNDustImpl().also {
  if (isYes) {
    it.enable = true
    it.level  = DefDustLevel
    it.window = DefDustWindow
    it.linker = DefDustLinker
  } else if (isNo) {
    it.enable = false
  } else {
    it.enable = true
    it.level  = level
    it.window = window
    it.linker = linker
  }
}

//
// Template Type
//

private fun BlastNTemplateType.toInternal() = TemplateType(when(this) {
  BlastNTemplateType.CODING           -> TemplateTypeType.Coding
  BlastNTemplateType.CODINGANDOPTIMAL -> TemplateTypeType.CodingAndOptimal
  BlastNTemplateType.OPTIMAL          -> TemplateTypeType.Optimal
})

private fun TemplateType.toExternal(): BlastNTemplateType =
  when(this.value) {
    TemplateTypeType.Coding           -> BlastNTemplateType.CODING
    TemplateTypeType.CodingAndOptimal -> BlastNTemplateType.CODINGANDOPTIMAL
    TemplateTypeType.Optimal          -> BlastNTemplateType.OPTIMAL
    TemplateTypeType.None             -> throw IllegalStateException()
  }

//
// Template Length
//

private fun Int.toTemplateLength() = TemplateLength(when(this) {
  TemplateLengthValue.Sixteen.value   -> TemplateLengthValue.Sixteen
  TemplateLengthValue.Eighteen.value  -> TemplateLengthValue.Eighteen
  TemplateLengthValue.TwentyOne.value -> TemplateLengthValue.TwentyOne
  else                                -> throw IllegalArgumentException("Invalid template length value $this")
})

private fun TemplateLength.toExternal(): Int = value.value
