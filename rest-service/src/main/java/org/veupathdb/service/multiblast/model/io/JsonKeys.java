package org.veupathdb.service.multiblast.model.io;

public final class JsonKeys
{
  public static final String
    BestHitOverhang       = "bestHitOverhang",
    BestHitScoreEdge      = "bestHitScoreEdge",
    CompositionBasedStats = "compBasedStats",
    Config                = "config",
    CullingLimit          = "cullingLimit",
    BlastDatabase         = "db",
    DBGeneticCode         = "dbGenCode",
    DBHardMask            = "dbHardMask",
    DB_SIZE                            = "dbSize",
    DBSoftMask                         = "dbSoftMask",
    Delimiter                          = "delim",
    DUST                               = "dust",
    ExpectValue                        = "eValue",
    ENABLED                            = "enabled",
    EntrezQuery                        = "entrezQuery",
    ExportSearchStrategy               = "exportSearchStrategy",
    Fields                             = "fields",
    FILTERING_DB                       = "filteringDb",
    Format                             = "format",
    GapExtend                          = "gapExtend",
    GapOpen                            = "gapOpen",
    GI_LIST                            = "giList",
    Help                               = "help",
    HighCut                            = "hicut",
    Html                               = "html",
    IgnoreMultiSequenceAlignmentMaster = "ignoreMsaMaster",
    ImportSearchStrategy               = "importSearchStrategy",
    InclusionEValueThreshold           = "inclusionEThresh",
    INDEX_NAME                         = "indexName",
    InputMultiSequenceAlignment        = "inMsa",
    InputPsiBlastCheckpointFile        = "inPssm",
    IPGList                            = "ipgList",
    LEVEL                              = "level",
    LineLength                         = "lineLength",
    LINKER                            = "linker",
    LOWERCASE_MASKING                 = "lcaseMasking",
    LowCut                            = "locut",
    Matrix                            = "matrix",
    MaxHSPs                           = "maxHSPs",
    MaxIntronLength                   = "maxIntronLength",
    MaxTargetSequences                = "maxTargetSeqs",
    MIN_RAW_GAPPED_SCORE              = "minRawGappedScore",
    MultiSequenceAlignmentMasterIndex = "msaMasterIndex",
    NEGATIVE_GI_LIST                  = "negativeGiList",
    NEGATIVE_SEQ_ID_LIST              = "negativeSeqIdList",
    NEGATIVE_TAX_ID_LIST           = "negativeTaxIdList",
    NegativeTaxIDs                 = "negativeTaxIds",
    NegativeIPGList                = "negativeIpgList",
    NonGreedy                      = "noGreedy",
    NumAlignments                  = "numAlignments",
    NumDescriptions                = "numDescriptions",
    NumIterations                  = "numIterations",
    NumThreads                     = "numThreads",
    OffDiagonalRange               = "offDiagonalRange",
    ORGANISM                       = "organism",
    OutFormat                      = "outFmt",
    OutAsciiPsiBlastCheckpointFile = "outAsciiPssm",
    OutPsiBlastCheckpointFile      = "outPssm",
    OutputFile                     = "out",
    PARSE_DEF_LINES                = "parseDefLines",
    Penalty                        = "penalty",
    PercentIdentity                = "percIdentity",
    PhiBlastPatternFile            = "phiPattern",
    PseudoCount                    = "pseudoCount",
    Query                          = "query",
    QueryCoverageHSPPercent        = "qCovHSPPerc",
    QueryGeneticCode                     = "queryGeneticCode",
    QueryLocation                        = "queryLoc",
    REMOTE                               = "remote",
    Reward                               = "reward",
    SaveEachPsiBlastCheckpoint           = "saveEachPssm",
    SavePsiBlastCheckpointAfterLastRound = "savePssmAfterLastRound",
    SearchSpace                          = "searchSpace",
    Seg                                  = "seg",
    SEQ_ID_LIST                          = "seqIdList",
    ShowNCBIGIs                          = "showGIs",
    SoftMasking                          = "softMasking",
    SortHits                             = "sortHits",
    SortHSPs                  = "sortHSPs",
    Start                     = "start",
    Stop                      = "stop",
    STRAND                    = "strand",
    SUBJECT                   = "subject",
    SubjectBestHit            = "subjectBestHit",
    SubjectLocation           = "subjectLoc",
    SumStats                  = "sumStats",
    TARGET_TYPE               = "target-type",
    TARGETS                   = "targets",
    Task                      = "task",
    TAX_ID_LIST               = "taxIdList",
    TaxIDs                    = "taxIds",
    TemplateLength            = "templateLength",
    TemplateType              = "templateType",
    Threshold                 = "threshold",
    TOOL                      = "tool",
    UNGAPPED                  = "ungapped",
    USE_INDEX                 = "useIndex",
    USE_SMITH_WATERMAN_ALIGNS = "useSwTBack",
    Version                   = "version",
    Window                    = "window",
    WINDOW_MASKER_DB          = "windowMaskerDb",
    WINDOW_MASKER_TAX_ID      = "windowMaskerTaxid",
    MultiHitWindowSize        = "windowSize",
    WordSize                  = "wordSize",
    XDROP_GAP                 = "xdropGap",
    XDROP_GAP_FINAL           = "xdropGapFinal",
    XDROP_UNGAP               = "xdropUngap";

  private JsonKeys() {
  }
}
