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

  /**
   * -task `<String>`
   */
  var task: BlastNTask

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeN

  /**
   * -gapopen `<Integer>`
   */
  var gapOpen: GapOpen

  /**
   * -gapextend `<Integer>`
   */
  var gapExtend: GapExtend

  /**
   * -penalty `<Integer>`
   */
  var penalty: Penalty

  /**
   * -reward `<Integer>`
   */
  var reward: Reward

  /**
   * -use_index `<Boolean>`
   */
  var useIndex: UseIndex

  /**
   * -index_name `<String>`
   */
  var indexName: IndexName

  /**
   * -subject `<File_In>`
   */
  var subjectFile: SubjectFile

  /**
   * -subject_loc `<String>`
   */
  var subjectLocation: SubjectLocation

  /**
   * -dust `<String>`
   */
  var dust: Dust

  /**
   * -filtering_db `<String>`
   */
  var filteringDBFile: FilteringDB

  /**
   * -window_masker_taxid `<Integer>`
   */
  var windowMaskerTaxID: WindowMaskerTaxID

  /**
   * -window_masker_db `<String>`
   */
  var windowMaskerDBFile: WindowMaskerDB

  /**
   * -db_soft_mask `<String>`
   */
  var dbSoftMask: DBSoftMask

  /**
   * -db_hard_mask `<String>`
   */
  var dbHardMask: DBHardMask

  /**
   * -perc_identity `<Real>`
   */
  var percentIdentity: PercentIdentity

  /**
   * -qcov_hsp_perc `<Real>`
   */
  var queryCoverageHSPPercent: QueryCoverageHSPPercent

  /**
   * -culling_limit `<Integer>`
   */
  var cullingLimit: CullingLimit

  /**
   * -template_type `<String>`
   */
  var templateType: TemplateType

  /**
   * -template_length `<Integer>`
   */
  var templateLength: TemplateLength

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
   * -no_greedy
   */
  var nonGreedy: NonGreedy

  /**
   * -min_raw_gapped_score `<Integer>`
   */
  var minRawGappedScore: MinRawGappedScore

  /**
   * -ungapped
   */
  var ungappedAlignmentsOnly: UngappedAlignmentsOnly

  /**
   * -off_diagonal_range `<Integer>`
   */
  var offDiagonalRange: OffDiagonalRange

  /**
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores

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
  var softMasking: SoftMaskingN
}
