package org.veupathdb.lib.blast.common

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap

internal abstract class BlastQueryWithListsImpl(
  tool: BlastTool,
  shortHelp: HelpShort,
  longHelp: HelpLong,
  version: Version,
  outFile: OutFile,
  outFormat: OutFormat,
  showGIs: ShowGIs,
  numDescriptions: NumDescriptions,
  numAlignments: NumAlignments,
  lineLength: LineLength,
  html: HTML,
  sortHits: SortHits,
  sortHSPs: SortHSPs,
  maxTargetSeqs: MaxTargetSeqs,
  parseDefLines: ParseDefLines,
  queryFile: QueryFile,
  queryLocation: QueryLocation,
  dbFile: DBFiles,
  expectValue: ExpectValue,
  lowercaseMasking: LowercaseMasking,
  entrezQuery: EntrezQuery,
  maxHSPs: MaxHSPs,
  dbSize: DBSize,
  searchSpace: SearchSpace,
  importSearchStrategy: ImportSearchStrategy,
  exportSearchStrategy: ExportSearchStrategy,
  extensionDropoffUngapped: ExtensionDropoffUngapped,
  windowSize: WindowSize,
  remote: Remote,

  override var giListFile: GIList,
  override var negativeGIListFile: NegativeGIList,
  override var seqIDListFile: SeqIDList,
  override var negativeSeqIDListFile: NegativeSeqIDList,
  override var taxIDListFile: TaxIDList,
  override var negativeTaxIDListFile: NegativeTaxIDList,
  override var taxIDs: TaxIDs,
  override var negativeTaxIDs: NegativeTaxIDs,
) : BlastQueryWithLists, BlastQueryBaseImpl(
  tool,
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

  override fun giListFile(value: String) { giListFile = GIList(value) }
  override fun negativeGIListFile(value: String) { negativeGIListFile = NegativeGIList(value) }
  override fun seqIDListFile(value: String) { seqIDListFile = SeqIDList(value) }
  override fun negativeSeqIDListFile(value: String) { negativeSeqIDListFile = NegativeSeqIDList(value) }
  override fun taxIDListFile(value: String) { taxIDListFile = TaxIDList(value) }
  override fun negativeTaxIDListFile(value: String) { negativeTaxIDListFile = NegativeTaxIDList(value) }
  override fun taxIDs(value: Iterable<String>) { taxIDs = TaxIDs(value.toList()) }
  override fun taxIDs(vararg values: String) { taxIDs = TaxIDs(values.asList()) }
  override fun negativeTaxIDs(value: List<String>) { negativeTaxIDs = NegativeTaxIDs(value.toList()) }
  override fun negativeTaxIDs(vararg values: String) { negativeTaxIDs = NegativeTaxIDs(values.asList()) }

  override fun appendJson(js: ObjectNode) {
    super.appendJson(js)

    giListFile.appendJson(js)
    negativeGIListFile.appendJson(js)
    seqIDListFile.appendJson(js)
    negativeSeqIDListFile.appendJson(js)
    taxIDListFile.appendJson(js)
    negativeTaxIDListFile.appendJson(js)
    taxIDs.appendJson(js)
    negativeTaxIDs.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    super.appendCli(sb)

    giListFile.appendCliSegment(sb)
    negativeGIListFile.appendCliSegment(sb)
    seqIDListFile.appendCliSegment(sb)
    negativeSeqIDListFile.appendCliSegment(sb)
    taxIDListFile.appendCliSegment(sb)
    negativeTaxIDListFile.appendCliSegment(sb)
    taxIDs.appendCliSegment(sb)
    negativeTaxIDs.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    super.appendCli(cli)

    giListFile.appendCliParts(cli)
    negativeGIListFile.appendCliParts(cli)
    seqIDListFile.appendCliParts(cli)
    negativeSeqIDListFile.appendCliParts(cli)
    taxIDListFile.appendCliParts(cli)
    negativeTaxIDListFile.appendCliParts(cli)
    taxIDs.appendCliParts(cli)
    negativeTaxIDs.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    super.validate(errs)

    errs.incompatible(giListFile, seqIDListFile, taxIDs, taxIDListFile, negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(seqIDListFile, taxIDs, taxIDListFile, negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(taxIDs, taxIDListFile, negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(taxIDListFile, negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(negativeGIListFile, negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(negativeSeqIDListFile, negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(negativeTaxIDs, negativeSeqIDListFile, remote)
    errs.incompatible(negativeSeqIDListFile, remote)
  }
}
