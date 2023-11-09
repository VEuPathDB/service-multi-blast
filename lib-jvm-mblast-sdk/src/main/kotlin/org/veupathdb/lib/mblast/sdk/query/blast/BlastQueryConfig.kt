package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

sealed class BlastQueryConfig {
  @get:JsonGetter("tool")
  abstract val tool: BlastQueryTool

  @JsonSetter("tool")
  protected fun setTool(v: Any) {}

  @get:JsonGetter("queryLocation")
  @set:JsonSetter("queryLocation")
  var queryLocation: BlastLocation? = null

  @get:JsonGetter("eValue")
  @set:JsonSetter("eValue")
  var eValue: String? = null

  @get:JsonGetter("softMasking")
  @set:JsonSetter("softMasking")
  var softMasking: Boolean? = null

  @get:JsonGetter("lowercaseMasking")
  @set:JsonSetter("lowercaseMasking")
  var lowercaseMasking: Boolean? = null

  @get:JsonGetter("queryCoverageHSPPercent")
  @set:JsonSetter("queryCoverageHSPPercent")
  var queryCoverageHSPPercent: Double? = null

  @get:JsonGetter("maxHSPs")
  @set:JsonSetter("maxHSPs")
  var maxHSPs: UInt? = null

  @get:JsonGetter("maxTargetSequences")
  @set:JsonSetter("maxTargetSequences")
  var maxTargetSequences: UInt? = null

  @get:JsonGetter("dbSize")
  @set:JsonSetter("dbSize")
  var dbSize: Byte? = null

  @get:JsonGetter("searchSpace")
  @set:JsonSetter("searchSpace")
  var searchSpace: UByte? = null

  @get:JsonGetter("xDropoffUngapped")
  @set:JsonSetter("xDropoffUngapped")
  var xDropoffUngapped: Double? = null

  @get:JsonGetter("windowSize")
  @set:JsonSetter("windowSize")
  var windowSize: UInt? = null

  @get:JsonGetter("parseDefLines")
  @set:JsonSetter("parseDefLines")
  var parseDefLines: Boolean? = null
}

