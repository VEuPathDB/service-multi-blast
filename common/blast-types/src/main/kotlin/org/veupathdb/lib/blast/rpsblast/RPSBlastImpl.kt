package org.veupathdb.lib.blast.rpsblast

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.BlastQueryBaseImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap
import org.veupathdb.lib.blast.rpsblast.fields.*

internal class RPSBlastImpl(
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

  override var compBasedStats:               CompBasedStatsRPS            = CompBasedStatsRPS(),
  override var seg:                          SegRPS                       = NoSegRPS,
  override var softMasking:                  SoftMaskingRPS               = SoftMaskingRPS(),
  override var queryCoverageHSPPercent:      QueryCoverageHSPPercent      = QueryCoverageHSPPercent(),
  override var cullingLimit:                 CullingLimit                 = CullingLimit(),
  override var bestHitOverhang:              BestHitOverhang              = BestHitOverhang(),
  override var bestHitScoreEdge:             BestHitScoreEdge             = BestHitScoreEdge(),
  override var subjectBestHit:               SubjectBestHit               = SubjectBestHit(),
  override var sumStats:                     SumStats                     = SumStats(),
  override var extensionDropoffPrelimGapped: ExtensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(),
  override var extensionDropoffFinalGapped:  ExtensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(),
  override var autoCPUCores:                 AutoCPUCores                 = AutoCPUCores(),
  override var multiThreadingMode:           MultiThreadingMode           = MultiThreadingMode(),
  override var useSmithWatermanTraceback:    UseSmithWatermanTraceback    = UseSmithWatermanTraceback(),
) : RPSBlast, BlastQueryBaseImpl(
  BlastTool.RPSBlast,
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
    ParseCompBasedStatsRPS(js),
    ParseSegRPS(js),
    ParseSoftMaskingRPS(js),
    ParseQueryCoverageHSPPercent(js),
    ParseCullingLimit(js),
    ParseBestHitOverhang(js),
    ParseBestHitScoreEdge(js),
    ParseSubjectBestHit(js),
    ParseSumStats(js),
    ParseXDropGap(js),
    ParseXDropGapFinal(js),
    ParseAutoCPUCores(js),
    ParseMultiThreadingMode(js),
    ParseUseSWTBack(js),
  )

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    compBasedStats.appendJson(js)
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
    autoCPUCores.appendJson(js)
    multiThreadingMode.appendJson(js)
    useSmithWatermanTraceback.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    compBasedStats.appendCliSegment(sb)
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
    autoCPUCores.appendCliSegment(sb)
    multiThreadingMode.appendCliSegment(sb)
    useSmithWatermanTraceback.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    compBasedStats.appendCliParts(cli)
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
    autoCPUCores.appendCliParts(cli)
    multiThreadingMode.appendCliParts(cli)
    useSmithWatermanTraceback.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    super.validate(errs)

    errs.incompatible(cullingLimit, bestHitOverhang, bestHitScoreEdge)
  }
}