package org.veupathdb.service.multiblast.model.blast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ToolOption
{
  BestHitOverhang("best_hit_overhang"),
  BestHitScoreEdge("best_hit_score_edge"),
  BlastDatabase("db"),
  CompositionBasedStats("comp_based_stats"),
  CullingLimit("culling_limit"),
  EffectiveDatabaseSize("dbsize"),
  DatabaseHardMask("db_hard_mask"),
  DatabaseSoftMask("db_soft_mask"),
  DatabaseTranslationGenCode("db_gencode"),
  DiscontiguousMegablastTemplateLength("template_length"),
  DiscontiguousMegablastTemplateType("template_type"),
  Dust("dust"),
  EntrezQuery("entrez_query"),
  ExpectationValue("evalue"),
  ExportSearchStrategy("export_search_strategy"),
  ExtensionDropoffFinalGapped("xdrop_gap_final"),
  ExtensionDropoffPrelimGapped("xdrop_gap"),
  FilteringDatabasePath("filtering_db"),
  GIListFile("gilist"),
  GapCostExtend("gapextend"),
  GapCostOpen("gapopen"),
  HTMLOutput("html"),
  Help("help"),
  IdenticalProteinGroupListFile("ipglist"),
  IgnoreMultiSequenceAlignmentMaster("ignore_msa_master"),
  ImportSearchStrategy("import_search_strategy"),
  InclusionEValueThreshold("inclusion_ethresh"),
  InputMultiSequenceAlignmentFile("in_msa"),
  InputPsiBlastCheckpointFile("in_pssm"),
  LineLength("line_length"),
  LowercaseMasking("lcase_masking"),
  MatchReward("reward"),
  MaxHSPs("max_hsps"),
  MaxIntronLength("max_intron_length"),
  MaxTargetSequences("max_target_seqs"),
  MegablastIndexName("index_name"),
  MinRawGappedScore("min_raw_gapped_score"),
  MismatchPenalty("penalty"),
  MultiHitWindowSize("window_size"),
  MultiSequenceAlignmentMasterIndex("msa_master_idx"),
  NegativeGIListFile("negative_gilist"),
  NegativeIdenticalProteinGroupListFile("negative_ipglist"),
  NegativeSequenceIDListFile("negative_seqidlist"),
  NegativeTaxonomyIDListFile("negative_taxidlist"),
  NegativeTaxonomyIDs("negative_taxids"),
  NonGreedyExtension("no_greedy"),
  NumAlignments("num_alignments"),
  NumDescriptions("num_descriptions"),
  NumIterations("num_iterations"),
  NumberOfThreads("num_threads"),
  OffDiagonalRange("off_diagonal_range"),
  OutAsciiPsiBlastCheckpointFile("out_ascii_pssm"),
  OutPsiBlastCheckpointFile("out_pssm"),
  OutputFile("out"),
  OutputFormat("outfmt"),
  ParseDefLines("parse_deflines"),
  PercentIdentity("perc_identity"),
  PhiBlastPatternFile("phi_pattern"),
  PseudoCount("pseudocount"),
  Query("query"),
  QueryCoveragePercentHSP("qcov_hsp_perc"),
  QueryGeneticCode("query_gencode"),
  QueryLocation("query_loc"),
  QueryStrand("strand"),
  Remote("remote"),
  SEGFilter("seg"),
  SaveEachPsiBlastCheckpoint("save_each_pssm"),
  SavePsiBlastCheckpointAfterLastRound("save_pssm_after_last_round"),
  ScoringMatrix("matrix"),
  SearchSpaceEffectiveLength("searchsp"),
  SequenceIDListFile("seqidlist"),
  ShowNCBIGIs("show_gis"),
  SoftMasking("soft_masking"),
  HitSorting("sorthsps"),
  HSPSorting("sorthits"),
  SubjectBestHit("subject_besthit"),
  SubjectFile("subject"),
  SubjectLocation("subject_loc"),
  SumStats("sum_stats"),
  Task("task"),
  TaxonomyIDListFile("taxidlist"),
  TaxonomyIDs("taxids"),
  Threshold("threshold"),
  UngappedAlignmentOnly("ungapped"),
  UseMegablastIndex("use_index"),
  UseSmithWatermanAlignments("use_sw_tback"),
  Version("version"),
  WindowMaskerDatabasePath("window_masker_db"),
  WindowMaskerTaxonomicID("window_masker_taxid"),
  WordSize("word_size"),
  ExtensionDropoffUngapped("xdrop_ungap"),
  ;

  public static final Map<String, ToolOption> optionsByName = Collections.unmodifiableMap(new HashMap<>(){{
    put("best_hit_overhang", BestHitOverhang);
      put("best_hit_score_edge", BestHitScoreEdge);
      put("db", BlastDatabase);
      put("comp_based_stats", CompositionBasedStats);
      put("culling_limit", CullingLimit);
      put("dbsize", EffectiveDatabaseSize);
      put("db_hard_mask", DatabaseHardMask);
      put("db_soft_mask", DatabaseSoftMask);
      put("db_gencode", DatabaseTranslationGenCode);
      put("dust", Dust);
      put("entrez_query", EntrezQuery);
      put("evalue", ExpectationValue);
      put("export_search_strategy", ExportSearchStrategy);
      put("filtering_db", FilteringDatabasePath);
      put("gapextend", GapCostExtend);
      put("gapopen", GapCostOpen);
      put("gilist", GIListFile);
      put("help", Help);
      put("html", HTMLOutput);
      put("ignore_msa_master", IgnoreMultiSequenceAlignmentMaster);
      put("import_search_strategy", ImportSearchStrategy);
      put("inclusion_ethresh", InclusionEValueThreshold);
      put("in_msa", InputMultiSequenceAlignmentFile);
      put("in_pssm", InputPsiBlastCheckpointFile);
      put("ipglist", IdenticalProteinGroupListFile);
      put("line_length", LineLength);
      put("lcase_masking", LowercaseMasking);
      put("reward", MatchReward);
      put("max_hsps", MaxHSPs);
      put("max_target_seqs", MaxTargetSequences);
      put("index_name", MegablastIndexName);
      put("template_length", DiscontiguousMegablastTemplateLength);
      put("template_type", DiscontiguousMegablastTemplateType);
      put("max_intron_length", MaxIntronLength);
      put("min_raw_gapped_score", MinRawGappedScore);
      put("penalty", MismatchPenalty);
      put("window_size", MultiHitWindowSize);
      put("msa_master_idx", MultiSequenceAlignmentMasterIndex);
      put("negative_gilist", NegativeGIListFile);
      put("negative_ipglist", NegativeIdenticalProteinGroupListFile);
      put("negative_seqidlist", NegativeSequenceIDListFile);
      put("negative_taxids", NegativeTaxonomyIDs);
      put("negative_taxidlist", NegativeTaxonomyIDListFile);
      put("no_greedy", NonGreedyExtension);
      put("num_alignments", NumAlignments);
      put("num_threads", NumberOfThreads);
      put("num_descriptions", NumDescriptions);
      put("num_iterations", NumIterations);
      put("off_diagonal_range", OffDiagonalRange);
      put("out", OutputFile);
      put("out_ascii_pssm", OutAsciiPsiBlastCheckpointFile);
      put("out_pssm", OutPsiBlastCheckpointFile);
      put("outfmt", OutputFormat);
      put("parse_deflines", ParseDefLines);
      put("perc_identity", PercentIdentity);
      put("phi_pattern", PhiBlastPatternFile);
      put("pseudocount", PseudoCount);
      put("query", Query);
      put("qcov_hsp_perc", QueryCoveragePercentHSP);
      put("query_gencode", QueryGeneticCode);
      put("query_loc", QueryLocation);
      put("remote", Remote);
      put("save_each_pssm", SaveEachPsiBlastCheckpoint);
      put("save_pssm_after_last_round", SavePsiBlastCheckpointAfterLastRound);
      put("matrix", ScoringMatrix);
      put("searchsp", SearchSpaceEffectiveLength);
      put("seg", SEGFilter);
      put("seqidlist", SequenceIDListFile);
      put("show_gis", ShowNCBIGIs);
      put("soft_masking", SoftMasking);
      put("sorthits", HSPSorting);
      put("sorthsps", HitSorting);
      put("strand", QueryStrand);
      put("subject_besthit", SubjectBestHit);
      put("subject", SubjectFile);
      put("subject_loc", SubjectLocation);
      put("sum_stats", SumStats);
      put("task", Task);
      put("taxids", TaxonomyIDs);
      put("taxidlist", TaxonomyIDListFile);
      put("threshold", Threshold);
      put("ungapped", UngappedAlignmentOnly);
      put("use_index", UseMegablastIndex);
      put("use_sw_tback", UseSmithWatermanAlignments);
      put("version", Version);
      put("window_masker_db", WindowMaskerDatabasePath);
      put("window_masker_taxid", WindowMaskerTaxonomicID);
      put("word_size", WordSize);
      put("xdrop_gap_final", ExtensionDropoffFinalGapped);
      put("xdrop_gap", ExtensionDropoffPrelimGapped);
      put("xdrop_ungap", ExtensionDropoffUngapped);
  }});

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
    var out = optionsByName.get(value);

    if (out == null) {
      throw new IllegalArgumentException(); // TODO: print actual value
    }

    return out;
  }
}
