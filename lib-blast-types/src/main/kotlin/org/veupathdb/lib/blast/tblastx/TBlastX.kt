package org.veupathdb.lib.blast.tblastx

import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastx.fields.*

/**
 * Translated Query-Translated Subject BLAST 2.13.0+
 */
interface TBlastX : BlastQueryWithLists {

  /**
   * -strand `<String>`
   */
  var strand: Strand
  fun strand(value: StrandType)

  /**
   * -query_gencode `<Integer>`
   */
  var queryGenCode: QueryGenCode
  fun queryGenCode(value: UByte)

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeTX
  fun wordSize(value: UInt)

  /**
   * -max_intron_length `<Integer>`
   */
  var maxIntronLength: MaxIntronLength
  fun maxIntronLength(value: UInt)

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixTX
  fun matrix(value: ScoringMatrixTXType)

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold
  fun threshold(value: Double)

  /**
   * -db_gencode `<Integer>`
   */
  var dbGenCode: DBGenCode
  fun dbGenCode(value: UByte)

  /**
   * -subject `<File_In>`
   */
  var subjectFile: SubjectFile
  fun subjectFile(value: String)

  /**
   * -subject_loc `<String>`
   */
  var subjectLocation: SubjectLocation
  fun subjectLocation(start: UInt, stop: UInt)

  /**
   * -seg `<String>`
   */
  var seg: SegTX

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingTX
  fun softMasking(value: Boolean)

  /**
   * -db_soft_mask `<String>`
   */
  var dbSoftMask: DBSoftMask
  fun dbSoftMask(value: String)

  /**
   * -db_hard_mask `<String>`
   */
  var dbHardMask: DBHardMask
  fun dbHardMask(value: String)

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent
  fun queryCoverageHSPPercent(value: Double)

  /**
   * -culling_limit `<Integer>`
   */
  var cullingLimit: CullingLimit
  fun cullingLimit(value: UInt)

  /**
   * -best_hit_overhang `<Real>`
   */
  var bestHitOverhang: BestHitOverhang
  fun bestHitOverhang(value: Double)

  /**
   * -best_hit_score_edge `<Real>`
   */
  var bestHitScoreEdge: BestHitScoreEdge
  fun bestHitScoreEdge(value: Double)

  /**
   * -subject_besthit
   */
  var subjectBestHit: SubjectBestHit
  fun subjectBestHit(value: Boolean)

  /**
   * -sum_stats `<Boolean>`
   */
  var sumStats: SumStats
  fun sumStats(value: Boolean)

  /**
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores
  fun numCPUCores(value: UByte)

  /**
   * Clones the current config and all it's field values.
   *
   * @return A cloned copy of this config instance.
   *
   * @since v8.2.0
   */
  override fun clone(): TBlastX
}