package org.veupathdb.lib.blast.common

import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.serial.BlastCommand

/**
 * Fields common to all BLAST+ tools.
 */

interface BlastCLI : BlastCommand {

  val tool: BlastTool

  /**
   * -h
   */
  var shortHelp: HelpShort
  fun shortHelp(value: Boolean)

  /**
   * -help
   */
  var longHelp: HelpLong
  fun longHelp(value: Boolean)

  /**
   * -version
   */
  var version: Version
  fun version(value: Boolean)

  /**
   * -out `<File_Out>`
   */
  var outFile: OutFile
  fun outFile(value: String)

  /**
   * -outfmt `<String>`
   */
  var outFormat: OutFormat

  /**
   * -show_gis
   */
  var showGIs: ShowGIs
  fun showGIs(value: Boolean)

  /**
   * -num_descriptions `<Integer>`
   */
  var numDescriptions: NumDescriptions
  fun numDescriptions(value: UInt)

  /**
   * -num_alignments `<Integer>`
   */
  var numAlignments: NumAlignments
  fun numAlignments(value: UInt)

  /**
   * -line_length `<Integer>`
   */
  var lineLength: LineLength
  fun lineLength(value: UInt)

  /**
   * -html
   */
  var html: HTML
  fun html(value: Boolean)

  /**
   * -sorthits `<Integer>`
   */
  var sortHits: SortHits
  fun sortHits(value: HitSorting)

  /**
   * -sorthsps `<Integer>`
   */
  var sortHSPs: SortHSPs
  fun sortHSPs(value: HSPSorting)

  /**
   * -max_target_seqs `<Integer>`
   */
  var maxTargetSeqs: MaxTargetSeqs
  fun maxTargetSeqs(value: UInt)

  /**
   * -parse_deflines
   */
  var parseDefLines: ParseDefLines
  fun parseDefLines(value: Boolean)

  /**
   * Clones this `BlastCLI` instance and all of it's configured options.
   *
   * @since v8.2.0
   */
  override fun clone(): BlastCLI
}
