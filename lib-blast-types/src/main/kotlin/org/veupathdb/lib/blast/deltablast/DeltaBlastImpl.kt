package org.veupathdb.lib.blast.deltablast

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.BlastQueryWithListsImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.deltablast.fields.*
import org.veupathdb.lib.blast.err.ErrorMap

internal class DeltaBlastImpl(
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
  giListFile:               GIList                   = GIList(),
  negativeGIListFile:       NegativeGIList           = NegativeGIList(),
  seqIDListFile:            SeqIDList                = SeqIDList(),
  negativeSeqIDListFile:    NegativeSeqIDList        = NegativeSeqIDList(),
  taxIDListFile:            TaxIDList                = TaxIDList(),
  negativeTaxIDListFile:    NegativeTaxIDList        = NegativeTaxIDList(),
  taxIds:                   TaxIDs                   = TaxIDs(),
  negativeTaxIds:           NegativeTaxIDs           = NegativeTaxIDs(),

  override var wordSize:                       WordSizeDelta                  = WordSizeDelta(),
  override var gapOpen:                        GapOpen                        = GapOpen(),
  override var gapExtend:                      GapExtend                      = GapExtend(),
  override var matrix:                         ScoringMatrixDelta                    = ScoringMatrixDelta(),
  override var threshold:                      Threshold                      = Threshold(),
  override var compBasedStats:                 CompBasedStatsDelta            = CompBasedStatsDelta(),
  override var subjectFile:                    SubjectFile                    = SubjectFile(),
  override var subjectLocation:                SubjectLocation                = SubjectLocation(),
  override var seg:                            SegDelta                       = NoSegD,
  override var softMasking:                    SoftMaskingDelta               = SoftMaskingDelta(),
  override var queryCoverageHSPPercent:        QueryCoverageHSPPercent        = QueryCoverageHSPPercent(),
  override var cullingLimit:                   CullingLimit                   = CullingLimit(),
  override var bestHitOverhang:                BestHitOverhang                = BestHitOverhang(),
  override var bestHitScoreEdge:               BestHitScoreEdge               = BestHitScoreEdge(),
  override var subjectBestHit:                 SubjectBestHit                 = SubjectBestHit(),
  override var sumStats:                       SumStats                       = SumStats(),
  override var extensionDropoffPrelimGapped:   ExtensionDropoffPrelimGapped   = ExtensionDropoffPrelimGapped(),
  override var extensionDropoffFinalGapped:    ExtensionDropoffFinalGapped    = ExtensionDropoffFinalGapped(),
  override var gapTrigger:                     GapTrigger                     = GapTrigger(),
  override var numCPUCores:                    NumCPUCores                    = NumCPUCores(),
  override var useSmithWatermanTraceback:      UseSmithWatermanTraceback      = UseSmithWatermanTraceback(),
  override var numIterations:                  NumIterations                  = NumIterations(),
  override var outPSSMFile:                    OutPSSMFile                    = OutPSSMFile(),
  override var outASCIIPSSMFile:               OutASCIIPSSMFile               = OutASCIIPSSMFile(),
  override var savePSSMAfterLastRound:         SavePSSMAfterLastRound         = SavePSSMAfterLastRound(),
  override var saveEachPSSM:                   SaveEachPSSM                   = SaveEachPSSM(),
  override var pseudoCount:                    PseudoCount                    = PseudoCount(),
  override var domainInclusionEValueThreshold: DomainInclusionEValueThreshold = DomainInclusionEValueThreshold(),
  override var inclusionEValueThreshold:       InclusionEValueThreshold       = InclusionEValueThreshold(),
  override var rpsDB:                          RPSDB                          = RPSDB(),
  override var showDomainHits:                 ShowDomainHits                 = ShowDomainHits(),
) : DeltaBlast, BlastQueryWithListsImpl(
  BlastTool.DeltaBlast,
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
  giListFile,
  negativeGIListFile,
  seqIDListFile,
  negativeSeqIDListFile,
  taxIDListFile,
  negativeTaxIDListFile,
  taxIds,
  negativeTaxIds
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
    ParseWordSizeDelta(js),
    ParseGapOpen(js),
    ParseGapExtend(js),
    ParseMatrixDelta(js),
    ParseThreshold(js),
    ParseCompBasedStatsDelta(js),
    ParseSubjectFile(js),
    ParseSubjectLocation(js),
    ParseSegDelta(js),
    ParseSoftMaskingDelta(js),
    ParseQueryCoverageHSPPercent(js),
    ParseCullingLimit(js),
    ParseBestHitOverhang(js),
    ParseBestHitScoreEdge(js),
    ParseSubjectBestHit(js),
    ParseSumStats(js),
    ParseXDropGap(js),
    ParseXDropGapFinal(js),
    ParseGapTrigger(js),
    ParseNumCPUCores(js),
    ParseUseSWTBack(js),
    ParseNumIterations(js),
    ParseOutPSSMFile(js),
    ParseOutASCIIPSSMFile(js),
    ParseSavePSSMAfterLastRound(js),
    ParseSaveEachPSSM(js),
    ParsePseudoCount(js),
    ParseDomainInclusionEValueThreshold(js),
    ParseInclusionEValueThreshold(js),
    ParseRPSDB(js),
    ParseShowDomainHits(js)
  )

  override fun wordSize(value: UInt) { wordSize = WordSizeDelta(value)  }
  override fun gapOpen(value: Int) { gapOpen = GapOpen(value)  }
  override fun gapExtend(value: Int) { gapExtend = GapExtend(value)  }
  override fun matrix(value: ScoringMatrixDeltaType) { matrix = ScoringMatrixDelta(value)  }
  override fun threshold(value: Double) { threshold = Threshold(value)  }
  override fun compBasedStats(value: CompBasedStatsDeltaValue) { compBasedStats = CompBasedStatsDelta(value)  }
  override fun subjectFile(value: String) { subjectFile = SubjectFile(value)  }
  override fun subjectLocation(start: UInt, stop: UInt) { subjectLocation = SubjectLocation(start, stop) }
  override fun softMasking(value: Boolean) { softMasking = SoftMaskingDelta(value)  }
  override fun queryCoverageHSPPercent(value: Double) { queryCoverageHSPPercent = QueryCoverageHSPPercent(value)  }
  override fun cullingLimit(value: UInt) { cullingLimit = CullingLimit(value)  }
  override fun bestHitOverhang(value: Double) { bestHitOverhang = BestHitOverhang(value)  }
  override fun bestHitScoreEdge(value: Double) { bestHitScoreEdge = BestHitScoreEdge(value)  }
  override fun subjectBestHit(value: Boolean) { subjectBestHit = SubjectBestHit(value)  }
  override fun sumStats(value: Boolean) { sumStats = SumStats(value)  }
  override fun extensionDropoffPrelimGapped(value: Double) { extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(value)  }
  override fun extensionDropoffFinalGapped(value: Double) { extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(value)  }
  override fun gapTrigger(value: Double) { gapTrigger = GapTrigger(value)  }
  override fun numCPUCores(value: UByte) { numCPUCores = NumCPUCores(value)  }
  override fun useSmithWatermanTraceback(value: Boolean) { useSmithWatermanTraceback = UseSmithWatermanTraceback(value)  }
  override fun numIterations(value: UByte) { numIterations = NumIterations(value)  }
  override fun outPSSMFile(value: String) { outPSSMFile = OutPSSMFile(value)  }
  override fun outASCIIPSSMFile(value: String) { outASCIIPSSMFile = OutASCIIPSSMFile(value)  }
  override fun savePSSMAfterLastRound(value: Boolean) { savePSSMAfterLastRound = SavePSSMAfterLastRound(value)  }
  override fun saveEachPSSM(value: Boolean) { saveEachPSSM = SaveEachPSSM(value)  }
  override fun pseudoCount(value: Int) { pseudoCount = PseudoCount(value)  }
  override fun domainInclusionEValueThreshold(value: Double) { domainInclusionEValueThreshold = DomainInclusionEValueThreshold(value)  }
  override fun inclusionEValueThreshold(value: Double) { inclusionEValueThreshold = InclusionEValueThreshold(value)  }
  override fun rpsDB(value: String) { rpsDB = RPSDB(value)  }
  override fun showDomainHits(value: Boolean) { showDomainHits = ShowDomainHits(value)  }

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    wordSize.appendJson(js)
    gapOpen.appendJson(js)
    gapExtend.appendJson(js)
    matrix.appendJson(js)
    threshold.appendJson(js)
    compBasedStats.appendJson(js)
    subjectFile.appendJson(js)
    subjectLocation.appendJson(js)
    seg.appendJson(js)
    softMasking.appendJson(js)
    queryCoverageHSPPercent.appendJson(js)
    cullingLimit.appendJson(js)
    bestHitOverhang.appendJson(js)
    bestHitScoreEdge.appendJson(js)
    subjectBestHit.appendJson(js)
    sumStats.appendJson(js)
    extensionDropoffPrelimGapped.appendJson(js)
    extensionDropoffFinalGapped.appendJson(js)
    gapTrigger.appendJson(js)
    numCPUCores.appendJson(js)
    useSmithWatermanTraceback.appendJson(js)
    numIterations.appendJson(js)
    outPSSMFile.appendJson(js)
    outASCIIPSSMFile.appendJson(js)
    savePSSMAfterLastRound.appendJson(js)
    saveEachPSSM.appendJson(js)
    pseudoCount.appendJson(js)
    domainInclusionEValueThreshold.appendJson(js)
    inclusionEValueThreshold.appendJson(js)
    rpsDB.appendJson(js)
    showDomainHits.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    wordSize.appendCliSegment(sb)
    gapOpen.appendCliSegment(sb)
    gapExtend.appendCliSegment(sb)
    matrix.appendCliSegment(sb)
    threshold.appendCliSegment(sb)
    compBasedStats.appendCliSegment(sb)
    subjectFile.appendCliSegment(sb)
    subjectLocation.appendCliSegment(sb)
    seg.appendCliSegment(sb)
    softMasking.appendCliSegment(sb)
    queryCoverageHSPPercent.appendCliSegment(sb)
    cullingLimit.appendCliSegment(sb)
    bestHitOverhang.appendCliSegment(sb)
    bestHitScoreEdge.appendCliSegment(sb)
    subjectBestHit.appendCliSegment(sb)
    sumStats.appendCliSegment(sb)
    extensionDropoffPrelimGapped.appendCliSegment(sb)
    extensionDropoffFinalGapped.appendCliSegment(sb)
    gapTrigger.appendCliSegment(sb)
    numCPUCores.appendCliSegment(sb)
    useSmithWatermanTraceback.appendCliSegment(sb)
    numIterations.appendCliSegment(sb)
    outPSSMFile.appendCliSegment(sb)
    outASCIIPSSMFile.appendCliSegment(sb)
    savePSSMAfterLastRound.appendCliSegment(sb)
    saveEachPSSM.appendCliSegment(sb)
    pseudoCount.appendCliSegment(sb)
    domainInclusionEValueThreshold.appendCliSegment(sb)
    inclusionEValueThreshold.appendCliSegment(sb)
    rpsDB.appendCliSegment(sb)
    showDomainHits.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    wordSize.appendCliParts(cli)
    gapOpen.appendCliParts(cli)
    gapExtend.appendCliParts(cli)
    matrix.appendCliParts(cli)
    threshold.appendCliParts(cli)
    compBasedStats.appendCliParts(cli)
    subjectFile.appendCliParts(cli)
    subjectLocation.appendCliParts(cli)
    seg.appendCliParts(cli)
    softMasking.appendCliParts(cli)
    queryCoverageHSPPercent.appendCliParts(cli)
    cullingLimit.appendCliParts(cli)
    bestHitOverhang.appendCliParts(cli)
    bestHitScoreEdge.appendCliParts(cli)
    subjectBestHit.appendCliParts(cli)
    sumStats.appendCliParts(cli)
    extensionDropoffPrelimGapped.appendCliParts(cli)
    extensionDropoffFinalGapped.appendCliParts(cli)
    gapTrigger.appendCliParts(cli)
    numCPUCores.appendCliParts(cli)
    useSmithWatermanTraceback.appendCliParts(cli)
    numIterations.appendCliParts(cli)
    outPSSMFile.appendCliParts(cli)
    outASCIIPSSMFile.appendCliParts(cli)
    savePSSMAfterLastRound.appendCliParts(cli)
    saveEachPSSM.appendCliParts(cli)
    pseudoCount.appendCliParts(cli)
    domainInclusionEValueThreshold.appendCliParts(cli)
    inclusionEValueThreshold.appendCliParts(cli)
    rpsDB.appendCliParts(cli)
    showDomainHits.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    super.validate(errs)

    errs.incompatible(subjectFile, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, showDomainHits)
    errs.incompatible(subjectLocation, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, remote)
    errs.incompatible(cullingLimit, bestHitOverhang, bestHitScoreEdge)
    errs.incompatible(numCPUCores, remote)
    errs.incompatible(numIterations, remote)
    errs.incompatible(showDomainHits, remote, subjectFile)
  }

  override fun clone(): DeltaBlast {
    val out = DeltaBlastImpl()

    super.copyInto(out)

    out.wordSize                       = wordSize.clone()
    out.gapOpen                        = gapOpen.clone()
    out.gapExtend                      = gapExtend.clone()
    out.matrix                         = matrix.clone()
    out.threshold                      = threshold.clone()
    out.compBasedStats                 = compBasedStats.clone()
    out.subjectFile                    = subjectFile.clone()
    out.subjectLocation                = subjectLocation.clone()
    out.seg                            = seg.clone()
    out.softMasking                    = softMasking.clone()
    out.queryCoverageHSPPercent        = queryCoverageHSPPercent.clone()
    out.cullingLimit                   = cullingLimit.clone()
    out.bestHitOverhang                = bestHitOverhang.clone()
    out.bestHitScoreEdge               = bestHitScoreEdge.clone()
    out.subjectBestHit                 = subjectBestHit.clone()
    out.sumStats                       = sumStats.clone()
    out.extensionDropoffPrelimGapped   = extensionDropoffPrelimGapped.clone()
    out.extensionDropoffFinalGapped    = extensionDropoffFinalGapped.clone()
    out.gapTrigger                     = gapTrigger.clone()
    out.numCPUCores                    = numCPUCores.clone()
    out.useSmithWatermanTraceback      = useSmithWatermanTraceback.clone()
    out.numIterations                  = numIterations.clone()
    out.outPSSMFile                    = outPSSMFile.clone()
    out.outASCIIPSSMFile               = outASCIIPSSMFile.clone()
    out.savePSSMAfterLastRound         = savePSSMAfterLastRound.clone()
    out.saveEachPSSM                   = saveEachPSSM.clone()
    out.pseudoCount                    = pseudoCount.clone()
    out.domainInclusionEValueThreshold = domainInclusionEValueThreshold.clone()
    out.inclusionEValueThreshold       = inclusionEValueThreshold.clone()
    out.rpsDB                          = rpsDB.clone()
    out.showDomainHits                 = showDomainHits.clone()

    return out
  }
}
