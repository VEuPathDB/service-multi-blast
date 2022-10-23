package org.veupathdb.lib.blast.deltablast

import org.veupathdb.lib.blast.common.BlastQueryWithLists
import org.veupathdb.lib.blast.common.fields.*
import org.veupathdb.lib.blast.deltablast.fields.*

/**
 * Domain enhanced lookup time accelerated BLAST 2.13.0+
 */
interface DeltaBlast : BlastQueryWithLists {

  /**
   * -word_size `<Integer>`
   */
  var wordSize: WordSizeDelta
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
  var matrix: ScoringMatrixDelta
  fun matrix(value: ScoringMatrixDeltaType)

  /**
   * -threshold `<Real>`
   */
  var threshold: Threshold
  fun threshold(value: Double)

  /**
   * -comp_based_stats `<String>`
   */
  var compBasedStats: CompBasedStatsDelta
  fun compBasedStats(value: CompBasedStatsDeltaValue)

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
  var seg: SegDelta

  /**
   * -soft_masking `<Boolean>`
   */
  var softMasking: SoftMaskingDelta
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
   * -max_target_seqs `<Integer>`
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
   * -pseudocount `<Integer>`
   */
  var pseudoCount: PseudoCount
  fun pseudoCount(value: Int)

  /**
   * -domain_inclusion_ethresh `<Real>`
   */
  var domainInclusionEValueThreshold: DomainInclusionEValueThreshold
  fun domainInclusionEValueThreshold(value: Double)

  /**
   * -inclusion_ethresh `<Real>`
   */
  var inclusionEValueThreshold: InclusionEValueThreshold
  fun inclusionEValueThreshold(value: Double)

  /**
   * -rpsdb `<String>`
   */
  var rpsDB: RPSDB
  fun rpsDB(value: String)

  /**
   * -show_domain_hits
   */
  var showDomainHits: ShowDomainHits
  fun showDomainHits(value: Boolean)

  override fun clone(): DeltaBlast
}