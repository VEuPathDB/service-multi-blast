package org.veupathdb.lib.blast.formatter

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.BlastCLIImpl
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap
import org.veupathdb.lib.blast.formatter.fields.Archive
import org.veupathdb.lib.blast.formatter.fields.ParseArchive
import org.veupathdb.lib.blast.formatter.fields.ParseRID
import org.veupathdb.lib.blast.formatter.fields.RID

internal class BlastFormatterImpl(
  shortHelp:       HelpShort       = HelpShort(),
  longHelp:        HelpLong        = HelpLong(),
  version:         Version         = Version(),
  outFile:         OutFile         = OutFile(),
  outFormat:       OutFormat       = OutFormat(),
  showGIs:         ShowGIs         = ShowGIs(),
  numDescriptions: NumDescriptions = NumDescriptions(),
  numAlignments:   NumAlignments   = NumAlignments(),
  lineLength:      LineLength      = LineLength(),
  html:            HTML            = HTML(),
  sortHits:        SortHits        = SortHits(),
  sortHSPs:        SortHSPs        = SortHSPs(),
  maxTargetSeqs:   MaxTargetSeqs   = MaxTargetSeqs(),
  parseDefLines:   ParseDefLines   = ParseDefLines(),

  override var rid:     RID     = RID(),
  override var archive: Archive = Archive(),
) : BlastFormatter, BlastCLIImpl(
  BlastTool.BlastFormatter,
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
    ParseRID(js),
    ParseArchive(js),
  )

  override fun appendJson(js: ObjectNode) {
    rid.appendJson(js)
    archive.appendJson(js)
  }

  override fun appendCli(sb: StringBuilder) {
    rid.appendCliSegment(sb)
    archive.appendCliSegment(sb)
  }

  override fun appendCli(cli: MutableList<String>) {
    rid.appendCliParts(cli)
    archive.appendCliParts(cli)
  }

  override fun validate(errs: ErrorMap) {
    errs.incompatible(archive, rid)
  }
}