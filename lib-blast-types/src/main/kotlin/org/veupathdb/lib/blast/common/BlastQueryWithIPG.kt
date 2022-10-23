package org.veupathdb.lib.blast.common

import org.veupathdb.lib.blast.common.fields.IPGList
import org.veupathdb.lib.blast.common.fields.NegativeIPGList

interface BlastQueryWithIPG : BlastQueryWithLists {

  /**
   * -ipglist `<String>`
   */
  var ipgListFile: IPGList
  fun ipgListFile(value: String)

  /**
   * -negative_ipglist `<String>`
   */
  var negativeIPGListFile: NegativeIPGList
  fun negativeIPGListFile(value: String)

  /**
   * Clones this `BlastQueryWithIPG` instance and all of it's configured options.
   */
  override fun clone(): BlastQueryWithIPG
}