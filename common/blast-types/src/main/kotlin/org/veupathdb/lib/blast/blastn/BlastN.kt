package org.veupathdb.lib.blast.blastn

import org.veupathdb.lib.blast.blastn.fields.*
import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*

/**
 * Nucleotide-Nucleotide BLAST 2.11.0+
 */
interface BlastN : BlastQueryWithLists {

  /**
   * -strand `<String>`
   */
  var strand: Strand
  fun strand(value: StrandType)

  /**
   * -task `<String>`
   */
  var task: BlastNTask
  fun task(value: BlastNTaskType)

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeN
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
   * -penalty `<Integer>`
   */
  var penalty: Penalty
  fun penalty(value: Int)

  /**
   * -reward `<Integer>`
   */
  var reward: Reward
  fun reward(value: UInt)

  /**
   * -use_index `<Boolean>`
   */
  var useIndex: UseIndex
  fun useIndex(value: Boolean)

  /**
   * -index_name `<String>`
   */
  var indexName: IndexName
  fun indexName(value: String)

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
   * -dust `<String>`
   */
  var dust: Dust

  /**
   * -filtering_db `<String>`
   */
  var filteringDBFile: FilteringDB
  fun filteringDBFile(value: String)

  /**
   * -window_masker_taxid `<Integer>`
   */
  var windowMaskerTaxID: WindowMaskerTaxID
  fun windowMaskerTaxID(value: Int)

  /**
   * -window_masker_db `<String>`
   */
  var windowMaskerDBFile: WindowMaskerDB
  fun windowMaskerDBFile(value: String)

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
   * -perc_identity `<Real>`
   */
  var percentIdentity: PercentIdentity
  fun percentIdentity(value: Double)

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
   * -template_type `<String>`
   */
  var templateType: TemplateType
  fun templateType(value: TemplateTypeValue)

  /**
   * -template_length `<Integer>`
   */
  var templateLength: TemplateLength
  fun templateLength(value: TemplateLengthValue)

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
   * -no_greedy
   */
  var nonGreedy: NonGreedy
  fun nonGreedy(value: Boolean)

  /**
   * -min_raw_gapped_score `<Integer>`
   */
  var minRawGappedScore: MinRawGappedScore
  fun minRawGappedScore(value: Int)

  /**
   * -ungapped
   */
  var ungappedAlignmentsOnly: UngappedAlignmentsOnly
  fun ungappedAlignmentsOnly(value: Boolean)

  /**
   * -off_diagonal_range `<Integer>`
   */
  var offDiagonalRange: OffDiagonalRange
  fun offDiagonalRange(value: UInt)

  /**
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores
  fun numCPUCores(value: UByte)

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
  var softMasking: SoftMaskingN
  fun softMasking(value: Boolean)
}
