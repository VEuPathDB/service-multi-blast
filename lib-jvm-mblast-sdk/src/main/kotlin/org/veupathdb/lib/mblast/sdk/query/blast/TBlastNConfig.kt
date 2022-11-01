package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

class TBlastNConfig : BlastQueryConfig() {

  override val tool: BlastQueryTool
    get() = BlastQueryTool.TBlastN

  @get:JsonGetter("task")
  @set:JsonSetter("task")
  var task: TBlastNTask? = null

  @get:JsonGetter("wordSize")
  @set:JsonSetter("wordSize")
  var wordSize: UInt? = null

  @get:JsonGetter("gapOpen")
  @set:JsonSetter("gapOpen")
  var gapOpen: Int? = null

  @get:JsonGetter("gapExtend")
  @set:JsonSetter("gapExtend")
  var gapExtend: Int? = null

  @get:JsonGetter("dbGenCode")
  @set:JsonSetter("dbGenCode")
  var dbGenCode: Int? = null

  @get:JsonGetter("maxIntronLength")
  @set:JsonSetter("maxIntronLength")
  var maxIntronLength: UInt? = null

  @get:JsonGetter("matrix")
  @set:JsonSetter("matrix")
  var matrix: TBlastNMatrix? = null

  @get:JsonGetter("threshold")
  @set:JsonSetter("threshold")
  var threshold: Double? = null

  @get:JsonGetter("compBasedStats")
  @set:JsonSetter("compBasedStats")
  var compBasedStats: TBlastNCompBasedStats? = null

  @get:JsonGetter("seg")
  @set:JsonSetter("seg")
  var seg: BlastSeg? = null

  @get:JsonGetter("dbSoftMask")
  @set:JsonSetter("dbSoftMask")
  var dbSoftMask: String? = null

  @get:JsonGetter("dbHardMask")
  @set:JsonSetter("dbHardMask")
  var dbHardMask: String? = null

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

  @get:JsonGetter("ungappedOnly")
  @set:JsonSetter("ungappedOnly")
  var ungappedOnly: Boolean? = null

  @get:JsonGetter("useSWTraceback")
  @set:JsonSetter("useSWTraceback")
  var useSWTraceback: Boolean? = null
}