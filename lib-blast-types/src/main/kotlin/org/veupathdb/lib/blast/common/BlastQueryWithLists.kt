package org.veupathdb.lib.blast.common

import org.veupathdb.lib.blast.common.fields.*

/**
 * List fields common to most BLAST+ query tools.
 */
interface BlastQueryWithLists : BlastQueryBase {

  /**
   * -gilist `<String>`
   */
  var giListFile: GIList
  fun giListFile(value: String)

  /**
   * -negative_gilist `<String>`
   */
  var negativeGIListFile: NegativeGIList
  fun negativeGIListFile(value: String)

  /**
   * -seqidlist `<String>`
   */
  var seqIDListFile: SeqIDList
  fun seqIDListFile(value: String)

  /**
   * -negative_seqidlist `<String>`
   */
  var negativeSeqIDListFile: NegativeSeqIDList
  fun negativeSeqIDListFile(value: String)

  /**
   * -taxidlist `<String>`
   */
  var taxIDListFile: TaxIDList
  fun taxIDListFile(value: String)

  /**
   * -negative_taxidlist `<String>`
   */
  var negativeTaxIDListFile: NegativeTaxIDList
  fun negativeTaxIDListFile(value: String)

  /**
   * -taxids `<String>`
   */
  var taxIDs: TaxIDs
  fun taxIDs(value: Iterable<String>)
  fun taxIDs(vararg values: String)


  /**
   * -negative_taxids `<String>`
   */
  var negativeTaxIDs: NegativeTaxIDs
  fun negativeTaxIDs(value: List<String>)
  fun negativeTaxIDs(vararg values: String)
}
