package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

class TBlastXConfig : BlastQueryConfig() {

  override val tool: BlastQueryTool
    get() = BlastQueryTool.TBlastX

  @get:JsonGetter("strand")
  @set:JsonSetter("strand")
  var strand: BlastStrand? = null

  @get:JsonGetter("queryGenCode")
  @set:JsonSetter("queryGenCode")
  var queryGenCode: Int? = null

  @get:JsonGetter("wordSize")
  @set:JsonSetter("wordSize")
  var wordSize: UInt? = null

  @get:JsonGetter("maxIntronLength")
  @set:JsonSetter("maxIntronLength")
  var maxIntronLength: UInt? = null

  @get:JsonGetter("matrix")
  @set:JsonSetter("matrix")
  var matrix: TBlastXMatrix? = null

  @get:JsonGetter("threshold")
  @set:JsonSetter("threshold")
  var threshold: Double? = null

  @get:JsonGetter("dbGenCode")
  @set:JsonSetter("dbGenCode")
  var dbGenCode: Int? = null

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
}