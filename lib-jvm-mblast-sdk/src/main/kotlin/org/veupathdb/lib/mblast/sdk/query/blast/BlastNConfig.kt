package org.veupathdb.lib.mblast.sdk.query.blast

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter

class BlastNConfig : BlastQueryConfig() {

  override val tool: BlastQueryTool
    get() = BlastQueryTool.BlastN

  @get:JsonGetter("strand")
  @set:JsonSetter("strand")
  var strand: BlastStrand? = null

  @get:JsonGetter("task")
  @set:JsonSetter("task")
  var task: BlastNTask? = null

  @get:JsonGetter("wordSize")
  @set:JsonSetter("wordSize")
  var wordSize: UInt? = null

  @get:JsonGetter("gapOpen")
  @set:JsonSetter("gapOpen")
  var gapOpen: Int? = null

  @get:JsonGetter("gapExtend")
  @set:JsonSetter("gapExtend")
  var gapExtend: Int? = null

  @get:JsonGetter("penalty")
  @set:JsonSetter("penalty")
  var penalty: Int? = null

  @get:JsonGetter("reward")
  @set:JsonSetter("reward")
  var reward: UInt? = null

  @get:JsonGetter("useIndex")
  @set:JsonSetter("useIndex")
  var useIndex: Boolean? = null

  @get:JsonGetter("indexName")
  @set:JsonSetter("indexName")
  var indexName: String? = null

  @get:JsonGetter("dust")
  @set:JsonSetter("dust")
  var dust: BlastNDust? = null

  @get:JsonGetter("dbSoftMask")
  @set:JsonSetter("dbSoftMask")
  var dbSoftMask: String? = null

  @get:JsonGetter("dbHardMask")
  @set:JsonSetter("dbHardMask")
  var dbHardMask: String? = null

  @get:JsonGetter("percentIdentity")
  @set:JsonSetter("percentIdentity")
  var percentIdentity: Double? = null

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

  @get:JsonGetter("templateType")
  @set:JsonSetter("templateType")
  var templateType: BlastNTemplateType? = null

  @get:JsonGetter("templateLength")
  @set:JsonSetter("templateLength")
  var templateLength: BlastNTemplateLength? = null

  @get:JsonGetter("sumStats")
  @set:JsonSetter("sumStats")
  var sumStats: Boolean? = null

  @get:JsonGetter("xDropoffPrelimGapped")
  @set:JsonSetter("xDropoffPrelimGapped")
  var xDropoffPrelimGapped: Double? = null

  @get:JsonGetter("xDropoffFinalGapped")
  @set:JsonSetter("xDropoffFinalGapped")
  var xDropoffFinalGapped: Double? = null

  @get:JsonGetter("nonGreedy")
  @set:JsonSetter("nonGreedy")
  var nonGreedy: Boolean? = null

  @get:JsonGetter("minRawGappedScore")
  @set:JsonSetter("minRawGappedScore")
  var minRawGappedScore: Int? = null

  @get:JsonGetter("ungappedOnly")
  @set:JsonSetter("ungappedOnly")
  var ungappedOnly: Boolean? = null

  @get:JsonGetter("offDiagonalRange")
  @set:JsonSetter("offDiagonalRange")
  var offDiagonalRange: Int? = null
}
