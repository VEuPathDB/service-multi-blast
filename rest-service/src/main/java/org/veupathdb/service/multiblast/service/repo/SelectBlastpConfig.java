package org.veupathdb.service.multiblast.service.repo;

import java.util.HashMap;
import java.util.Map;

import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.impl.BlastpConfigImpl;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.impl.SegImpl;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.p.BlastpTask;

import static org.veupathdb.service.multiblast.model.blast.ToolOption.*;

public class SelectBlastpConfig extends BlastSelector<BlastpConfig>
{
  private static final Map<ToolOption, Setter<BlastpConfig, ?>> setters = new HashMap<>()
  {{
    put(Task, new Setter<>(BlastpConfig::setTask, BlastpTask::fromString));
    put(WordSize, new Setter<>(BlastpConfig::setWordSize, pInt));
    put(GapCostOpen, new Setter<>(BlastpConfig::setGapCostOpen, pInt));
    put(GapCostExtend, new Setter<>(BlastpConfig::setGapCostExtend, pInt));
    put(
      ScoringMatrix,
      new Setter<>(BlastpConfig::setScoringMatrix, BlastpScoringMatrix::fromString)
    );
    put(Threshold, new Setter<>(BlastpConfig::setScoreThreshold, pDub));
    put(
      CompositionBasedStats,
      new Setter<>(BlastpConfig::setCompBasedStatisticsType, CompBasedStats::fromValue)
    );
    put(SubjectFile, new Setter<>(BlastpConfig::setSubjectFile, pFile));
    put(SubjectLocation, new Setter<>(BlastpConfig::setSubjectLocation, LocationImpl::fromString));
    put(SEGFilter, new Setter<>(BlastpConfig::setSeg, SegImpl::fromString));
    put(GIListFile, new Setter<>(BlastpConfig::setGenInfoIdListFile, pFile));
    put(NegativeGIListFile, new Setter<>(BlastpConfig::setNegativeGenInfoIdListFile, pFile));
    put(SequenceIDListFile, new Setter<>(BlastpConfig::setSequenceIdListFile, pFile));
    put(
      NegativeSequenceIDListFile,
      new Setter<>(BlastpConfig::setNegativeSequenceIdListFile, pFile)
    );
    put(TaxonomyIDListFile, new Setter<>(BlastpConfig::setTaxIdListFile, pFile));
    put(NegativeTaxonomyIDListFile, new Setter<>(BlastpConfig::setNegativeTaxIdListFile, pFile));
    put(TaxonomyIDs, new Setter<>(BlastpConfig::setTaxIds, BlastSelector::stringToIntArray));
    put(
      NegativeTaxonomyIDs,
      new Setter<>(BlastpConfig::setNegativeTaxIds, BlastSelector::stringToIntArray)
    );
    put(
      IdenticalProteinGroupListFile,
      new Setter<>(BlastpConfig::setIdenticalProteinGroupListFile, pFile)
    );
    put(
      NegativeIdenticalProteinGroupListFile,
      new Setter<>(BlastpConfig::setNegativeIdenticalProteinGroupListFile, pFile)
    );
    put(DatabaseSoftMask, new Setter<>(BlastpConfig::setDbSoftMaskAlgorithmId, pStr));
    put(DatabaseHardMask, new Setter<>(BlastpConfig::setDbHardMaskAlgorithmId, pStr));
    put(CullingLimit, new Setter<>(BlastpConfig::setCullingLimit, pInt));
    put(BestHitOverhang, new Setter<>(BlastpConfig::setBestHitOverhang, pDub));
    put(BestHitScoreEdge, new Setter<>(BlastpConfig::setBestHitScoreEdge, pDub));
    put(SubjectBestHit, new Setter<>(BlastpConfig::enableSubjectBestHit, pBool));
    put(
      ExtensionDropoffPrelimGapped,
      new Setter<>(BlastpConfig::setExtensionDropoffPreliminaryGapped, pDub)
    );
    put(
      ExtensionDropoffFinalGapped,
      new Setter<>(BlastpConfig::setExtensionDropoffFinalGapped, pDub)
    );
    put(UngappedAlignmentOnly, new Setter<>(BlastpConfig::enableUngappedAlignmentOnly, pBool));
    put(
      UseSmithWatermanAlignments,
      new Setter<>(BlastpConfig::enableSmithWatermanTraceback, pBool)
    );
  }};

  @Override
  protected void parseBlastConfig(BlastpConfig out, ToolOption key, String value) {
    var hand = setters.get(key);

    if (hand == null)
      super.parseBlastConfig(out, key, value);
    else
      hand.set(out, value);
  }

  @Override
  protected BlastpConfig newBlastConfig() {
    return new BlastpConfigImpl();
  }
}
