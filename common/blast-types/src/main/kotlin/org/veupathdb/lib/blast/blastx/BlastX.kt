package org.veupathdb.lib.blast.blastx

import org.veupathdb.lib.blast.blastx.field.*
import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*

/**
 * Translated Query-Protein Subject BLAST 2.11.0+
 */
interface BlastX : BlastQueryWithIPG {

  /**
   * -strand `<String>`
   */
  var strand: Strand

  /**
   * -query_gencode `<Integer>`
   */
  var queryGenCode: QueryGenCode

  /**
   * -task `<String>`
   */
  var task: BlastXTask

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeX

  /**
   * -gapopen `<Integer>`
   */
  var gapOpen: GapOpen

  /**
   * -gapextend `<Integer>`
   */
  var gapExtend: GapExtend

  /**
   * -max_intron_length `<Integer>`
   */
  var maxIntronLength: MaxIntronLength

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixX

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsX

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
  var seg: SegX

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
  var softMasking: SoftMaskingX
}