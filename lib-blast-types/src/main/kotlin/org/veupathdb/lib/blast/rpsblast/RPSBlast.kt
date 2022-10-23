package org.veupathdb.lib.blast.rpsblast

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpsblast.fields.CompBasedStatsRPS
import org.veupathdb.lib.blast.rpsblast.fields.CompBasedStatsRPSValue
import org.veupathdb.lib.blast.rpsblast.fields.SegRPS
import org.veupathdb.lib.blast.rpsblast.fields.SoftMaskingRPS

/**
 * Reverse Position Specific BLAST 2.13.0+
 */
interface RPSBlast : BlastQueryBase {
  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsRPS
  fun compBasedStats(value: CompBasedStatsRPSValue)

  /**
   * -seg `<String>`
   */
  var seg: SegRPS

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingRPS
  fun softMasking(value: Boolean)

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
   * -num_threads `<Integer>`
   */
  var autoCPUCores: AutoCPUCores
  fun autoCPUCores(value: AutoCPUCoresValue)

  /**
   * -mt_mode `<Integer>`
   */
  var multiThreadingMode: MultiThreadingMode
  fun multiThreadingMode(value: MultiThreadingModeValue)

  /**
   * -use_sw_tback
   */
  var useSmithWatermanTraceback: UseSmithWatermanTraceback
  fun useSmithWatermanTraceback(value: Boolean)

  override fun clone(): RPSBlast
}