package org.veupathdb.lib.blast.common

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap

internal abstract class BlastQueryBaseImpl(
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

  override var queryFile: QueryFile,
  override var queryLocation: QueryLocation,
  override var dbFile: DBFiles,
  override var expectValue: ExpectValue,
  override var lowercaseMasking: LowercaseMasking,
  override var entrezQuery: EntrezQuery,
  override var maxHSPs: MaxHSPs,
  override var dbSize: DBSize,
  override var searchSpace: SearchSpace,
  override var importSearchStrategy: ImportSearchStrategy,
  override var exportSearchStrategy: ExportSearchStrategy,
  override var extensionDropoffUngapped: ExtensionDropoffUngapped,
  override var windowSize: WindowSize,
  override var remote: Remote,
) : BlastQueryBase, BlastCLIImpl(
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
) {

  override fun queryFile(value: String) { queryFile = QueryFile(value) }
  override fun queryLocation(start: UInt, stop: UInt) { queryLocation = QueryLocation(start, stop) }
  override fun dbFiles(vararg files: String) { dbFile = DBFiles(*files) }
  override fun expectValue(value: String) { expectValue = ExpectValue(value) }
  override fun lowercaseMasking(value: Boolean) { lowercaseMasking = LowercaseMasking(value) }
  override fun entrezQuery(value: String) { entrezQuery = EntrezQuery(value) }
  override fun maxHSPs(value: UInt) { maxHSPs = MaxHSPs(value) }
  override fun dbSize(value: Byte) { dbSize = DBSize(value) }
  override fun searchSpace(value: Byte) { searchSpace = SearchSpace(value) }
  override fun importSearchStrategy(value: String) { importSearchStrategy = ImportSearchStrategy(value) }
  override fun exportSearchStrategy(value: String) { exportSearchStrategy = ExportSearchStrategy(value) }
  override fun extensionDropoffUngapped(value: Double) { extensionDropoffUngapped = ExtensionDropoffUngapped(value) }
  override fun windowSize(value: UInt) { windowSize = WindowSize(value) }
  override fun remote(value: Boolean) { remote = Remote(value) }

  override fun appendJson(js: ObjectNode) {
    queryFile.appendJson(js)
    queryLocation.appendJson(js)
    dbFile.appendJson(js)
    expectValue.appendJson(js)
    lowercaseMasking.appendJson(js)
    entrezQuery.appendJson(js)
    maxHSPs.appendJson(js)
    dbSize.appendJson(js)
    searchSpace.appendJson(js)
    importSearchStrategy.appendJson(js)
    exportSearchStrategy.appendJson(js)
    extensionDropoffUngapped.appendJson(js)
    windowSize.appendJson(js)
    remote.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    queryFile.appendCliSegment(sb)
    queryLocation.appendCliSegment(sb)
    dbFile.appendCliSegment(sb)
    expectValue.appendCliSegment(sb)
    lowercaseMasking.appendCliSegment(sb)
    entrezQuery.appendCliSegment(sb)
    maxHSPs.appendCliSegment(sb)
    dbSize.appendCliSegment(sb)
    searchSpace.appendCliSegment(sb)
    importSearchStrategy.appendCliSegment(sb)
    exportSearchStrategy.appendCliSegment(sb)
    extensionDropoffUngapped.appendCliSegment(sb)
    windowSize.appendCliSegment(sb)
    remote.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    queryFile.appendCliParts(cli)
    queryLocation.appendCliParts(cli)
    dbFile.appendCliParts(cli)
    expectValue.appendCliParts(cli)
    lowercaseMasking.appendCliParts(cli)
    entrezQuery.appendCliParts(cli)
    maxHSPs.appendCliParts(cli)
    dbSize.appendCliParts(cli)
    searchSpace.appendCliParts(cli)
    importSearchStrategy.appendCliParts(cli)
    exportSearchStrategy.appendCliParts(cli)
    extensionDropoffUngapped.appendCliParts(cli)
    windowSize.appendCliParts(cli)
    remote.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    errs.requires(entrezQuery, remote)
    errs.incompatible(importSearchStrategy, exportSearchStrategy)
  }

  fun copyInto(cli: BlastQueryBase) {
    super.copyInto(cli)

    cli.queryFile                = queryFile.clone()
    cli.queryLocation            = queryLocation.clone()
    cli.dbFile                   = dbFile.clone()
    cli.expectValue              = expectValue.clone()
    cli.lowercaseMasking         = lowercaseMasking.clone()
    cli.entrezQuery              = entrezQuery.clone()
    cli.maxHSPs                  = maxHSPs.clone()
    cli.dbSize                   = dbSize.clone()
    cli.searchSpace              = searchSpace.clone()
    cli.importSearchStrategy     = importSearchStrategy.clone()
    cli.exportSearchStrategy     = exportSearchStrategy.clone()
    cli.extensionDropoffUngapped = extensionDropoffUngapped.clone()
    cli.windowSize               = windowSize.clone()
    cli.remote                   = remote.clone()
  }
}
