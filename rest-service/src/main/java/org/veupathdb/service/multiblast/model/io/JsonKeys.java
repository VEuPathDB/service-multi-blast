package org.veupathdb.service.multiblast.model.io;

public final class JsonKeys
{
  public static final String
    BEST_HIT_OVERHANG          = "bestHitOverhang",
    BEST_HIT_SCORE_EDGE        = "bestHitScoreEdge",
    COMPOSITION_BASED_STATS    = "compBasedStats",
    CONFIG                     = "config",
    CULLING_LIMIT              = "cullingLimit",
    DB                         = "db",
    DB_GENETIC_CODE            = "dbGenCode",
    DB_HARD_MASK               = "dbHardMask",
    DB_SIZE                    = "dbSize",
    DB_SOFT_MASK               = "dbSoftMask",
    DELIMITER                  = "delim",
    DUST                       = "dust",
    E_VALUE                    = "eValue",
    ENABLED                    = "enabled",
    FIELDS                     = "fields",
    FILTERING_DB               = "filteringDb",
    FORMAT                     = "format",
    GAP_EXTEND                 = "gapExtend",
    GAP_OPEN                   = "gapOpen",
    GI_LIST                    = "giList",
    HIGH_CUT                   = "hicut",
    INDEX_NAME                 = "indexName",
    LEVEL                      = "level",
    LINE_LENGTH                = "lineLength",
    LINKER                     = "linker",
    LOWERCASE_MASKING          = "lcaseMasking",
    LOW_CUT                    = "locut",
    MATRIX                     = "matrix",
    MAX_HSPS                   = "maxHSPs",
    MAX_INTRON_LENGTH          = "maxIntronLength",
    MAX_TARGET_SEQS            = "maxTargetSeqs",
    MIN_RAW_GAPPED_SCORE       = "minRawGappedScore",
    NEGATIVE_GI_LIST           = "negativeGiList",
    NEGATIVE_SEQ_ID_LIST       = "negativeSeqIdList",
    NEGATIVE_TAX_ID_LIST       = "negativeTaxIdList",
    NEGATIVE_TAX_IDS           = "negativeTaxIds",
    NO_GREEDY                  = "noGreedy",
    NUM_ALIGNMENTS             = "numAlignments",
    NUM_DESCRIPTIONS           = "numDescriptions",
    ORGANISM                   = "organism",
    OUT_FMT                    = "outFmt",
    PARSE_DEF_LINES            = "parseDefLines",
    PENALTY                    = "penalty",
    PERCENT_IDENTITY           = "percIdentity",
    QUERY                      = "query",
    QUERY_COVERAGE_HSP_PERCENT = "qCovHSPPerc",
    QUERY_GENETIC_CODE         = "queryGeneticCode",
    QUERY_LOC                  = "queryLoc",
    REMOTE                     = "remote",
    REWARD                     = "reward",
    SEARCH_SPACE               = "searchSpace",
    SEG                        = "seg",
    SEQ_ID_LIST                = "seqIdList",
    SOFT_MASKING               = "softMasking",
    SORT_HITS                  = "sortHits",
    SORT_HSPS                  = "sortHSPs",
    START                      = "start",
    STOP                       = "stop",
    STRAND                     = "strand",
    SUBJECT                    = "subject",
    SUBJECT_LOC                = "subjectLoc",
    TARGET_TYPE                = "target-type",
    TARGETS                    = "targets",
    TASK                       = "task",
    TAX_ID_LIST                = "taxIdList",
    TAX_IDS                    = "taxIds",
    TEMPLATE_LENGTH            = "templateLength",
    TEMPLATE_TYPE              = "templateType",
    THRESHOLD                  = "threshold",
    TOOL                       = "tool",
    UNGAPPED                   = "ungapped",
    USE_INDEX                  = "useIndex",
    USE_SMITH_WATERMAN_ALIGNS  = "useSwTBack",
    WINDOW                     = "window",
    WINDOW_MASKER_DB           = "windowMaskerDb",
    WINDOW_MASKER_TAX_ID       = "windowMaskerTaxid",
    WINDOW_SIZE                = "windowSize",
    WORD_SIZE                  = "wordSize",
    XDROP_GAP                  = "xdropGap",
    XDROP_GAP_FINAL            = "xdropGapFinal",
    XDROP_UNGAP                = "xdropUngap";

  private JsonKeys() {
  }
}
