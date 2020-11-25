package org.veupathdb.service.multiblast.model.blast;

import java.util.Optional;

public enum ToolOption
{
  BestHitOverhang("best_hit_overhang"),
  BestHitScoreEdge("best_hit_score_edge"),
  BlastDatabase("db"),
  CompositionBasedStats("comp_based_stats"),
  CullingLimit("culling_limit"),
  DatabaseEffectiveSize("dbsize"),
  DatabaseHardMask("db_hard_mask"),
  DatabaseSoftMask("db_soft_mask"),
  DatabaseTranslationGenCode("db_gencode"),
  Dust("dust"),
  EntrezQuery("entrez_query"),
  ExpectationValue("evalue"),
  ExportSearchStrategy("export_search_strategy"),
  FilteringDatabasePath("filtering_db"),
  GapCostExtend("gapextend"),
  GapCostOpen("gapopen"),
  GIListFile("gilist"),
  Help("help"),
  HTMLOutput("html"),
  IgnoreMultiSequenceAlignmentMaster("ignore_msa_master"),
  ImportSearchStrategy("import_search_strategy"),
  InclusionEValueThreshold("inclusion_ethresh"),
  InputMultiSequenceAlignmentFile("in_msa"),
  InputPsiBlastCheckpointFile("in_pssm"),
  IdenticalProteinGroupListFile("ipglist"),
  LineLength("line_length"),
  LowercaseMasking("lcase_masking"),
  MatchReward("reward"),
  MaxHSPs("max_hsps"),
  MaxTargetSequences("max_target_seqs"),
  MegablastIndexName("index_name"),
  DiscontiguousMegablastTemplateLength("template_length"),
  DiscontiguousMegablastTemplateType("template_type"),
  MaxIntronLength("max_intron_length"),
  MinRawGappedScore("min_raw_gapped_score"),
  MismatchPenalty("penalty"),
  MultiHitWindowSize("window_size"),
  MultiSequenceAlignmentMasterIndex("msa_master_idx"),
  NegativeGIListFile("negative_gilist"),
  NegativeIdenticalProteinGroupListFile("negative_ipglist"),
  NegativeSequenceIDListFile("negative_seqidlist"),
  NegativeTaxonomyIDs("negative_taxids"),
  NegativeTaxonomyIDListFile("negative_taxidlist"),
  NonGreedyExtension("no_greedy"),
  NumAlignments("num_alignments"),
  NumberOfThreads("num_threads"),
  NumDescriptions("num_descriptions"),
  NumIterations("num_iterations"),
  OffDiagonalRange("off_diagonal_range"),
  OutputFile("out"),
  OutAsciiPsiBlastCheckpointFile("out_ascii_pssm"),
  OutPsiBlastCheckpointFile("out_pssm"),
  OutputFormat("outfmt"),
  ParseDefLines("parse_deflines"),
  PercentIdentity("perc_identity"),
  PhiBlastPatternFile("phi_pattern"),
  PseudoCount("pseudocount"),
  Query("query"),
  QueryCoveragePercentHSP("qcov_hsp_perc"),
  QueryGeneticCode("query_gencode"),
  QueryLocation("query_loc"),
  Remote("remote"),
  SaveEachPsiBlastCheckpoint("save_each_pssm"),
  SavePsiBlastCheckpointAfterLastRound("save_pssm_after_last_round"),
  ScoringMatrix("matrix"),
  SearchSpaceEffectiveLength("searchsp"),
  SEGFilter("seg"),
  SequenceIDListFile("seqidlist"),
  ShowNCBIGIs("show_gis"),
  SoftMasking("soft_masking"),
  SortHits("sorthits"),
  SortHSPs("sorthsps"),
  QueryStrand("strand"),
  SubjectBestHit("subject_besthit"),
  SubjectFile("subject"),
  SubjectLocation("subject_loc"),
  SumStats("sum_stats"),
  Task("task"),
  TaxonomyIDs("taxids"),
  TaxonomyIDListFile("taxidlist"),
  Threshold("threshold"),
  UngappedAlignmentOnly("ungapped"),
  UseMegablastIndex("use_index"),
  UseSmithWatermanAlignments("use_sw_tback"),
  Version("version"),
  WindowMaskerDatabasePath("window_masker_db"),
  WindowMaskerTaxonomicID("window_masker_taxid"),
  WordSize("word_size"),
  ExtensionDropoffFinalGapped("xdrop_gap_final"),
  ExtensionDropoffPrelimGapped("xdrop_gap"),
  XDropoffUngappedExtensions("xdrop_ungap"),
  ;

  private final String flag;

  ToolOption(String flag) {
    this.flag = flag;
  }

  public String getFlag() {
    return "-" + this.flag;
  }

  @Override
  public String toString() {
    return flag;
  }

  public static ToolOption fromString(String value) {
    for (var e : ToolOption.values())
      if (e.flag.equals(value))
        return e;

    throw new IllegalArgumentException(); // TODO: print actual value
  }
}
