package org.veupathdb.lib.blast.rpstblastn

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.BlastQueryBaseImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap
import org.veupathdb.lib.blast.rpstblastn.fields.*

internal class RPSTBlastNImpl(
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

  override var queryGenCode:                 QueryGenCode                 = QueryGenCode(),
  override var strand:                       Strand                       = Strand(),
  override var compBasedStats:               CompBasedStatsRPSTN          = CompBasedStatsRPSTN(),
  override var seg:                          SegRPSTN                     = NoSegRPSTN,
  override var softMasking:                  SoftMaskingRPSTN             = SoftMaskingRPSTN(),
  override var queryCoverageHSPPercent:      QueryCoverageHSPPercent      = QueryCoverageHSPPercent(),
  override var sumStats:                     SumStats                     = SumStats(),
  override var extensionDropoffPrelimGapped: ExtensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(),
  override var extensionDropoffFinalGapped:  ExtensionDropoffFinalGapped  = ExtensionDropoffFinalGapped(),
  override var ungappedAlignmentsOnly:       UngappedAlignmentsOnly       = UngappedAlignmentsOnly(),
  override var autoCPUCores:                 AutoCPUCores                 = AutoCPUCores(),
  override var multiThreadingMode:           MultiThreadingMode           = MultiThreadingMode(),
  override var useSmithWatermanTraceback:    UseSmithWatermanTraceback    = UseSmithWatermanTraceback(),
) : RPSTBlastN, BlastQueryBaseImpl(
  BlastTool.RPSTBlastN,
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
    ParseQueryGenCode(js),
    ParseStrand(js),
    ParseCompBasedStatsRPSTN(js),
    ParseSegRPSTN(js),
    ParseSoftMaskingRPSTN(js),
    ParseQueryCoverageHSPPercent(js),
    ParseSumStats(js),
    ParseXDropGap(js),
    ParseXDropGapFinal(js),
    ParseUngappedAlignmentsOnly(js),
    ParseAutoCPUCores(js),
    ParseMultiThreadingMode(js),
    ParseUseSWTBack(js)
  )

  override fun queryGenCode(value: UByte) { queryGenCode = QueryGenCode(value) }
  override fun strand(value: StrandType) { strand = Strand(value) }
  override fun compBasedStats(value: CompBasedStatsRPSTNValue) { compBasedStats = CompBasedStatsRPSTN(value) }
  override fun softMasking(value: Boolean) { softMasking = SoftMaskingRPSTN(value) }
  override fun queryCoverageHSPPercent(value: Double) { queryCoverageHSPPercent = QueryCoverageHSPPercent(value) }
  override fun sumStats(value: Boolean) { sumStats = SumStats(value) }
  override fun extensionDropoffPrelimGapped(value: Double) { extensionDropoffPrelimGapped = ExtensionDropoffPrelimGapped(value) }
  override fun extensionDropoffFinalGapped(value: Double) { extensionDropoffFinalGapped = ExtensionDropoffFinalGapped(value) }
  override fun ungappedAlignmentsOnly(value: Boolean) { ungappedAlignmentsOnly = UngappedAlignmentsOnly(value) }
  override fun autoCPUCores(value: AutoCPUCoresValue) { autoCPUCores = AutoCPUCores(value) }
  override fun multiThreadingMode(value: MultiThreadingModeValue) { multiThreadingMode = MultiThreadingMode(value) }
  override fun useSmithWatermanTraceback(value: Boolean) { useSmithWatermanTraceback = UseSmithWatermanTraceback(value) }

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    queryGenCode.appendJson(js)
    strand.appendJson(js)
    compBasedStats.appendJson(js)
    seg.appendJson(js)
    softMasking.appendJson(js)
    queryCoverageHSPPercent.appendJson(js)
    sumStats.appendJson(js)
    extensionDropoffPrelimGapped.appendJson(js)
    extensionDropoffFinalGapped.appendJson(js)
    ungappedAlignmentsOnly.appendJson(js)
    autoCPUCores.appendJson(js)
    multiThreadingMode.appendJson(js)
    useSmithWatermanTraceback.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    queryGenCode.appendCliSegment(sb)
    strand.appendCliSegment(sb)
    compBasedStats.appendCliSegment(sb)
    seg.appendCliSegment(sb)
    softMasking.appendCliSegment(sb)
    queryCoverageHSPPercent.appendCliSegment(sb)
    sumStats.appendCliSegment(sb)
    extensionDropoffPrelimGapped.appendCliSegment(sb)
    extensionDropoffFinalGapped.appendCliSegment(sb)
    ungappedAlignmentsOnly.appendCliSegment(sb)
    autoCPUCores.appendCliSegment(sb)
    multiThreadingMode.appendCliSegment(sb)
    useSmithWatermanTraceback.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    queryGenCode.appendCliParts(cli)
    strand.appendCliParts(cli)
    compBasedStats.appendCliParts(cli)
    seg.appendCliParts(cli)
    softMasking.appendCliParts(cli)
    queryCoverageHSPPercent.appendCliParts(cli)
    sumStats.appendCliParts(cli)
    extensionDropoffPrelimGapped.appendCliParts(cli)
    extensionDropoffFinalGapped.appendCliParts(cli)
    ungappedAlignmentsOnly.appendCliParts(cli)
    autoCPUCores.appendCliParts(cli)
    multiThreadingMode.appendCliParts(cli)
    useSmithWatermanTraceback.appendCliParts(cli)
  }
}