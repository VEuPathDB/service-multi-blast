package org.veupathdb.lib.blast.tblastx

import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.tblastx.fields.ScoringMatrixTX
import org.veupathdb.lib.blast.tblastx.fields.SegTX
import org.veupathdb.lib.blast.tblastx.fields.SoftMaskingTX
import org.veupathdb.lib.blast.tblastx.fields.WordSizeTX

/**
 * Translated Query-Translated Subject BLAST 2.11.0+
 */
interface TBlastX : BlastQueryWithLists {

  /**
   * -strand `<String>`
   */
  var strand: Strand

  /**
   * -query_gencode `<Integer>`
   */
  var queryGenCode: QueryGenCode

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeTX

  /**
   * -max_intron_length `<Integer>`
   */
  var maxIntronLength: MaxIntronLength

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixTX

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -db_gencode `<Integer>`
   */
  var dbGenCode: DBGenCode

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
  var seg: SegTX

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingTX

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
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores
}