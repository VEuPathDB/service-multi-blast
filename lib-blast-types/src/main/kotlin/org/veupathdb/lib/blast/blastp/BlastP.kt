package org.veupathdb.lib.blast.blastp

import org.veupathdb.lib.blast.blastp.field.*
import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*

/**
 * Protein-Protein BLAST 2.13.0+
 */
interface BlastP : BlastQueryWithIPG {

  /**
   * -task `<String>`
   */
  var task: BlastPTask
  fun task(value: BlastPTaskType)

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeP
  fun wordSize(value: UInt)

  /**
   * -gapopen `<Integer>`
   */
  var gapOpen: GapOpen
  fun gapOpen(value: Int)

  /**
   * -gapextend `<Integer>`
   */
  var gapExtend: GapExtend
  fun gapExtend(value: Int)

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixP
  fun matrix(value: ScoringMatrixPType)

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold
  fun threshold(value: Double)

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsP
  fun compBasedStats(value: CompBasedStatsPValue)

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
  var seg: SegP

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
   * -xdrop_gap `<Real>`
   */
  var extensionDropoffPrelimGapped: ExtensionDropoffPrelimGapped
  fun extensionDropoffPrelimGapped(value: Double)

  /**
   * -xdrop_gap_final `<Real>`
   */
  var extensionDropoffFinalGapped: ExtensionDropoffFinalGapped
  fun extensionDropoffFinalGapped(value: Double)

  /**
   * -ungapped
   */
  var ungappedAlignmentsOnly: UngappedAlignmentsOnly
  fun ungappedAlignmentsOnly(value: Boolean)

  /**
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores
  fun numCPUCores(value: UByte)

  /**
   * -use_sw_tback
   */
  var useSmithWatermanTraceback: UseSmithWatermanTraceback
  fun useSmithWatermanTraceback(value: Boolean)

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
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingP
  fun softMasking(value: Boolean)

  override fun clone(): BlastP
}