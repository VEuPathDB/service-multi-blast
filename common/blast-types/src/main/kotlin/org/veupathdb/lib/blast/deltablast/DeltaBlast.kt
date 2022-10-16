package org.veupathdb.lib.blast.deltablast

import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.deltablast.fields.*

interface DeltaBlast : BlastQueryWithLists {

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeDelta

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
  var matrix: ScoringMatrixDelta

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsDelta

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
  var seg: SegDelta

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingDelta

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
   * -max_target_seqs `<Integer>`
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
   * -gap_trigger `<Real>`
   */
  var gapTrigger: GapTrigger

  /**
   * -num_threads `<Integer>`
   */
  var numCPUCores: NumCPUCores

  /**
   * -use_sw_tback
   */
  var useSmithWatermanTraceback: UseSmithWatermanTraceback

  /**
   * -num_iterations `<Integer>`
   */
  var numIterations: NumIterations

  /**
   * -out_pssm `<File_Out>`
   */
  var outPSSMFile: OutPSSMFile

  /**
   * -out_ascii_pssm `<File_Out>`
   */
  var outASCIIPSSMFile: OutASCIIPSSMFile

  /**
   * -save_pssm_after_last_round
   */
  var savePSSMAfterLastRound: SavePSSMAfterLastRound

  /**
   * -save_each_pssm
   */
  var saveEachPSSM: SaveEachPSSM

  /**
   * -pseudocount `<Integer>`
   */
  var pseudoCount: PseudoCount

  /**
   * -domain_inclusion_ethresh `<Real>`
   */
  var domainInclusionEValueThreshold: DomainInclusionEValueThreshold

  /**
   * -inclusion_ethresh `<Real>`
   */
  var inclusionEValueThreshold: InclusionEValueThreshold

  /**
   * -rpsdb `<String>`
   */
  var rpsDB: RPSDB

  /**
   * -show_domain_hits
   */
  var showDomainHits: ShowDomainHits
}