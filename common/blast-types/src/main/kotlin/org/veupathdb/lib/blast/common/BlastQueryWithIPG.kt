package org.veupathdb.lib.blast.common

import org.veupathdb.lib.blast.common.fields.IPGList
import org.veupathdb.lib.blast.common.fields.NegativeIPGList

interface BlastQueryWithIPG : BlastQueryWithLists {

  /**
   * -ipglist `<String>`
   */
  var ipgListFile: IPGList

  /**
   * -negative_ipglist `<String>`
   */
  var negativeIPGListFile: NegativeIPGList
}