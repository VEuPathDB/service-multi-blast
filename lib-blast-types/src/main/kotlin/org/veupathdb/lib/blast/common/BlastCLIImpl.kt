package org.veupathdb.lib.blast.common

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.err.ErrorMap
import org.veupathdb.lib.jackson.Json

internal abstract class BlastCLIImpl(
  override val tool:            BlastTool,
  override var shortHelp:       HelpShort,
  override var longHelp:        HelpLong,
  override var version:         Version,
  override var outFile:         OutFile,
  override var outFormat:       OutFormat,
  override var showGIs:         ShowGIs,
  override var numDescriptions: NumDescriptions,
  override var numAlignments:   NumAlignments,
  override var lineLength:      LineLength,
  override var html:            HTML,
  override var sortHits:        SortHits,
  override var sortHSPs:        SortHSPs,
  override var maxTargetSeqs:   MaxTargetSeqs,
  override var parseDefLines:   ParseDefLines
) :  BlastCLI {

  override fun shortHelp(value: Boolean) { shortHelp = HelpShort(value) }
  override fun longHelp(value: Boolean) { longHelp = HelpLong(value) }
  override fun version(value: Boolean) { version = Version(value) }
  override fun outFile(value: String) { outFile = OutFile(value) }
  override fun showGIs(value: Boolean) { showGIs = ShowGIs(value) }
  override fun numDescriptions(value: UInt) { numDescriptions = NumDescriptions(value) }
  override fun numAlignments(value: UInt) { numAlignments = NumAlignments(value) }
  override fun lineLength(value: UInt) { lineLength = LineLength(value) }
  override fun html(value: Boolean) { html = HTML(value) }
  override fun sortHits(value: HitSorting) { sortHits = SortHits(value) }
  override fun sortHSPs(value: HSPSorting) { sortHSPs = SortHSPs(value) }
  override fun maxTargetSeqs(value: UInt) { maxTargetSeqs = MaxTargetSeqs(value) }
  override fun parseDefLines(value: Boolean) { parseDefLines = ParseDefLines(value) }

  override fun toJson() = Json.new<ObjectNode> {
    put("tool", tool.value)

    shortHelp.appendJson(this)
    longHelp.appendJson(this)
    version.appendJson(this)
    outFile.appendJson(this)
    outFormat.appendJson(this)
    showGIs.appendJson(this)
    numDescriptions.appendJson(this)
    numAlignments.appendJson(this)
    lineLength.appendJson(this)
    html.appendJson(this)
    sortHits.appendJson(this)
    sortHSPs.appendJson(this)
    maxTargetSeqs.appendJson(this)
    parseDefLines.appendJson(this)

    appendJson(this)
  }

  protected abstract fun appendJson(js: ObjectNode)

  override fun toCliString(): String {
    val out = StringBuilder(8192)

    out.append(tool.value)

    shortHelp.appendCliSegment(out)
    longHelp.appendCliSegment(out)
    version.appendCliSegment(out)
    outFile.appendCliSegment(out)
    outFormat.appendCliSegment(out)
    showGIs.appendCliSegment(out)
    numDescriptions.appendCliSegment(out)
    numAlignments.appendCliSegment(out)
    lineLength.appendCliSegment(out)
    html.appendCliSegment(out)
    sortHits.appendCliSegment(out)
    sortHSPs.appendCliSegment(out)
    maxTargetSeqs.appendCliSegment(out)
    parseDefLines.appendCliSegment(out)

    appendCli(out)

    return out.toString()
  }

  protected abstract fun appendCli(sb: StringBuilder)

  override fun toCliArray(): Array<String> {
    val out = ArrayList<String>(256)

    out.add(tool.value)

    shortHelp.appendCliParts(out)
    longHelp.appendCliParts(out)
    version.appendCliParts(out)
    outFile.appendCliParts(out)
    outFormat.appendCliParts(out)
    showGIs.appendCliParts(out)
    numDescriptions.appendCliParts(out)
    numAlignments.appendCliParts(out)
    lineLength.appendCliParts(out)
    html.appendCliParts(out)
    sortHits.appendCliParts(out)
    sortHSPs.appendCliParts(out)
    maxTargetSeqs.appendCliParts(out)
    parseDefLines.appendCliParts(out)

    appendCli(out)

    return out.toTypedArray()
  }

  protected abstract fun appendCli(cli: MutableList<String>)

  final override fun validate(): ErrorMap {
    val out = ErrorMap()

    out.incompatible(numDescriptions, maxTargetSeqs)
    out.incompatible(numAlignments, maxTargetSeqs)

    validate(out)

    return out
  }

  protected abstract fun validate(errs: ErrorMap)

  protected open fun copyInto(cli: BlastCLI) {
    cli.shortHelp       = shortHelp.clone()
    cli.longHelp        = longHelp.clone()
    cli.version         = version.clone()
    cli.outFile         = outFile.clone()
    cli.outFormat       = outFormat.clone()
    cli.showGIs         = showGIs.clone()
    cli.numDescriptions = numDescriptions.clone()
    cli.numAlignments   = numAlignments.clone()
    cli.lineLength      = lineLength.clone()
    cli.html            = html.clone()
    cli.sortHits        = sortHits.clone()
    cli.sortHSPs        = sortHSPs.clone()
    cli.maxTargetSeqs   = maxTargetSeqs.clone()
    cli.parseDefLines   = parseDefLines.clone()
  }
}
