package org.veupathdb.lib.blast.common

import org.veupathdb.lib.blast.common.fields.*


/**
 * Basic fields common to all BLAST+ query tools.
 */
interface BlastQueryBase : BlastCLI {

  /**
   * -query `<File_In>`
   */
  var queryFile: QueryFile
  fun queryFile(value: String)

  /**
   * -query_loc `<String>`
   */
  var queryLocation: QueryLocation
  fun queryLocation(start: UInt, stop: UInt)

  /**
   * -db `<String>`
   */
  var dbFile: DBFiles
  fun dbFiles(vararg files: String)

  /**
   * -evalue `<Real>`
   */
  var expectValue: ExpectValue
  fun expectValue(value: String)

  /**
   * -lcase_masking
   */
  var lowercaseMasking: LowercaseMasking
  fun lowercaseMasking(value: Boolean)

  /**
   * -entrez_query `<String>`
   */
  var entrezQuery: EntrezQuery
  fun entrezQuery(value: String)

  /**
   * -max_hsps `<Integer>`
   */
  var maxHSPs: MaxHSPs
  fun maxHSPs(value: UInt)

  /**
   * -dbsize `<Int8>`
   */
  var dbSize: DBSize
  fun dbSize(value: Byte)

  /**
   * -searchsp `<Int8>`
   */
  var searchSpace: SearchSpace
  fun searchSpace(value: Byte)

  /**
   * -import_search_strategy `<File_In>`
   */
  var importSearchStrategy: ImportSearchStrategy
  fun importSearchStrategy(value: String)

  /**
   * -export_search_strategy `<File_Out>`
   */
  var exportSearchStrategy: ExportSearchStrategy
  fun exportSearchStrategy(value: String)

  /**
   * -xdrop_ungap `<Real>`
   */
  var extensionDropoffUngapped: ExtensionDropoffUngapped
  fun extensionDropoffUngapped(value: Double)

  /**
   * -window_size `<Integer>`
   */
  var windowSize: WindowSize
  fun windowSize(value: UInt)

  /**
   * -remote
   */
  var remote: Remote
  fun remote(value: Boolean)

  /**
   * Clones this `BlastQueryBase` instance and all of it's configured options.
   */
  override fun clone(): BlastQueryBase
}
