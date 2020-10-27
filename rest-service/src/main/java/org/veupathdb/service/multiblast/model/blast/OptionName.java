package org.veupathdb.service.multiblast.model.blast;

import javax.swing.text.html.HTML;

public final class OptionName
{
  public static final String
    BEST_HIT_OVERHANG      = "-best_hit_overhang",
    BEST_HIT_SCORE_EDGE    = "-best_hit_score_edge",
    CULLING_LIMIT          = "-culling_limit",
    DB                     = "-db",
    DB_HARD_MASK           = "-db_hard_mask",
    DB_SIZE                = "-dbsize",
    DB_SOFT_MASK           = "-db_soft_mask",
    DUST                   = "-dust",
    ENTREZ_QUERY           = "-entrez_query",
    EXPORT_SEARCH_STRATEGY = "-export_search_strategy",
    E_VALUE                = "-evalue",
    FILTERING_DB           = "-filtering_db",
    GAP_EXTEND             = "-gapextend",
    GAP_OPEN               = "-gapopen",
    GI_LIST                = "-gilist",
    HELP                   = "-help",
    HELP_SHORT             = "-h",
    IMPORT_SEARCH_STRATEGY = "-import_search_strategy",
    INDEX_NAME             = "-index_name",
    LCASE_MASKING          = "-lcase_masking",
    LINE_LENGTH            = "-line_length",
    MAX_HSPS               = "-max_hsps",
    MAX_TARGET_SEQS        = "-max_target_seqs",
    MIN_RAW_GAPPED_SCORE   = "-min_raw_gapped_score",
    NEGATIVE_GI_LIST       = "-negative_gilist",
    NEGATIVE_SEQ_ID_LIST   = "-negative_seqidlist",
    NEGATIVE_TAX_IDS       = "-negative_taxids",
    NEGATIVE_TAX_ID_LIST   = "-negative_taxidlist",
    NO_GREEDY              = "-no_greedy",
    NUM_ALIGNMENTS         = "-num_alignments",
    NUM_DESCRIPTIONS       = "-num_descriptions",
    NUM_THREADS            = "-num_threads",
    OFF_DIAGONAL_RANGE     = "-off_diagonal_range",
    OUT                    = "-out",
    OUT_FORMAT             = "-outfmt",
    PARSE_DEFLINES         = "-parse_deflines",
    PENALTY                = "-penalty",
    PERCENT_IDENTITY       = "-perc_identity",
    QUERY                  = "-query",
    QUERY_COV_HSP_PERCENT  = "-qcov_hsp_perc",
    QUERY_LOCATION         = "-query_loc",
    REMOTE                 = "-remote",
    REWARD                 = "-reward",
    SEARCH_HSP             = "-searchhsp",
    SEQ_ID_LIST            = "-seqidlist",
    SHOW_GIS               = "-show_gis",
    SOFT_MASKING           = "-soft_masking",
    SORT_HITS              = "-sorthits",
    SORT_HSPS              = "-sorthsps",
    STRAND                 = "-strand",
    SUBJECT                = "-subject",
    SUBJECT_BEST_HIT       = "-subject_besthit",
    SUBJECT_LOCATION       = "-subject_loc",
    SUM_STATS              = "-sum_stats",
    TASK                   = "-task",
    TAX_IDS                = "-taxids",
    TAX_ID_LIST            = "-taxidlist",
    TEMPLATE_LENGTH        = "-template_length",
    TEMPLATE_TYPE          = "-template_type",
    UNGAPPED               = "-ungapped",
    USE_INDEX              = "-use_index",
    VERSION                = "-version",
    WINDOW_MASKER_DB       = "-window_masker_db",
    WINDOW_MASKER_TAX_ID   = "-window_masker_taxid",
    WINDOW_SIZE            = "-window_size",
    WORD_SIZE              = "-word_size",
    XDROP_GAP              = "-xdrop_gap",
    XDROP_GAP_FINAL        = "-xdrop_gap_final",
    XDROP_UNGAP            = "-xdrop_ungap"
  ;

  public static String toFlag(String name) {
    return "-" + name;
  }

  private OptionName() {
  }
}
