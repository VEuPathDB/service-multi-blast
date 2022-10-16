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

  /**
   * -query_loc `<String>`
   */
  var queryLocation: QueryLocation

  /**
   * -db `<String>`
   */
  var dbFile: DBFiles

  /**
   * -evalue `<Real>`
   */
  var expectValue: ExpectValue

  /**
   * -lcase_masking
   */
  var lowercaseMasking: LowercaseMasking

  /**
   * -entrez_query `<String>`
   */
  var entrezQuery: EntrezQuery

  /**
   * -max_hsps `<Integer>`
   */
  var maxHSPs: MaxHSPs

  /**
   * -dbsize `<Int8>`
   */
  var dbSize: DBSize

  /**
   * -searchsp `<Int8>`
   */
  var searchSpace: SearchSpace

  /**
   * -import_search_strategy `<File_In>`
   */
  var importSearchStrategy: ImportSearchStrategy

  /**
   * -export_search_strategy `<File_Out>`
   */
  var exportSearchStrategy: ExportSearchStrategy

  /**
   * -xdrop_ungap `<Real>`
   */
  var extensionDropoffUngapped: ExtensionDropoffUngapped

  /**
   * -window_size `<Integer>`
   */
  var windowSize: WindowSize

  /**
   * -remote
   */
  var remote: Remote
}
