package org.veupathdb.lib.blast.rpstblastn

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.SegRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.SoftMaskingRPSTN

/**
 * Translated Reverse Position Specific BLAST 2.11.0+
 */
interface RPSTBlastN : BlastQueryBase {

  /**
   * -query_gencode `<Integer>`
   */
  var queryGenCode: QueryGenCode

  /**
   * -strand `<String>`
   */
  var strand: Strand

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsRPSTN

  /**
   * -seg `<String>`
   */
  var seg: SegRPSTN

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingRPSTN

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent

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
   * -ungapped
   */
  var ungappedAlignmentsOnly: UngappedAlignmentsOnly

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