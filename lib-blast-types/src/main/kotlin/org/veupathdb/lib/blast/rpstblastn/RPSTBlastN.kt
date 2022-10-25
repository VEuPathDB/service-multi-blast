package org.veupathdb.lib.blast.rpstblastn

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.CompBasedStatsRPSTNValue
import org.veupathdb.lib.blast.rpstblastn.fields.SegRPSTN
import org.veupathdb.lib.blast.rpstblastn.fields.SoftMaskingRPSTN

/**
 * Translated Reverse Position Specific BLAST 2.13.0+
 */
interface RPSTBlastN : BlastQueryBase {

  /**
   * -query_gencode `<Integer>`
   */
  var queryGenCode: QueryGenCode
  fun queryGenCode(value: UByte)

  /**
   * -strand `<String>`
   */
  var strand: Strand
  fun strand(value: StrandType)

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsRPSTN
  fun compBasedStats(value: CompBasedStatsRPSTNValue)

  /**
   * -seg `<String>`
   */
  var seg: SegRPSTN

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingRPSTN
  fun softMasking(value: Boolean)

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent
  fun queryCoverageHSPPercent(value: Double)

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
   * -ungapped
   */
  var ungappedAlignmentsOnly: UngappedAlignmentsOnly
  fun ungappedAlignmentsOnly(value: Boolean)

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

  /**
   * Clones the current config and all it's field values.
   *
   * @return A cloned copy of this config instance.
   *
   * @since v8.2.0
   */
  override fun clone(): RPSTBlastN
}