package org.veupathdb.service.multiblast.service.valid;

import java.util.HashMap;
import java.util.Map;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

class Util
{
  private static Util instance;

  private final Map<ToolOption, String> mapping;

  private Util() {
  }

  public String toolOptionToJsonKey(ToolOption opt) {
    return mapping.get(opt);
  }

  public String getKey(ToolOption opt, boolean external) {
    return external ? toolOptionToJsonKey(opt) : opt.toString();
  }

  public static String key(ToolOption opt, boolean external) {
    return getInstance().getKey(opt, external);
  }

  public static Util getInstance() {
    if (instance == null)
      instance = new Util();

    return instance;
  }

  {
    mapping = new HashMap<>();
    mapping.put(ToolOption.BestHitOverhang, JsonKeys.BestHitOverhang);
    mapping.put(ToolOption.BestHitScoreEdge, JsonKeys.BestHitScoreEdge);
    mapping.put(ToolOption.BlastDatabase, JsonKeys.BlastDatabase);
    mapping.put(ToolOption.CompositionBasedStats, JsonKeys.CompositionBasedStats);
    mapping.put(ToolOption.CullingLimit, JsonKeys.CullingLimit);
    mapping.put(ToolOption.EffectiveDatabaseSize, JsonKeys.DB_SIZE);
    mapping.put(ToolOption.DatabaseHardMask, JsonKeys.DBHardMask);
    mapping.put(ToolOption.DatabaseSoftMask, JsonKeys.SoftMasking);
    mapping.put(ToolOption.Dust, JsonKeys.DUST);
    mapping.put(ToolOption.EntrezQuery, JsonKeys.EntrezQuery);
    mapping.put(ToolOption.ExpectValue, JsonKeys.ExpectValue);
    mapping.put(ToolOption.ExportSearchStrategy, JsonKeys.ExportSearchStrategy);
    mapping.put(ToolOption.FilteringDatabasePath, JsonKeys.FILTERING_DB);
    mapping.put(ToolOption.GapCostExtend, JsonKeys.GapExtend);
    mapping.put(ToolOption.GapCostOpen, JsonKeys.GapOpen);
    mapping.put(ToolOption.GIListFile, JsonKeys.GI_LIST);
    mapping.put(ToolOption.Help, JsonKeys.Help);
    mapping.put(ToolOption.HTMLOutput, JsonKeys.Html);
    mapping.put(
      ToolOption.IgnoreMultiSequenceAlignmentMaster,
      JsonKeys.IgnoreMultiSequenceAlignmentMaster
    );
    mapping.put(ToolOption.ImportSearchStrategy, JsonKeys.ImportSearchStrategy);
    mapping.put(ToolOption.InclusionEValueThreshold, JsonKeys.InclusionEValueThreshold);
    mapping.put(ToolOption.InputMultiSequenceAlignmentFile, JsonKeys.InputMultiSequenceAlignment);
    mapping.put(ToolOption.InputPsiBlastCheckpointFile, JsonKeys.InputPsiBlastCheckpointFile);
    mapping.put(ToolOption.IdenticalProteinGroupListFile, JsonKeys.IPGList);
    mapping.put(ToolOption.LineLength, JsonKeys.LineLength);
    mapping.put(ToolOption.LowercaseMasking, JsonKeys.LOWERCASE_MASKING);
    mapping.put(ToolOption.MatchReward, JsonKeys.Reward);
    mapping.put(ToolOption.MaxHSPs, JsonKeys.MaxHSPs);
    mapping.put(ToolOption.MaxTargetSequences, JsonKeys.MaxTargetSequences);
    mapping.put(ToolOption.MegablastIndexName, JsonKeys.INDEX_NAME);
    mapping.put(ToolOption.DiscontiguousMegablastTemplateLength, JsonKeys.TemplateLength);
    mapping.put(ToolOption.DiscontiguousMegablastTemplateType, JsonKeys.TemplateType);
    mapping.put(ToolOption.MaxIntronLength, JsonKeys.MaxIntronLength);
    mapping.put(ToolOption.MinRawGappedScore, JsonKeys.MIN_RAW_GAPPED_SCORE);
    mapping.put(ToolOption.MismatchPenalty, JsonKeys.Penalty);
    mapping.put(ToolOption.MultiHitWindowSize, JsonKeys.MultiHitWindowSize);
    mapping.put(
      ToolOption.MultiSequenceAlignmentMasterIndex,
      JsonKeys.MultiSequenceAlignmentMasterIndex
    );
    mapping.put(ToolOption.NegativeGIListFile, JsonKeys.NEGATIVE_GI_LIST);
    mapping.put(ToolOption.NegativeIdenticalProteinGroupListFile, JsonKeys.NegativeIPGList);
    mapping.put(ToolOption.NegativeSequenceIDListFile, JsonKeys.NEGATIVE_SEQ_ID_LIST);
    mapping.put(ToolOption.NegativeTaxonomyIDs, JsonKeys.NegativeTaxIDs);
    mapping.put(ToolOption.NegativeTaxonomyIDListFile, JsonKeys.NEGATIVE_TAX_ID_LIST);
    mapping.put(ToolOption.NonGreedyExtension, JsonKeys.NonGreedy);
    mapping.put(ToolOption.NumAlignments, JsonKeys.NumAlignments);
    mapping.put(ToolOption.NumberOfThreads, JsonKeys.NumThreads);
    mapping.put(ToolOption.NumDescriptions, JsonKeys.NumDescriptions);
    mapping.put(ToolOption.NumIterations, JsonKeys.NumIterations);
    mapping.put(ToolOption.OffDiagonalRange, JsonKeys.OffDiagonalRange);
    mapping.put(ToolOption.OutputFile, JsonKeys.OutputFile);
    mapping.put(ToolOption.OutAsciiPsiBlastCheckpointFile, JsonKeys.OutAsciiPsiBlastCheckpointFile);
    mapping.put(ToolOption.OutPsiBlastCheckpointFile, JsonKeys.OutPsiBlastCheckpointFile);
    mapping.put(ToolOption.OutputFormat, JsonKeys.OutFormat);
    mapping.put(ToolOption.ParseDefLines, JsonKeys.PARSE_DEF_LINES);
    mapping.put(ToolOption.PercentIdentity, JsonKeys.PercentIdentity);
    mapping.put(ToolOption.PhiBlastPatternFile, JsonKeys.PhiBlastPatternFile);
    mapping.put(ToolOption.PseudoCount, JsonKeys.PseudoCount);
    mapping.put(ToolOption.Query, JsonKeys.Query);
    mapping.put(ToolOption.QueryCoveragePercentHSP, JsonKeys.QueryCoverageHSPPercent);
    mapping.put(ToolOption.QueryGeneticCode, JsonKeys.QueryGeneticCode);
    mapping.put(ToolOption.QueryLocation, JsonKeys.QueryLocation);
    mapping.put(ToolOption.Remote, JsonKeys.REMOTE);
    mapping.put(ToolOption.SaveEachPsiBlastCheckpoint, JsonKeys.SaveEachPsiBlastCheckpoint);
    mapping.put(
      ToolOption.SavePsiBlastCheckpointAfterLastRound,
      JsonKeys.SavePsiBlastCheckpointAfterLastRound
    );
    mapping.put(ToolOption.ScoringMatrix, JsonKeys.Matrix);
    mapping.put(ToolOption.SearchSpaceEffectiveLength, JsonKeys.SearchSpace);
    mapping.put(ToolOption.SEGFilter, JsonKeys.Seg);
    mapping.put(ToolOption.SequenceIDListFile, JsonKeys.SEQ_ID_LIST);
    mapping.put(ToolOption.ShowNCBIGIs, JsonKeys.ShowNCBIGIs);
    mapping.put(ToolOption.SoftMasking, JsonKeys.SoftMasking);
    mapping.put(ToolOption.HSPSorting, JsonKeys.SortHits);
    mapping.put(ToolOption.HitSorting, JsonKeys.SortHSPs);
    mapping.put(ToolOption.QueryStrand, JsonKeys.STRAND);
    mapping.put(ToolOption.SubjectBestHit, JsonKeys.SubjectBestHit);
    mapping.put(ToolOption.SubjectFile, JsonKeys.SUBJECT);
    mapping.put(ToolOption.SubjectLocation, JsonKeys.SubjectLocation);
    mapping.put(ToolOption.SumStats, JsonKeys.SumStats);
    mapping.put(ToolOption.Task, JsonKeys.Task);
    mapping.put(ToolOption.TaxonomyIDs, JsonKeys.TaxIDs);
    mapping.put(ToolOption.TaxonomyIDListFile, JsonKeys.TAX_ID_LIST);
    mapping.put(ToolOption.Threshold, JsonKeys.Threshold);
    mapping.put(ToolOption.UngappedAlignmentOnly, JsonKeys.UNGAPPED);
    mapping.put(ToolOption.UseMegablastIndex, JsonKeys.USE_INDEX);
    mapping.put(ToolOption.UseSmithWatermanAlignments, JsonKeys.USE_SMITH_WATERMAN_ALIGNS);
    mapping.put(ToolOption.Version, JsonKeys.Version);
    mapping.put(ToolOption.WindowMaskerDatabasePath, JsonKeys.WINDOW_MASKER_DB);
    mapping.put(ToolOption.WindowMaskerTaxonomicID, JsonKeys.WINDOW_MASKER_TAX_ID);
    mapping.put(ToolOption.WordSize, JsonKeys.WordSize);
    mapping.put(ToolOption.ExtensionDropoffFinalGapped, JsonKeys.XDROP_GAP_FINAL);
    mapping.put(ToolOption.ExtensionDropoffPrelimGapped, JsonKeys.XDROP_GAP);
    mapping.put(ToolOption.ExtensionDropoffUngapped, JsonKeys.XDROP_UNGAP);
  }
}
