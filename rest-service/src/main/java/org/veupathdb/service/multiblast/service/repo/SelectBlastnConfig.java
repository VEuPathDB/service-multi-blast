package org.veupathdb.service.multiblast.service.repo;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import io.vulpine.lib.query.util.StatementPreparer;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.impl.BlastnConfigImpl;
import org.veupathdb.service.multiblast.model.blast.impl.LocationImpl;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.n.BlastnTask;
import org.veupathdb.service.multiblast.model.blast.n.DcTemplateType;

import static org.veupathdb.service.multiblast.model.blast.ToolOption.*;

public class SelectBlastnConfig extends BlastSelector<BlastnConfig>
{
  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Static Configuration                                             ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private static final Map<ToolOption, Setter<BlastnConfig, ?>> setters = new HashMap<>()
  {{
    put(Task, new Setter<>(BlastnConfig::setTask, BlastnTask::fromString));
    put(TaxonomyIDs, new Setter<>(BlastnConfig::setTaxIds, BlastSelector::stringToIntArray));
    put(
      NegativeTaxonomyIDs,
      new Setter<>(BlastnConfig::setNegativeTaxIds, BlastSelector::stringToIntArray)
    );
    put(GIListFile, new Setter<>(BlastnConfig::setGenInfoIdListFile, pFile));
    put(NegativeGIListFile, new Setter<>(BlastnConfig::setNegativeGenInfoIdListFile, pFile));
    put(SequenceIDListFile, new Setter<>(BlastnConfig::setSequenceIdListFile, pFile));
    put(
      NegativeSequenceIDListFile,
      new Setter<>(BlastnConfig::setNegativeSequenceIdListFile, pFile)
    );
    put(TaxonomyIDListFile, new Setter<>(BlastnConfig::setTaxIdListFile, pFile));
    put(NegativeTaxonomyIDListFile, new Setter<>(BlastnConfig::setNegativeTaxIdListFile, pFile));
    put(
      QueryStrand,
      new Setter<>(
        BlastnConfig::setStrand,
        org.veupathdb.service.multiblast.model.blast.QueryStrand::fromString
      )
    );
    put(WordSize, new Setter<>(BlastnConfig::setWordSize, pInt));
    put(GapCostOpen, new Setter<>(BlastnConfig::setGapCostOpen, pInt));
    put(GapCostExtend, new Setter<>(BlastnConfig::setGapCostExtend, pInt));
    put(MismatchPenalty, new Setter<>(BlastnConfig::setNucleotideMismatchPenalty, pInt));
    put(MatchReward, new Setter<>(BlastnConfig::setNucleotideMatchReward, pInt));
    put(UseMegablastIndex, new Setter<>(BlastnConfig::enableMegablastDbIndexUsage, pBool));
    put(MegablastIndexName, new Setter<>(BlastnConfig::setMegablastDbIndexName, pStr));
    put(
      Dust,
      new Setter<>(
        BlastnConfig::setDust,
        org.veupathdb.service.multiblast.model.blast.impl.DustImpl::fromString
      )
    );
    put(FilteringDatabasePath, new Setter<>(BlastnConfig::setFilteringDbPath, Path::of));
    put(WindowMaskerTaxonomicID, new Setter<>(BlastnConfig::setWindowMaskerTaxId, pInt));
    put(WindowMaskerDatabasePath, new Setter<>(BlastnConfig::setWindowMaskerDbPath, Path::of));
    put(PercentIdentity, new Setter<>(BlastnConfig::setPercentIdentity, pDub));
    put(CullingLimit, new Setter<>(BlastnConfig::setCullingLimit, pInt));
    put(BestHitOverhang, new Setter<>(BlastnConfig::setBestHitOverhang, pDub));
    put(BestHitScoreEdge, new Setter<>(BlastnConfig::setBestHitScoreEdge, pDub));
    put(SubjectBestHit, new Setter<>(BlastnConfig::enableSubjectBestHit, pBool));
    put(
      DiscontiguousMegablastTemplateType,
      new Setter<>(
        BlastnConfig::setDiscontiguousMegablastTemplateType,
        DcTemplateType::unsafeFromString
      )
    );
    put(
      DiscontiguousMegablastTemplateLength,
      new Setter<>(BlastnConfig::setDiscontiguousMegablastTemplateLength, pByte)
    );
    put(SumStats, new Setter<>(BlastnConfig::enableSumStatistics, pBool));
    put(SubjectFile, new Setter<>(BlastnConfig::setSubjectFile, pFile));
    put(SubjectLocation, new Setter<>(BlastnConfig::setSubjectLocation, LocationImpl::fromString));
    put(ExtensionDropoffPrelimGapped, new Setter<>(BlastnConfig::setExtensionDropoffPreliminaryGapped, pDub));
    put(ExtensionDropoffFinalGapped, new Setter<>(BlastnConfig::setExtensionDropoffFinalGapped, pDub));
    put(UngappedAlignmentOnly, new Setter<>(BlastnConfig::enableUngappedAlignmentOnly, pBool));
    put(
      NonGreedyExtension,
      new Setter<>(BlastnConfig::enableNonGreedyDynamicProgramExtension, pBool)
    );
    put(MinRawGappedScore, new Setter<>(BlastnConfig::setMinRawGappedScore, pInt));
    put(OffDiagonalRange, new Setter<>(BlastnConfig::setOffDiagonalRange, pInt));
    put(DatabaseHardMask, new Setter<>(BlastnConfig::setDbHardMaskAlgorithmId, pStr));
    put(DatabaseSoftMask, new Setter<>(BlastnConfig::setDbSoftMaskAlgorithmId, pStr));
  }};

  // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ //
  // ┃                                                                      ┃ //
  // ┃     Instance Methods                                                 ┃ //
  // ┃                                                                      ┃ //
  // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ //

  private final int jobId;

  public SelectBlastnConfig(int jobId) {
    this.jobId = jobId;
  }

  public BlastnConfig execute() throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.Job.ConfigByID,
      Util::getPgConnection,
      this::parseBlastConfig,
      StatementPreparer.singleInt(jobId)
    ).execute().getValue();
  }

  @Override
  protected BlastnConfig newBlastConfig() {
    return new BlastnConfigImpl();
  }

  @Override
  protected void parseBlastConfig(BlastnConfig out, ToolOption key, String val) {
    var set = setters.get(key);

    if (set == null)
      super.parseBlastConfig(out, key, val);
    else
      set.set(out, val);
  }
}
