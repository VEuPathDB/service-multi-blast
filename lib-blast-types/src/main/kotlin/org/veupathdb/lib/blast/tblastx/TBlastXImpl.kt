package org.veupathdb.lib.blast.tblastx

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.BlastQueryWithListsImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap
import org.veupathdb.lib.blast.tblastx.fields.*

internal class TBlastXImpl(
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
  taxIDs:                   TaxIDs                   = TaxIDs(),
  negativeTaxIDs:           NegativeTaxIDs           = NegativeTaxIDs(),

  override var strand:                  Strand                  = Strand(),
  override var queryGenCode:            QueryGenCode            = QueryGenCode(),
  override var wordSize:                WordSizeTX              = WordSizeTX(),
  override var maxIntronLength:         MaxIntronLength         = MaxIntronLength(),
  override var matrix:                  ScoringMatrixTX         = ScoringMatrixTX(),
  override var threshold:               Threshold               = Threshold(),
  override var dbGenCode:               DBGenCode               = DBGenCode(),
  override var subjectFile:             SubjectFile             = SubjectFile(),
  override var subjectLocation:         SubjectLocation         = SubjectLocation(),
  override var seg:                     SegTX                   = SegTX.of(),
  override var softMasking:             SoftMaskingTX           = SoftMaskingTX(),
  override var dbSoftMask:              DBSoftMask              = DBSoftMask(),
  override var dbHardMask:              DBHardMask              = DBHardMask(),
  override var queryCoverageHSPPercent: QueryCoverageHSPPercent = QueryCoverageHSPPercent(),
  override var cullingLimit:            CullingLimit            = CullingLimit(),
  override var bestHitOverhang:         BestHitOverhang         = BestHitOverhang(),
  override var bestHitScoreEdge:        BestHitScoreEdge        = BestHitScoreEdge(),
  override var subjectBestHit:          SubjectBestHit          = SubjectBestHit(),
  override var sumStats:                SumStats                = SumStats(),
  override var numCPUCores:             NumCPUCores             = NumCPUCores(),
) : TBlastX, BlastQueryWithListsImpl(
  BlastTool.TBlastX,
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
  taxIDs,
  negativeTaxIDs,
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
    ParseQueryGenCode(js),
    ParseWordSizeTX(js),
    ParseMaxIntronLength(js),
    ParseScoringMatrixTX(js),
    ParseThreshold(js),
    ParseDBGenCode(js),
    ParseSubjectFile(js),
    ParseSubjectLocation(js),
    ParseSegTX(js),
    ParseSoftMaskingTX(js),
    ParseDBSoftMask(js),
    ParseDBHardMask(js),
    ParseQueryCoverageHSPPercent(js),
    ParseCullingLimit(js),
    ParseBestHitOverhang(js),
    ParseBestHitScoreEdge(js),
    ParseSubjectBestHit(js),
    ParseSumStats(js),
    ParseNumCPUCores(js),
  )

  override fun strand(value: StrandType) { strand = Strand(value) }
  override fun queryGenCode(value: UByte) { queryGenCode = QueryGenCode(value) }
  override fun wordSize(value: UInt) { wordSize = WordSizeTX(value) }
  override fun maxIntronLength(value: UInt) { maxIntronLength = MaxIntronLength(value) }
  override fun matrix(value: ScoringMatrixTXType) { matrix = ScoringMatrixTX(value) }
  override fun threshold(value: Double) { threshold = Threshold(value) }
  override fun dbGenCode(value: UByte) { dbGenCode = DBGenCode(value) }
  override fun subjectFile(value: String) { subjectFile = SubjectFile(value) }
  override fun subjectLocation(start: UInt, stop: UInt) { subjectLocation = SubjectLocation(start, stop) }
  override fun softMasking(value: Boolean) { softMasking = SoftMaskingTX(value) }
  override fun dbSoftMask(value: String) { dbSoftMask = DBSoftMask(value) }
  override fun dbHardMask(value: String) { dbHardMask = DBHardMask(value) }
  override fun queryCoverageHSPPercent(value: Double) { queryCoverageHSPPercent = QueryCoverageHSPPercent(value) }
  override fun cullingLimit(value: UInt) { cullingLimit = CullingLimit(value) }
  override fun bestHitOverhang(value: Double) { bestHitOverhang = BestHitOverhang(value) }
  override fun bestHitScoreEdge(value: Double) { bestHitScoreEdge = BestHitScoreEdge(value) }
  override fun subjectBestHit(value: Boolean) { subjectBestHit = SubjectBestHit(value) }
  override fun sumStats(value: Boolean) { sumStats = SumStats(value) }
  override fun numCPUCores(value: UByte) { numCPUCores = NumCPUCores(value) }

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    strand.appendJson(js)
    queryGenCode.appendJson(js)
    wordSize.appendJson(js)
    maxIntronLength.appendJson(js)
    matrix.appendJson(js)
    threshold.appendJson(js)
    dbGenCode.appendJson(js)
    subjectFile.appendJson(js)
    subjectLocation.appendJson(js)
    seg.appendJson(js)
    softMasking.appendJson(js)
    dbSoftMask.appendJson(js)
    dbHardMask.appendJson(js)
    queryCoverageHSPPercent.appendJson(js)
    cullingLimit.appendJson(js)
    bestHitOverhang.appendJson(js)
    bestHitScoreEdge.appendJson(js)
    subjectBestHit.appendJson(js)
    sumStats.appendJson(js)
    numCPUCores.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    strand.appendCliSegment(sb)
    queryGenCode.appendCliSegment(sb)
    wordSize.appendCliSegment(sb)
    maxIntronLength.appendCliSegment(sb)
    matrix.appendCliSegment(sb)
    threshold.appendCliSegment(sb)
    dbGenCode.appendCliSegment(sb)
    subjectFile.appendCliSegment(sb)
    subjectLocation.appendCliSegment(sb)
    seg.appendCliSegment(sb)
    softMasking.appendCliSegment(sb)
    dbSoftMask.appendCliSegment(sb)
    dbHardMask.appendCliSegment(sb)
    queryCoverageHSPPercent.appendCliSegment(sb)
    cullingLimit.appendCliSegment(sb)
    bestHitOverhang.appendCliSegment(sb)
    bestHitScoreEdge.appendCliSegment(sb)
    subjectBestHit.appendCliSegment(sb)
    sumStats.appendCliSegment(sb)
    numCPUCores.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    strand.appendCliParts(cli)
    queryGenCode.appendCliParts(cli)
    wordSize.appendCliParts(cli)
    maxIntronLength.appendCliParts(cli)
    matrix.appendCliParts(cli)
    threshold.appendCliParts(cli)
    dbGenCode.appendCliParts(cli)
    subjectFile.appendCliParts(cli)
    subjectLocation.appendCliParts(cli)
    seg.appendCliParts(cli)
    softMasking.appendCliParts(cli)
    dbSoftMask.appendCliParts(cli)
    dbHardMask.appendCliParts(cli)
    queryCoverageHSPPercent.appendCliParts(cli)
    cullingLimit.appendCliParts(cli)
    bestHitOverhang.appendCliParts(cli)
    bestHitScoreEdge.appendCliParts(cli)
    subjectBestHit.appendCliParts(cli)
    sumStats.appendCliParts(cli)
    numCPUCores.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    super.validate(errs)

    errs.incompatible(subjectFile, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, dbSoftMask, dbHardMask)
    errs.incompatible(subjectLocation, dbFile, giListFile, seqIDListFile,
      negativeGIListFile, negativeSeqIDListFile, taxIDs, taxIDListFile,
      negativeTaxIDs, negativeTaxIDListFile, dbSoftMask, dbHardMask, remote)
    errs.incompatible(dbSoftMask, dbHardMask)
    errs.incompatible(cullingLimit, bestHitOverhang, bestHitScoreEdge)
    errs.incompatible(numCPUCores, remote)
    errs.incompatible(remote, giListFile, seqIDListFile, taxIDs, taxIDListFile,
      negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs,
      negativeTaxIDListFile)
  }

  override fun clone(): TBlastX {
    val out = TBlastXImpl()

    super.copyInto(out)

    out.strand                  = strand.clone()
    out.queryGenCode            = queryGenCode.clone()
    out.wordSize                = wordSize.clone()
    out.maxIntronLength         = maxIntronLength.clone()
    out.matrix                  = matrix.clone()
    out.threshold               = threshold.clone()
    out.dbGenCode               = dbGenCode.clone()
    out.subjectFile             = subjectFile.clone()
    out.subjectLocation         = subjectLocation.clone()
    out.seg                     = seg.clone()
    out.softMasking             = softMasking.clone()
    out.dbSoftMask              = dbSoftMask.clone()
    out.dbHardMask              = dbHardMask.clone()
    out.queryCoverageHSPPercent = queryCoverageHSPPercent.clone()
    out.cullingLimit            = cullingLimit.clone()
    out.bestHitOverhang         = bestHitOverhang.clone()
    out.bestHitScoreEdge        = bestHitScoreEdge.clone()
    out.subjectBestHit          = subjectBestHit.clone()
    out.sumStats                = sumStats.clone()
    out.numCPUCores             = numCPUCores.clone()

    return out
  }
}