package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

class RPSTBlastNConfig : BlastQueryConfig() {

  override val tool: BlastQueryTool
    get() = BlastQueryTool.RPSTBlastN

  @get:JsonGetter("queryGenCode")
  @set:JsonSetter("queryGenCode")
  var queryGenCode: Int? = null

  @get:JsonGetter("strand")
  @set:JsonSetter("strand")
  var strand: BlastStrand? = null

  @get:JsonGetter("compBasedStats")
  @set:JsonSetter("compBasedStats")
  var compBasedStats: RPSTBlastNCompBasedStats? = null

  @get:JsonGetter("seg")
  @set:JsonSetter("seg")
  var seg: BlastSeg? = null

  @get:JsonGetter("sumStats")
  @set:JsonSetter("sumStats")
  var sumStats: Boolean? = null

  @get:JsonGetter("xDropoffPrelimGapped")
  @set:JsonSetter("xDropoffPrelimGapped")
  var xDropoffPrelimGapped: Double? = null

  @get:JsonGetter("xDropoffFinalGapped")
  @set:JsonSetter("xDropoffFinalGapped")
  var xDropoffFinalGapped: Double? = null

  @get:JsonGetter("ungappedOnly")
  @set:JsonSetter("ungappedOnly")
  var ungappedOnly: Boolean? = null

  @get:JsonGetter("useSWTraceback")
  @set:JsonSetter("useSWTraceback")
  var useSWTraceback: Boolean? = null
}