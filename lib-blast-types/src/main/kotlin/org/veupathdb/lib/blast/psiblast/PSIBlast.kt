package org.veupathdb.lib.blast.psiblast

import org.veupathdb.lib.blast.common.BlastQueryWithIPG
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.psiblast.fields.*

/**
 * Position-Specific Iterated BLAST 2.13.0+
 */
interface PSIBlast : BlastQueryWithIPG {

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizePSI
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
   * -matrix `<String>`
   */
  var matrix: ScoringMatrixPSI
  fun matrix(value: ScoringMatrixPSIType)

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold
  fun threshold(value: Double)

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsPSI
  fun compBasedStats(value: CompBasedStatsPSIValue)

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
  var seg: SegPSI

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingPSI
  fun softMasking(value: Boolean)

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
   * -gap_trigger `<Real>`
   */
  var gapTrigger: GapTrigger
  fun gapTrigger(value: Double)

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
   * -num_iterations `<Integer>`
   */
  var numIterations: NumIterations
  fun numIterations(value: UByte)

  /**
   * -out_pssm `<File_Out>`
   */
  var outPSSMFile: OutPSSMFile
  fun outPSSMFile(value: String)

  /**
   * -out_ascii_pssm `<File_Out>`
   */
  var outASCIIPSSMFile: OutASCIIPSSMFile
  fun outASCIIPSSMFile(value: String)

  /**
   * -save_pssm_after_last_round
   */
  var savePSSMAfterLastRound: SavePSSMAfterLastRound
  fun savePSSMAfterLastRound(value: Boolean)

  /**
   * -save_each_pssm
   */
  var saveEachPSSM: SaveEachPSSM
  fun saveEachPSSM(value: Boolean)

  /**
   * -in_msa `<File_In>`
   */
  var inMSAFile: InMSAFile
  fun inMSAFile(value: String)

  /**
   *  -msa_master_idx `<Integer>`
   */
  var msaMasterIndex: MSAMasterIndex
  fun msaMasterIndex(value: UInt)

  /**
   * -ignore_msa_master
   */
  var ignoreMSAMaster: IgnoreMSAMaster
  fun ignoreMSAMaster(value: Boolean)

  /**
   * -in_pssm `<File_In>`
   */
  var inPSSMFile: InPSSMFile
  fun inPSSMFile(value: String)

  /**
   * -pseudocount `<Integer>`
   */
  var pseudoCount: PseudoCount
  fun pseudoCount(value: Int)

  /**
   * -inclusion_ethresh `<Real>`
   */
  var inclusionEValueThreshold: InclusionEValueThreshold
  fun inclusionEValueThreshold(value: Double)

  /**
   * -phi_pattern `<File_In>`
   */
  var phiPatternFile: PHIPatternFile
  fun phiPatternFile(value: String)

  override fun clone(): PSIBlast
}