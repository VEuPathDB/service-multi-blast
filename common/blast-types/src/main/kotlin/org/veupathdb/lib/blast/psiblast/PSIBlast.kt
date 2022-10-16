package org.veupathdb.lib.blast.psiblast

import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.psiblast.fields.*

/**
 * Position-Specific Iterated BLAST 2.11.0+
 */
interface PSIBlast : BlastQueryWithIPG {

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizePSI

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
  var matrix: ScoringMatrixPSI

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsPSI

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
  var seg: SegPSI

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingPSI

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
   * -in_msa `<File_In>`
   */
  var inMSAFile: InMSAFile

  /**
   *  -msa_master_idx `<Integer>`
   */
  var msaMasterIndex: MSAMasterIndex

  /**
   * -ignore_msa_master
   */
  var ignoreMSAMaster: IgnoreMSAMaster

  /**
   * -in_pssm `<File_In>`
   */
  var inPSSMFile: InPSSMFile

  /**
   * -pseudocount `<Integer>`
   */
  var pseudoCount: PseudoCount

  /**
   * -inclusion_ethresh `<Real>`
   */
  var inclusionEValueThreshold: InclusionEValueThreshold

  /**
   * -phi_pattern `<File_In>`
   */
  var phiPatternFile: PHIPatternFile
}