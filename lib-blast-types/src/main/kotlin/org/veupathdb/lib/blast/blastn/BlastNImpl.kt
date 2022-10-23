package org.veupathdb.lib.blast.blastn

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.blastn.fields.*
import org.veupathdb.lib.blast.common.BlastQueryWithListsImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap

internal class BlastNImpl(
  shortHelp:                HelpShort                = HelpShort(),
  longHelp:                 HelpLong                 = HelpLong(),
  version:                  Version                  = Version(),
  outFile:                  OutFile                  = OutFile(),
  outFormat:                OutFormat                = OutFormat(),
  showGIs:                  ShowGIs                  = ShowGIs(),
  numDescriptions:          NumDescriptions          = NumDescriptions(),
  numAlignments:            NumAlignments            = NumAlignments(),
  lineLength:               LineLength               = LineLength(),
  html:                     HTML                     = HTML(),
  sortHits:                 SortHits                 = SortHits(),
  sortHSPs:                 SortHSPs                 = SortHSPs(),
  maxTargetSeqs:            MaxTargetSeqs            = MaxTargetSeqs(),
  parseDefLines:            ParseDefLines            = ParseDefLines(),
  queryFile:                QueryFile                = QueryFile(),
  queryLocation:            QueryLocation            = QueryLocation(),
  dbFile:                   DBFiles                  = DBFiles(),
  expectValue:              ExpectValue              = ExpectValue(),
  lowercaseMasking:         LowercaseMasking         = LowercaseMasking(),
  entrezQuery:              EntrezQuery              = EntrezQuery(),
  maxHSPs:                  MaxHSPs                  = MaxHSPs(),
  dbSize:                   DBSize                   = DBSize(),
  searchSpace:              SearchSpace              = SearchSpace(),
  importSearchStrategy:     ImportSearchStrategy     = ImportSearchStrategy(),
  exportSearchStrategy:     ExportSearchStrategy     = ExportSearchStrategy(),
  extensionDropoffUngapped: ExtensionDropoffUngapped = ExtensionDropoffUngapped(),
  windowSize:               WindowSize               = WindowSize(),
  remote:                   Remote                   = Remote(),
  giList:                   GIList                   = GIList(),
  negativeGIList:           NegativeGIList           = NegativeGIList(),
  seqIdList:                SeqIDList                = SeqIDList(),
  negativeSeqIdList:        NegativeSeqIDList        = NegativeSeqIDList(),
  taxIdList:                TaxIDList                = TaxIDList(),
  negativeTaxIdList:        NegativeTaxIDList        = NegativeTaxIDList(),
  taxIds:                   TaxIDs                   = TaxIDs(),
  negativeTaxIds:           NegativeTaxIDs           = NegativeTaxIDs(),

  override var strand:                       Strand                       = Strand(),
  override var task:                         BlastNTask                   = BlastNTask(),
  override var wordSize:                     WordSizeN                    = WordSizeN(),
  override var gapOpen:                      GapOpen                      = GapOpen(),
  override var gapExtend:                    GapExtend                    = GapExtend(),
  override var penalty:                      Penalty                      = Penalty(),
  override var reward:                       Reward                       = Reward(),
  override var useIndex:                     UseIndex                     = UseIndex(),
  override var indexName:                    IndexName                    = IndexName(),
  override var subjectFile:                  SubjectFile                  = SubjectFile(),
  override var subjectLocation:              SubjectLocation              = SubjectLocation(),
  override var dust:                         Dust                         = ValDust(),
  override var filteringDBFile:              FilteringDB                  = FilteringDB(),
  override var windowMaskerTaxID:            WindowMaskerTaxID            = WindowMaskerTaxID(),
  override var windowMaskerDBFile:           WindowMaskerDB               = WindowMaskerDB(),
  override var dbSoftMask:                   DBSoftMask                   = DBSoftMask(),
  override var dbHardMask:                   DBHardMask                   = DBHardMask(),
  override var percentIdentity:              PercentIdentity              = PercentIdentity(),
  override var queryCoverageHSPPercent:      QueryCoverageHSPPercent      = QueryCoverageHSPPercent(),
  override var cullingLimit:                 CullingLimit                 = CullingLimit(),
  override var templateType:                 TemplateType                 = TemplateType(),
  override var templateLength:               TemplateLength               = TemplateLength(),
  override var sumStats:                     SumStats                     = SumStats(),
  override var extensionDropoffPrelimGapped: ExtensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(),
  override var extensionDropoffFinalGapped:  ExtensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(),
  override var nonGreedy:                    NonGreedy                    = NonGreedy(),
  override var minRawGappedScore:            MinRawGappedScore            = MinRawGappedScore(),
  override var ungappedAlignmentsOnly:       UngappedAlignmentsOnly       = UngappedAlignmentsOnly(),
  override var offDiagonalRange:             OffDiagonalRange             = OffDiagonalRange(),
  override var numCPUCores:                  NumCPUCores                  = NumCPUCores(),
  override var bestHitOverhang:              BestHitOverhang              = BestHitOverhang(),
  override var bestHitScoreEdge:             BestHitScoreEdge             = BestHitScoreEdge(),
  override var subjectBestHit:               SubjectBestHit               = SubjectBestHit(),
  override var softMasking:                  SoftMaskingN                 = SoftMaskingN()
) : BlastN, BlastQueryWithListsImpl(
  BlastTool.BlastN,
  shortHelp,
  longHelp,
  version,
  outFile,
  outFormat,
  showGIs,
  numDescriptions,
  numAlignments,
  lineLength,
  html,
  sortHits,
  sortHSPs,
  maxTargetSeqs,
  parseDefLines,
  queryFile,
  queryLocation,
  dbFile,
  expectValue,
  lowercaseMasking,
  entrezQuery,
  maxHSPs,
  dbSize,
  searchSpace,
  importSearchStrategy,
  exportSearchStrategy,
  extensionDropoffUngapped,
  windowSize,
  remote,
  giList,
  negativeGIList,
  seqIdList,
  negativeSeqIdList,
  taxIdList,
  negativeTaxIdList,
  taxIds,
  negativeTaxIds,
) {
  constructor(js: ObjectNode) : this(
    ParseHelpShort(js),
    ParseHelpLong(js),
    ParseVersion(js),
    ParseOutFile(js),
    ParseOutFormat(js),
    ParseShowGIs(js),
    ParseNumDescriptions(js),
    ParseNumAlignments(js),
    ParseLineLength(js),
    ParseHTML(js),
    ParseSortHits(js),
    ParseSortHSPs(js),
    ParseMaxTargetSeqs(js),
    ParseParseDefLines(js),
    ParseQueryFile(js),
    ParseQueryLocation(js),
    ParseDBFiles(js),
    ParseEValue(js),
    ParseLowercaseMasking(js),
    ParseEntrezQuery(js),
    ParseMaxHSPs(js),
    ParseDBSize(js),
    ParseSearchSpace(js),
    ParseImportSearchStrategy(js),
    ParseExportSearchStrategy(js),
    ParseXDropUngap(js),
    ParseWindowSize(js),
    ParseRemote(js),
    ParseGIList(js),
    ParseNegGIList(js),
    ParseSeqIDList(js),
    ParseNegSeqIDList(js),
    ParseTaxIDList(js),
    ParseNegTaxIDList(js),
    ParseTaxIDs(js),
    ParseNegTaxIDs(js),
    ParseStrand(js),
    ParseBlastNTask(js),
    ParseWordSizeN(js),
    ParseGapOpen(js),
    ParseGapExtend(js),
    ParsePenalty(js),
    ParseReward(js),
    ParseUseIndex(js),
    ParseIndexName(js),
    ParseSubjectFile(js),
    ParseSubjectLocation(js),
    ParseDust(js),
    ParseFilteringDB(js),
    ParseWindowMaskerTaxID(js),
    ParseWindowMaskerDB(js),
    ParseDBSoftMask(js),
    ParseDBHardMask(js),
    ParsePercentIdentity(js),
    ParseQueryCoverageHSPPercent(js),
    ParseCullingLimit(js),
    ParseTemplateType(js),
    ParseTemplateLength(js),
    ParseSumStats(js),
    ParseXDropGap(js),
    ParseXDropGapFinal(js),
    ParseNonGreedy(js),
    ParseMinRawGappedScore(js),
    ParseUngappedAlignmentsOnly(js),
    ParseOffDiagonalRange(js),
    ParseNumCPUCores(js),
    ParseBestHitOverhang(js),
    ParseBestHitScoreEdge(js),
    ParseSubjectBestHit(js),
    ParseSoftMaskingN(js),
  )

  override fun strand(value: StrandType) { strand = Strand(value) }
  override fun task(value: BlastNTaskType) { task = BlastNTask(value) }
  override fun wordSize(value: UInt) { wordSize = WordSizeN(value) }
  override fun gapOpen(value: Int) { gapOpen = GapOpen(value) }
  override fun gapExtend(value: Int) { gapExtend = GapExtend(value) }
  override fun penalty(value: Int) { penalty = Penalty(value) }
  override fun reward(value: UInt) { reward = Reward(value) }
  override fun useIndex(value: Boolean) { useIndex = UseIndex(value) }
  override fun indexName(value: String) { indexName = IndexName(value) }
  override fun subjectFile(value: String) { subjectFile = SubjectFile(value) }
  override fun subjectLocation(start: UInt, stop: UInt) { subjectLocation = SubjectLocation(start, stop) }
  override fun filteringDBFile(value: String) { filteringDBFile = FilteringDB(value) }
  override fun windowMaskerTaxID(value: Int) { windowMaskerTaxID = WindowMaskerTaxID(value) }
  override fun windowMaskerDBFile(value: String) { windowMaskerDBFile = WindowMaskerDB(value) }
  override fun dbSoftMask(value: String) { dbSoftMask = DBSoftMask(value) }
  override fun dbHardMask(value: String) { dbHardMask = DBHardMask(value) }
  override fun percentIdentity(value: Double) { percentIdentity = PercentIdentity(value) }
  override fun queryCoverageHSPPercent(value: Double) { queryCoverageHSPPercent = QueryCoverageHSPPercent(value) }
  override fun cullingLimit(value: UInt) { cullingLimit = CullingLimit(value) }
  override fun templateType(value: TemplateTypeValue) { templateType = TemplateType(value) }
  override fun templateLength(value: TemplateLengthValue) { templateLength = TemplateLength(value) }
  override fun sumStats(value: Boolean) { sumStats = SumStats(value) }
  override fun extensionDropoffPrelimGapped(value: Double) { extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(value) }
  override fun extensionDropoffFinalGapped(value: Double) { extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(value) }
  override fun nonGreedy(value: Boolean) { nonGreedy = NonGreedy(value) }
  override fun minRawGappedScore(value: Int) { minRawGappedScore = MinRawGappedScore(value) }
  override fun ungappedAlignmentsOnly(value: Boolean) { ungappedAlignmentsOnly = UngappedAlignmentsOnly(value) }
  override fun offDiagonalRange(value: UInt) { offDiagonalRange = OffDiagonalRange(value) }
  override fun numCPUCores(value: UByte) { numCPUCores = NumCPUCores(value) }
  override fun bestHitOverhang(value: Double) { bestHitOverhang = BestHitOverhang(value) }
  override fun bestHitScoreEdge(value: Double) { bestHitScoreEdge = BestHitScoreEdge(value) }
  override fun subjectBestHit(value: Boolean) { subjectBestHit = SubjectBestHit(value) }
  override fun softMasking(value: Boolean) { softMasking = SoftMaskingN(value) }

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    strand.appendJson(js)
    task.appendJson(js)
    wordSize.appendJson(js)
    gapOpen.appendJson(js)
    gapExtend.appendJson(js)
    penalty.appendJson(js)
    reward.appendJson(js)
    useIndex.appendJson(js)
    indexName.appendJson(js)
    subjectFile.appendJson(js)
    subjectLocation.appendJson(js)
    dust.appendJson(js)
    filteringDBFile.appendJson(js)
    windowMaskerTaxID.appendJson(js)
    windowMaskerDBFile.appendJson(js)
    dbSoftMask.appendJson(js)
    dbHardMask.appendJson(js)
    percentIdentity.appendJson(js)
    queryCoverageHSPPercent.appendJson(js)
    cullingLimit.appendJson(js)
    templateType.appendJson(js)
    templateLength.appendJson(js)
    sumStats.appendJson(js)
    extensionDropoffPrelimGapped.appendJson(js)
    extensionDropoffFinalGapped.appendJson(js)
    nonGreedy.appendJson(js)
    minRawGappedScore.appendJson(js)
    ungappedAlignmentsOnly.appendJson(js)
    offDiagonalRange.appendJson(js)
    numCPUCores.appendJson(js)
    bestHitOverhang.appendJson(js)
    bestHitScoreEdge.appendJson(js)
    subjectBestHit.appendJson(js)
    softMasking.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    strand.appendCliSegment(sb)
    task.appendCliSegment(sb)
    wordSize.appendCliSegment(sb)
    gapOpen.appendCliSegment(sb)
    gapExtend.appendCliSegment(sb)
    penalty.appendCliSegment(sb)
    reward.appendCliSegment(sb)
    useIndex.appendCliSegment(sb)
    indexName.appendCliSegment(sb)
    subjectFile.appendCliSegment(sb)
    subjectLocation.appendCliSegment(sb)
    dust.appendCliSegment(sb)
    filteringDBFile.appendCliSegment(sb)
    windowMaskerTaxID.appendCliSegment(sb)
    windowMaskerDBFile.appendCliSegment(sb)
    dbSoftMask.appendCliSegment(sb)
    dbHardMask.appendCliSegment(sb)
    percentIdentity.appendCliSegment(sb)
    queryCoverageHSPPercent.appendCliSegment(sb)
    cullingLimit.appendCliSegment(sb)
    templateType.appendCliSegment(sb)
    templateLength.appendCliSegment(sb)
    sumStats.appendCliSegment(sb)
    extensionDropoffPrelimGapped.appendCliSegment(sb)
    extensionDropoffFinalGapped.appendCliSegment(sb)
    nonGreedy.appendCliSegment(sb)
    minRawGappedScore.appendCliSegment(sb)
    ungappedAlignmentsOnly.appendCliSegment(sb)
    offDiagonalRange.appendCliSegment(sb)
    numCPUCores.appendCliSegment(sb)
    bestHitOverhang.appendCliSegment(sb)
    bestHitScoreEdge.appendCliSegment(sb)
    subjectBestHit.appendCliSegment(sb)
    softMasking.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    strand.appendCliParts(cli)
    task.appendCliParts(cli)
    wordSize.appendCliParts(cli)
    gapOpen.appendCliParts(cli)
    gapExtend.appendCliParts(cli)
    penalty.appendCliParts(cli)
    reward.appendCliParts(cli)
    useIndex.appendCliParts(cli)
    indexName.appendCliParts(cli)
    subjectFile.appendCliParts(cli)
    subjectLocation.appendCliParts(cli)
    dust.appendCliParts(cli)
    filteringDBFile.appendCliParts(cli)
    windowMaskerTaxID.appendCliParts(cli)
    windowMaskerDBFile.appendCliParts(cli)
    dbSoftMask.appendCliParts(cli)
    dbHardMask.appendCliParts(cli)
    percentIdentity.appendCliParts(cli)
    queryCoverageHSPPercent.appendCliParts(cli)
    cullingLimit.appendCliParts(cli)
    templateType.appendCliParts(cli)
    templateLength.appendCliParts(cli)
    sumStats.appendCliParts(cli)
    extensionDropoffPrelimGapped.appendCliParts(cli)
    extensionDropoffFinalGapped.appendCliParts(cli)
    nonGreedy.appendCliParts(cli)
    minRawGappedScore.appendCliParts(cli)
    ungappedAlignmentsOnly.appendCliParts(cli)
    offDiagonalRange.appendCliParts(cli)
    numCPUCores.appendCliParts(cli)
    bestHitOverhang.appendCliParts(cli)
    bestHitScoreEdge.appendCliParts(cli)
    subjectBestHit.appendCliParts(cli)
    softMasking.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    super.validate(errs)

    if (task.value != BlastNTaskType.Megablast) {
      if (!useIndex.isDefault)
        errs.addError(useIndex.name, "Requires task type ${BlastNTaskType.Megablast.value}")
      if (!indexName.isDefault)
        errs.addError(indexName.name, "Requires task type ${BlastNTaskType.Megablast.value}")
    }

    if (task.value != BlastNTaskType.DiscontiguousMegablast) {
      if (!templateType.isDefault)
        errs.addError(templateType.name, "Requires task type ${BlastNTaskType.DiscontiguousMegablast.value}")
      if (!templateLength.isDefault)
        errs.addError(templateLength.name, "Requires task type ${BlastNTaskType.DiscontiguousMegablast.value}")
    }

    errs.incompatible(subjectFile, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, dbSoftMask, dbHardMask)
    errs.incompatible(subjectLocation, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, dbSoftMask, dbHardMask, remote)
    errs.incompatible(dbSoftMask, dbHardMask)
    errs.incompatible(cullingLimit, bestHitOverhang, bestHitScoreEdge)
    errs.incompatible(numCPUCores, remote)
  }

  override fun clone(): BlastN {
    val cli = BlastNImpl()

    super.copyInto(cli)

    cli.strand                       = strand.clone()
    cli.task                         = task.clone()
    cli.wordSize                     = wordSize.clone()
    cli.gapOpen                      = gapOpen.clone()
    cli.gapExtend                    = gapExtend.clone()
    cli.penalty                      = penalty.clone()
    cli.reward                       = reward.clone()
    cli.useIndex                     = useIndex.clone()
    cli.indexName                    = indexName.clone()
    cli.subjectFile                  = subjectFile.clone()
    cli.subjectLocation              = subjectLocation.clone()
    cli.dust                         = dust.clone()
    cli.filteringDBFile              = filteringDBFile.clone()
    cli.windowMaskerTaxID            = windowMaskerTaxID.clone()
    cli.windowMaskerDBFile           = windowMaskerDBFile.clone()
    cli.dbSoftMask                   = dbSoftMask.clone()
    cli.dbHardMask                   = dbHardMask.clone()
    cli.percentIdentity              = percentIdentity.clone()
    cli.queryCoverageHSPPercent      = queryCoverageHSPPercent.clone()
    cli.cullingLimit                 = cullingLimit.clone()
    cli.templateType                 = templateType.clone()
    cli.templateLength               = templateLength.clone()
    cli.sumStats                     = sumStats.clone()
    cli.extensionDropoffPrelimGapped = extensionDropoffPrelimGapped.clone()
    cli.extensionDropoffFinalGapped  = extensionDropoffFinalGapped.clone()
    cli.nonGreedy                    = nonGreedy.clone()
    cli.minRawGappedScore            = minRawGappedScore.clone()
    cli.ungappedAlignmentsOnly       = ungappedAlignmentsOnly.clone()
    cli.offDiagonalRange             = offDiagonalRange.clone()
    cli.numCPUCores                  = numCPUCores.clone()
    cli.bestHitOverhang              = bestHitOverhang.clone()
    cli.bestHitScoreEdge             = bestHitScoreEdge.clone()
    cli.subjectBestHit               = subjectBestHit.clone()
    cli.softMasking                  = softMasking.clone()

    return cli
  }
}
