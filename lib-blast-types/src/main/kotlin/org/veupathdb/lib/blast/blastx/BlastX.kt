package org.veupathdb.lib.blast.blastx

import org.veupathdb.lib.blast.blastx.field.*
import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*

/**
 * Translated Query-Protein Subject BLAST 2.13.0+
 */
interface BlastX : BlastQueryWithIPG {

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
   * -task `<String>`
   */
  var task: BlastXTask
  fun task(value: BlastXTaskType)

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeX
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
   * -max_intron_length `<Integer>`
   */
  var maxIntronLength: MaxIntronLength
  fun maxIntronLength(value: UInt)

  /**
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixX
  fun matrix(value: ScoringMatrixXType)

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold
  fun threshold(value: Double)

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsX
  fun compBasedStats(value: CompBasedStatsXValue)

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
  var seg: SegX

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
  var softMasking: SoftMaskingX
  fun softMasking(value: Boolean)
}