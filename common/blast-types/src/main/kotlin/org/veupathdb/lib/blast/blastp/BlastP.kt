package org.veupathdb.lib.blast.blastp

import org.veupathdb.lib.blast.blastp.field.*
import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*

/**
 * Protein-Protein BLAST 2.11.0+
 */
interface BlastP : BlastQueryWithIPG {

  /**
   * -task `<String>`
   */
  var task: BlastPTask

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeP

  /**
   * -gapopen `<Integer>`
   */
  var gapOpen: GapOpen

  /**
   * -gapextend `<Integer>`
   */
  var gapExtend: GapExtend

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixP

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsP

  /**
   * -subject `<File_In>`
   */
  var subjectFile: SubjectFile

  /**
   * -subject_loc `<String>`
   */
  var subjectLocation: SubjectLocation

  /**
   * -seg `<String>`
   */
  var seg: SegP

  /**
   * -db_soft_mask `<String>`
   */
  var dbSoftMask: DBSoftMask

  /**
   * -db_hard_mask `<String>`
   */
  var dbHardMask: DBHardMask

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent

  /**
   * -culling_limit `<Integer>`
   */
  var cullingLimit: CullingLimit

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
  var numCPUCores: NumCPUCores

  /**
   * -use_sw_tback
   */
  var useSmithWatermanTraceback: UseSmithWatermanTraceback

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
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingP
}