package mb.lib.blast.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode

@Suppress("SpellCheckingInspection")
enum class ToolOption(val value: String)
{
  // 0
  BestHitOverhang                       ("best_hit_overhang"),
  BestHitScoreEdge                      ("best_hit_score_edge"),
  CompositionBasedStats                 ("comp_based_stats"),
  CullingLimit                          ("culling_limit"),
  BlastDatabase                         ("db"),
  // 5
  DatabaseTranslationGenCode            ("db_gencode"),
  DatabaseHardMask                      ("db_hard_mask"),
  DatabaseSoftMask                      ("db_soft_mask"),
  EffectiveDatabaseSize                 ("dbsize"),
  Dust                                  ("dust"),
  // 10
  EntrezQuery                           ("entrez_query"),
  ExpectValue                           ("evalue"),
  ExportSearchStrategy                  ("export_search_strategy"),
  FilteringDatabasePath                 ("filtering_db"),
  GIListFile                            ("gilist"),
  // 15
  GapCostExtend                         ("gapextend"),
  GapCostOpen                           ("gapopen"),
  Help                                  ("help"),
  HTMLOutput                            ("html"),
  IgnoreMultiSequenceAlignmentMaster    ("ignore_msa_master"),
  // 20
  ImportSearchStrategy                  ("import_search_strategy"),
  InputMultiSequenceAlignmentFile       ("in_msa"),
  InputPsiBlastCheckpointFile           ("in_pssm"),
  InclusionEValueThreshold              ("inclusion_ethresh"),
  MegablastIndexName                    ("index_name"),
  // 25
  IdenticalProteinGroupListFile         ("ipglist"),
  LowercaseMasking                      ("lcase_masking"),
  LineLength                            ("line_length"),
  ScoringMatrix                         ("matrix"),
  MaxHSPs                               ("max_hsps"),
  // 30
  MaxIntronLength                       ("max_intron_length"),
  MaxTargetSequences                    ("max_target_seqs"),
  MinRawGappedScore                     ("min_raw_gapped_score"),
  MultiSequenceAlignmentMasterIndex     ("msa_master_idx"),
  NegativeGIListFile                    ("negative_gilist"),
  // 35
  NegativeIdenticalProteinGroupListFile ("negative_ipglist"),
  NegativeSequenceIDListFile            ("negative_seqidlist"),
  NegativeTaxonomyIDListFile            ("negative_taxidlist"),
  NegativeTaxonomyIDs                   ("negative_taxids"),
  NonGreedyExtension                    ("no_greedy"),
  // 40
  NumAlignments                         ("num_alignments"),
  NumDescriptions                       ("num_descriptions"),
  NumIterations                         ("num_iterations"),
  NumberOfThreads                       ("num_threads"),
  OffDiagonalRange                      ("off_diagonal_range"),
  // 45
  OutputFile                            ("out"),
  OutAsciiPsiBlastCheckpointFile        ("out_ascii_pssm"),
  OutPsiBlastCheckpointFile             ("out_pssm"),
  OutputFormat                          ("outfmt"),
  ParseDefLines                         ("parse_deflines"),
  // 50
  MismatchPenalty                       ("penalty"),
  PercentIdentity                       ("perc_identity"),
  PhiBlastPatternFile                   ("phi_pattern"),
  PseudoCount                           ("pseudocount"),
  QueryCoveragePercentHSP               ("qcov_hsp_perc"),
  // 55
  Query                                 ("query"),
  QueryGeneticCode                      ("query_gencode"),
  QueryLocation                         ("query_loc"),
  MatchReward                           ("reward"),
  Remote                                ("remote"),
  // 60
  QueryStrand                           ("strand"),
  SaveEachPsiBlastCheckpoint            ("save_each_pssm"),
  SavePsiBlastCheckpointAfterLastRound  ("save_pssm_after_last_round"),
  SearchSpaceEffectiveLength            ("searchsp"),
  SEGFilter                             ("seg"),
  // 65
  SequenceIDListFile                    ("seqidlist"),
  ShowNCBIGIs                           ("show_gis"),
  SoftMasking                           ("soft_masking"),
  HitSorting                            ("sorthits"),
  HSPSorting                            ("sorthsps"),
  // 70
  SubjectFile                           ("subject"),
  SubjectBestHit                        ("subject_besthit"),
  SubjectLocation                       ("subject_loc"),
  SumStats                              ("sum_stats"),
  Task                                  ("task"),
  // 75
  TaxonomyIDListFile                    ("taxidlist"),
  TaxonomyIDs                           ("taxids"),
  DiscontiguousMegablastTemplateLength  ("template_length"),
  DiscontiguousMegablastTemplateType    ("template_type"),
  Threshold                             ("threshold"),
  // 80
  UngappedAlignmentOnly                 ("ungapped"),
  UseMegablastIndex                     ("use_index"),
  UseSmithWatermanAlignments            ("use_sw_tback"),
  Version                               ("version"),
  WindowMaskerDatabasePath              ("window_masker_db"),
  // 85
  WindowMaskerTaxonomicID               ("window_masker_taxid"),
  MultiHitWindowSize                    ("window_size"),
  WordSize                              ("word_size"),
  ExtensionDropoffPrelimGapped          ("xdrop_gap"),
  ExtensionDropoffFinalGapped           ("xdrop_gap_final"),
  // 90
  ExtensionDropoffUngapped              ("xdrop_ungap"),
  ;

  val flag get() = "-$value"

  override fun toString() = flag

  @JsonValue
  fun toJSON(): JsonNode = TextNode(value)

  companion object {

    /**
     * Pointlessly micro-optimized getter allowing lookups of enum values by
     * their external text form.
     *
     * The method is micro-optimized so that lookups will take at best 3 steps
     * and at worst 16 steps to make a match.
     *
     * @param i Value to match against when looking up the corresponding
     * ToolOption value.
     *
     * This value may begin with a dash ('-') character.
     *
     * @return The matching ToolOption instance.
     *
     * @throws NoSuchElementException
     * If no ToolOption value was found matching the given input string.
     */
    @JsonCreator
    operator fun get(i: String): ToolOption {
      val options = values()
      val test    = if (i[0] == '-') i.substring(1) else i

      val c = test[0].code
      when {
        // [b..n]
        c < 111 -> when {
          // [b..h]
          c < 105 -> {
            for (e in 0..18) {
              if (options[e].value == test)
                return options[e]
            }
          }
          // [i-n]
          else -> {
            for (e in 19..43) {
              if (options[e].value == test)
                return options[e]
            }
          }
        }
        // [o..x]
        else -> when {
          // [o..r]
          c < 115 -> {
            for (e in 44..59) {
              if (options[e].value == test)
                return options[e]
            }
          }

          // [s]
          c == 115 -> {
            for (e in 60..73) {
              if (options[e].value == test)
                return options[e]
            }
          }

          // [t..x]
          else -> {
            for (e in 73..90) {
              if (options[e].value == test)
                return options[e]
            }
          }
        }
      }

      throw NoSuchElementException()
    }
  }
}
