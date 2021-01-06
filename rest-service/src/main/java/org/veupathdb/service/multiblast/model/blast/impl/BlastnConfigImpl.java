package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.n.BlastnConfig;
import org.veupathdb.service.multiblast.model.blast.n.BlastnTask;
import org.veupathdb.service.multiblast.model.blast.n.DcTemplateType;
import org.veupathdb.service.multiblast.model.blast.n.Dust;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastnConfigImpl
  extends BlastConfigImpl<BlastnConfig>
  implements BlastnConfig
{
  // Wrapped Options
  private EBestHit      eBestHit;
  private ECullingLimit eCullingLimit;
  private EDbMask       eDbMask;
  private EGapCost      eGapCost;
  private EGapExtDrop   eXGap;
  private ETaxIds       eTaxIds;
  private EUngapped     eUngap;
  private ESubject      eSubject;
  private EWordSize     eWordSize;
  private ESeqIdList    eSeqIdList;
  private ETaxIdList    eTaxIdList;
  private EGiList       eGiList;
  private EStrand       eStrand;
  private ESumStats     eSumStats;


  // Exported options
  private BlastnTask     task;
  private Integer        reward;
  private Integer        penalty;
  private Boolean        useIndex;
  private String         indexName;
  private Dust           dust;
  private Path           filteringDb;
  private Integer        windowMaskerTaxId;
  private Path           windowMaskerDbPath;
  private DcTemplateType dcTemplateType;
  private Byte           dcTemplateLength;
  private boolean        noGreedy;
  private Double         percentIdentity;
  private Integer        minRawGappedScore;
  private Integer        offDiagonalRange;

  @Override
  public Integer getNucleotideMatchReward() {
    return this.reward;
  }

  @Override
  public Integer getNucleotideMismatchPenalty() {
    return this.penalty;
  }

  @Override
  public Integer getCullingLimit() {
    return lazy(eCullingLimit, ECullingLimit::getCullingLimit);
  }

  @Override
  public Double getBestHitOverhang() {
    return lazy(eBestHit, EBestHit::getBestHitOverhang);
  }

  @Override
  public Double getBestHitScoreEdge() {
    return lazy(eBestHit, EBestHit::getBestHitScoreEdge);
  }

  @Override
  public boolean isSubjectBestHitEnabled() {
    return eBestHit != null && eBestHit.isSubjectBestHitEnabled();
  }

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return lazy(eDbMask, EDbMask::getDbSoftMaskAlgorithmId);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(eDbMask, EDbMask::getDbHardMaskAlgorithmId);
  }

  @Override
  public Integer getGapCostOpen() {
    return lazy(eGapCost, EGapCost::getGapCostOpen);
  }

  @Override
  public Integer getGapCostExtend() {
    return lazy(eGapCost, EGapCost::getGapCostExtend);
  }

  @Override
  public Double getExtensionDropoffPreliminaryGapped() {
    return lazy(eXGap, EGapExtDrop::getExtensionDropoffPreliminaryGapped);
  }

  @Override
  public Double getExtensionDropoffFinalGapped() {
    return lazy(eXGap, EGapExtDrop::getExtensionDropoffFinalGapped);
  }

  @Override
  public File getGenInfoIdListFile() {
    return lazy(eGiList, EGiList::getGenInfoIdListFile);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(eGiList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public File getSequenceIdListFile() {
    return lazy(eSeqIdList, ESeqIdList::getSequenceIdListFile);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(eSeqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public QueryStrand getStrand() {
    return lazy(eStrand, EStrand::getStrand);
  }

  @Override
  public File getSubjectFile() {
    return lazy(eSubject, ESubject::getSubjectFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(eSubject, ESubject::getSubjectLocation);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(eTaxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(eTaxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public BlastnTask getTask() {
    return task;
  }

  @Override
  public int[] getTaxIds() {
    return lazy(eTaxIds, ETaxIds::getTaxIds);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(eTaxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public Integer getWordSize() {
    return lazy(eWordSize, EWordSize::getWordSize);
  }

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return eUngap != null && eUngap.isUngappedAlignmentOnlyEnabled();
  }

  @Override
  public String getMegablastDbIndexName() {
    return indexName;
  }

  @Override
  public Dust getDust() {
    return dust;
  }

  @Override
  public Path getFilteringDbPath() {
    return filteringDb;
  }

  @Override
  public Integer getWindowMaskerTaxId() {
    return windowMaskerTaxId;
  }

  @Override
  public Path getWindowMaskerDbPath() {
    return windowMaskerDbPath;
  }

  @Override
  public DcTemplateType getDiscontiguousMegablastTemplateType() {
    return dcTemplateType;
  }

  @Override
  public Byte getDiscontiguousMegablastTemplateLength() {
    return dcTemplateLength;
  }

  @Override
  public boolean isNonGreedyDynamicProgramExtensionEnabled() {
    return noGreedy;
  }

  @Override
  public Double getPercentIdentity() {
    return percentIdentity;
  }

  @Override
  public Integer getMinRawGappedScore() {
    return minRawGappedScore;
  }

  @Override
  public Integer getOffDiagonalRange() {
    return offDiagonalRange;
  }

  @Override
  public BlastnConfig enableMegablastDbIndexUsage(Boolean b) {
    strCache = null;
    useIndex = b;
    return this;
  }

  @Override
  public BlastnConfig enableNonGreedyDynamicProgramExtension(boolean b) {
    strCache = null;
    noGreedy = b;
    return this;
  }

  @Override
  public BlastnConfig enableSubjectBestHit(boolean b) {
    strCache = null;
    (eBestHit = lazy(eBestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public BlastnConfig enableSumStatistics(Boolean b) {
    strCache = null;
    (eSumStats = lazy(eSumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public BlastnConfig enableUngappedAlignmentOnly(boolean b) {
    strCache = null;
    (eUngap = lazy(eUngap, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isMegablastDbIndexUsageEnabled() {
    return useIndex;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(eSumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public BlastnConfig setBestHitOverhang(Double v) {
    strCache = null;
    (eBestHit = lazy(eBestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public BlastnConfig setBestHitScoreEdge(Double v) {
    strCache = null;
    (eBestHit = lazy(eBestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public BlastnConfig setCullingLimit(Integer v) {
    strCache = null;
    (eCullingLimit = lazy(eCullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public BlastnConfig setDbHardMaskAlgorithmId(String id) {
    strCache = null;
    (eDbMask = lazy(eDbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastnConfig setDbSoftMaskAlgorithmId(String id) {
    strCache = null;
    (eDbMask = lazy(eDbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastnConfig setDiscontiguousMegablastTemplateLength(Byte len) {
    strCache = null;
    dcTemplateLength = len;
    return this;
  }

  @Override
  public BlastnConfig setDiscontiguousMegablastTemplateType(DcTemplateType type) {
    if (type != dcTemplateType) {
      strCache = null;
      dcTemplateType = type;
    }
    return this;
  }

  @Override
  public BlastnConfig setDust(Dust d) {
    strCache = null;
    dust = d;
    return this;
  }

  @Override
  public BlastnConfig setFilteringDbPath(Path p) {
    strCache = null;
    filteringDb = p;
    return this;
  }

  @Override
  public BlastnConfig setExtensionDropoffFinalGapped(Double d) {
    strCache = null;
    (eXGap = lazy(eXGap, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public BlastnConfig setGapCostExtend(Integer cost) {
    strCache = null;
    (eGapCost = lazy(eGapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public BlastnConfig setGapCostOpen(Integer cost) {
    strCache = null;
    (eGapCost = lazy(eGapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public BlastnConfig setGenInfoIdListFile(File f) {
    strCache = null;
    (eGiList = lazy(eGiList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setMegablastDbIndexName(String name) {
    strCache = null;
    indexName = name;
    return this;
  }

  @Override
  public BlastnConfig setMinRawGappedScore(Integer i) {
    strCache = null;
    minRawGappedScore = i;
    return this;
  }

  @Override
  public BlastnConfig setNegativeGenInfoIdListFile(File f) {
    strCache = null;
    (eGiList = lazy(eGiList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeSequenceIdListFile(File f) {
    strCache = null;
    (eSeqIdList = lazy(eSeqIdList, ESeqIdList::new)).setNegativeSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIdListFile(File f) {
    strCache = null;
    (eTaxIdList = lazy(eTaxIdList, ETaxIdList::new)).setNegativeTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIds(int[] negativeTaxIds) {
    strCache = null;
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public BlastnConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    strCache = null;
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return null;
  }

  @Override
  public BlastnConfig setNucleotideMatchReward(Integer i) {
    strCache = null;
    reward = i;
    return this;
  }

  @Override
  public BlastnConfig setNucleotideMismatchPenalty(Integer i) {
    strCache = null;
    penalty = i;
    return this;
  }

  @Override
  public BlastnConfig setOffDiagonalRange(Integer val) {
    strCache = null;
    offDiagonalRange = val;
    return this;
  }

  @Override
  public BlastnConfig setPercentIdentity(Double d) {
    strCache = null;
    percentIdentity = d;
    return this;
  }

  @Override
  public BlastnConfig setExtensionDropoffPreliminaryGapped(Double d) {
    strCache = null;
    (eXGap = lazy(eXGap, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public BlastnConfig setSequenceIdListFile(File f) {
    strCache = null;
    (eSeqIdList = lazy(eSeqIdList, ESeqIdList::new)).setSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setStrand(QueryStrand s) {
    strCache = null;
    (eStrand = lazy(eStrand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public BlastnConfig setSubjectFile(File f) {
    strCache = null;
    (eSubject = lazy(eSubject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public BlastnConfig setSubjectLocation(Location loc) {
    strCache = null;
    (eSubject = lazy(eSubject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public BlastnConfig setTask(BlastnTask task) {
    strCache = null;
    this.task = task;
    return this;
  }

  @Override
  public BlastnConfig setTaxIdListFile(File f) {
    strCache = null;
    (eTaxIdList = lazy(eTaxIdList, ETaxIdList::new)).setTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastnConfig setTaxIds(int[] taxIds) {
    strCache = null;
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public BlastnConfig setTaxIds(Collection<Integer> taxIds) {
    strCache = null;
    (eTaxIds = lazy(eTaxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public BlastnConfig setWindowMaskerDbPath(Path p) {
    strCache = null;
    windowMaskerDbPath = p;
    return this;
  }

  @Override
  public BlastnConfig setWindowMaskerTaxId(Integer id) {
    strCache = null;
    windowMaskerTaxId = id;
    return this;
  }

  @Override
  public BlastnConfig setWordSize(Integer size) {
    strCache = null;
    (eWordSize = lazy(eWordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    cli.appendNonNull(ToolOption.Task, task)
      .appendNonNull(ToolOption.MatchReward, reward)
      .appendNonNull(ToolOption.MismatchPenalty, penalty)
      .appendNonNull(ToolOption.UseMegablastIndex, useIndex)
      .appendNonNull(ToolOption.MegablastIndexName, indexName)
      .appendNonNull(ToolOption.Dust, dust)
      .appendNonNull(ToolOption.FilteringDatabasePath, filteringDb)
      .appendNonNull(ToolOption.WindowMaskerTaxonomicID, windowMaskerTaxId)
      .appendNonNull(ToolOption.WindowMaskerDatabasePath, windowMaskerDbPath)
      .appendNonNull(ToolOption.DiscontiguousMegablastTemplateType, dcTemplateLength)
      .appendNonNull(ToolOption.DiscontiguousMegablastTemplateLength, dcTemplateLength)
      .appendNonNull(ToolOption.PercentIdentity, percentIdentity)
      .appendNonNull(ToolOption.MinRawGappedScore, minRawGappedScore)
      .appendNonNull(ToolOption.OffDiagonalRange, offDiagonalRange);

    if (noGreedy)
      cli.append(ToolOption.NonGreedyExtension);

    lazy(
      cli,
      eBestHit,
      eCullingLimit,
      eDbMask,
      eGapCost,
      eXGap,
      eTaxIds,
      eUngap,
      eSubject,
      eWordSize,
      eSeqIdList,
      eTaxIdList,
      eGiList,
      eStrand
    );
  }
}
