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

  /**
   * -negative_gilist `<String>`
   */
  var negativeGIListFile: NegativeGIList

  /**
   * -seqidlist `<String>`
   */
  var seqIDListFile: SeqIDList

  /**
   * -negative_seqidlist `<String>`
   */
  var negativeSeqIDListFile: NegativeSeqIDList

  /**
   * -taxidlist `<String>`
   */
  var taxIDListFile: TaxIDList

  /**
   * -negative_taxidlist `<String>`
   */
  var negativeTaxIDListFile: NegativeTaxIDList

  /**
   * -taxids `<String>`
   */
  var taxIDs: TaxIDs

  /**
   * -negative_taxids `<String>`
   */
  var negativeTaxIDs: NegativeTaxIDs
}
