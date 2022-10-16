package org.veupathdb.lib.blast.rpsblast

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpsblast.fields.CompBasedStatsRPS
import org.veupathdb.lib.blast.rpsblast.fields.SegRPS
import org.veupathdb.lib.blast.rpsblast.fields.SoftMaskingRPS

/**
 * Reverse Position Specific BLAST 2.11.0+
 */
interface RPSBlast : BlastQueryBase {
  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsRPS

  /**
   * -seg `<String>`
   */
  var seg: SegRPS

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingRPS

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent

  /**
   * -culling_limit `<Integer>`
   */
  var cullingLimit: CullingLimit

  /**
   * -best_hit_overhang `<Real>`
   */
  var bestHitOverhang: BestHitOverhang

  /**
   * -best_hit_score_edge `<Real>`
   */
  var bestHitScoreEdge: BestHitScoreEdge

  /**
   * -subject_besthit
   */
  var subjectBestHit: SubjectBestHit

  /**
   * -sum_stats `<Boolean>`
   */
  var sumStats: SumStats

  /**
   * -xdrop_gap `<Real>`
   */
  var extensionDropoffPrelimGapped: ExtensionDropoffPrelimGapped

  /**
   * -xdrop_gap_final `<Real>`
   */
  var extensionDropoffFinalGapped: ExtensionDropoffFinalGapped

  /**
   * -num_threads `<Integer>`
   */
  var autoCPUCores: AutoCPUCores

  /**
   * -mt_mode `<Integer>`
   */
  var multiThreadingMode: MultiThreadingMode

  /**
   * -use_sw_tback
   */
  var useSmithWatermanTraceback: UseSmithWatermanTraceback
}