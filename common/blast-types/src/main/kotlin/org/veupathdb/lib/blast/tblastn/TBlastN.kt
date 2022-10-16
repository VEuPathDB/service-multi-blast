package org.veupathdb.lib.blast.tblastn

import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastn.fields.*

/**
 * Protein Query-Translated Subject BLAST 2.11.0+
 */
interface TBlastN : BlastQueryWithLists {
  /**
   * -task `<String>`
   */
  var task: TBlastNTask

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeTN

  /**
   * -gapopen `<Integer>`
   */
  var gapOpen: GapOpen

  /**
   * -gapextend `<Integer>`
   */
  var gapExtend: GapExtend

  /**
   * -db_gencode `<Integer>`
   */
  var dbGenCode: DBGenCode

  /**
   * -max_intron_length `<Integer>`
   */
  var maxIntronLength: MaxIntronLength

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixTN

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsTN

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
  var seg: SegTN

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingTN

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
   * -in_pssm `<File_In>`
   */
  var inPSSMFile: InPSSMFile
}