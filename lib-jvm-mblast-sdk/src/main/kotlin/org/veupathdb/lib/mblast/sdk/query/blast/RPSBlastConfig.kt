package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

class RPSBlastConfig : BlastQueryConfig() {

  override val tool: BlastQueryTool
    get() = BlastQueryTool.RPSBlast

  @get:JsonGetter("compBasedStats")
  @set:JsonSetter("compBasedStats")
  var compBasedStats: RPSBlastCompBasedStats? = null

  @get:JsonGetter("seg")
  @set:JsonSetter("seg")
  var seg: BlastSeg? = null

  @get:JsonGetter("cullingLimit")
  @set:JsonSetter("cullingLimit")
  var cullingLimit: UInt? = null

  @get:JsonGetter("bestHitOverhang")
  @set:JsonSetter("bestHitOverhang")
  var bestHitOverhang: Double? = null

  @get:JsonGetter("bestHitScoreEdge")
  @set:JsonSetter("bestHitScoreEdge")
  var bestHitScoreEdge: Double? = null

  @get:JsonGetter("subjectBestHit")
  @set:JsonSetter("subjectBestHit")
  var subjectBestHit: Boolean? = null

  @get:JsonGetter("sumStats")
  @set:JsonSetter("sumStats")
  var sumStats: Boolean? = null

  @get:JsonGetter("xDropoffPrelimGapped")
  @set:JsonSetter("xDropoffPrelimGapped")
  var xDropoffPrelimGapped: Double? = null

  @get:JsonGetter("xDropoffFinalGapped")
  @set:JsonSetter("xDropoffFinalGapped")
  var xDropoffFinalGapped: Double? = null

  @get:JsonGetter("useSWTraceback")
  @set:JsonSetter("useSWTraceback")
  var useSWTraceback: Boolean? = null
}