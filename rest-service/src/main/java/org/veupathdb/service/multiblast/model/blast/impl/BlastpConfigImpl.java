package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.p.BlastpConfig;
import org.veupathdb.service.multiblast.model.blast.p.BlastpScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.p.BlastpTask;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastpConfigImpl
  extends BlastConfigImpl<BlastpConfig>
  implements BlastpConfig
{
  private EWordSize wordSize;
  private EGapCost gapCost;
  private EScoringMatrix<BlastpScoringMatrix> scoringMatrix;
  private ECompBasedStats compBasedStats;
  private ESubject subject;
  private ESeg seg;
  private EGiList giList;
  private ESeqIdList seqIdList;
  private ETaxIdList taxIdList;
  private ETaxIds taxIds;
  private EIpgList ipgList;
  private EDbMask dbMask;
  private ECullingLimit cullingLimit;
  private EBestHit bestHit;
  private EGapExtDrop xDrop;
  private EUngapped ungapped;
  private ESWTback sw;

  private BlastpTask task;

  @Override
  public BlastpTask getTask() {
    return task;
  }

  @Override
  public Double getBestHitOverhang() {
    return lazy(bestHit, EBestHit::getBestHitOverhang);
  }

  @Override
  public Double getBestHitScoreEdge() {
    return lazy(bestHit, EBestHit::getBestHitScoreEdge);
  }

  @Override
  public boolean isSubjectBestHitEnabled() {
    return bestHit != null && bestHit.isSubjectBestHitEnabled();
  }

  @Override
  public CompBasedStats getCompBasedStatisticsType() {
    return lazy(compBasedStats, ECompBasedStats::getCompBasedStatisticsType);
  }

  @Override
  public Integer getCullingLimit() {
    return lazy(cullingLimit, ECullingLimit::getCullingLimit);
  }

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbSoftMaskAlgorithmId);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbHardMaskAlgorithmId);
  }

  @Override
  public Integer getGapCostOpen() {
    return lazy(gapCost, EGapCost::getGapCostOpen);
  }

  @Override
  public Integer getGapCostExtend() {
    return lazy(gapCost, EGapCost::getGapCostExtend);
  }

  @Override
  public Double getExtensionDropoffPreliminaryGapped() {
    return lazy(xDrop, EGapExtDrop::getExtensionDropoffPreliminaryGapped);
  }

  @Override
  public Double getExtensionDropoffFinalGapped() {
    return lazy(xDrop, EGapExtDrop::getExtensionDropoffFinalGapped);
  }

  @Override
  public File getGenInfoIdListFile() {
    return lazy(giList, EGiList::getGenInfoIdListFile);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(giList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public File getIdenticalProteinGroupListFile() {
    return lazy(ipgList, EIpgList::getIdenticalProteinGroupListFile);
  }

  @Override
  public File getNegativeIdenticalProteinGroupListFile() {
    return lazy(ipgList, EIpgList::getNegativeIdenticalProteinGroupListFile);
  }

  @Override
  public BlastpScoringMatrix getScoringMatrix() {
    return lazy(scoringMatrix, EScoringMatrix::getScoringMatrix);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(scoringMatrix, EScoringMatrix::getScoreThreshold);
  }

  @Override
  public Seg getSeg() {
    return lazy(seg, ESeg::getSeg);
  }

  @Override
  public File getSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getSequenceIdListFile);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public boolean isSmithWatermanTracebackEnabled() {
    return sw != null && sw.isSmithWatermanTracebackEnabled();
  }

  @Override
  public File getSubjectFile() {
    return lazy(subject, ESubject::getSubjectFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(subject, ESubject::getSubjectLocation);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public int[] getTaxIds() {
    return lazy(taxIds, ETaxIds::getTaxIds);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(taxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return ungapped != null && ungapped.isUngappedAlignmentOnlyEnabled();
  }

  @Override
  public Integer getWordSize() {
    return lazy(wordSize, EWordSize::getWordSize);
  }

  @Override
  public BlastpConfig enableSmithWatermanTraceback(boolean b) {
    strCache = null;
    (sw = lazy(sw, ESWTback::new)).enableSmithWatermanTraceback(b);
    return this;
  }

  @Override
  public BlastpConfig enableSubjectBestHit(boolean b) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public BlastpConfig enableUngappedAlignmentOnly(boolean b) {
    strCache = null;
    (ungapped = lazy(ungapped, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public BlastpConfig setBestHitOverhang(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public BlastpConfig setBestHitScoreEdge(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public BlastpConfig setCompBasedStatisticsType(CompBasedStats c) {
    strCache = null;
    (compBasedStats = lazy(compBasedStats, ECompBasedStats::new)).setCompBasedStatisticsType(c);
    return this;
  }

  @Override
  public BlastpConfig setCullingLimit(Integer v) {
    strCache = null;
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public BlastpConfig setDbHardMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastpConfig setDbSoftMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastpConfig setExtensionDropoffFinalGapped(Double d) {
    strCache = null;
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public BlastpConfig setGapCostExtend(Integer cost) {
    strCache = null;
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public BlastpConfig setGapCostOpen(Integer cost) {
    strCache = null;
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public BlastpConfig setGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setIdenticalProteinGroupListFile(File ipgList) {
    strCache = null;
    (this.ipgList = lazy(this.ipgList, EIpgList::new)).setIdenticalProteinGroupListFile(ipgList);
    return this;
  }

  @Override
  public BlastpConfig setNegativeGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setNegativeIdenticalProteinGroupListFile(File negIpgList) {
    strCache = null;
    (ipgList = lazy(ipgList, EIpgList::new)).setNegativeIdenticalProteinGroupListFile(negIpgList);
    return this;
  }

  @Override
  public BlastpConfig setNegativeSequenceIdListFile(File f) {
    strCache = null;
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setNegativeTaxIdListFile(File f) {
    strCache = null;
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setNegativeTaxIds(int[] negativeTaxIds) {
    strCache = null;
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public BlastpConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    strCache = null;
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return this;
  }

  @Override
  public BlastpConfig setExtensionDropoffPreliminaryGapped(Double d) {
    strCache = null;
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public BlastpConfig setScoreThreshold(Double d) {
    strCache = null;
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public BlastpConfig setScoringMatrix(BlastpScoringMatrix matrix) {
    strCache = null;
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public BlastpConfig setSeg(Seg seg) {
    strCache = null;
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public BlastpConfig setSequenceIdListFile(File f) {
    strCache = null;
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setSubjectFile(File f) {
    strCache = null;
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public BlastpConfig setSubjectLocation(Location loc) {
    strCache = null;
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public BlastpConfig setTask(BlastpTask task) {
    strCache = null;
    this.task = task;
    return this;
  }

  @Override
  public BlastpConfig setTaxIdListFile(File f) {
    strCache = null;
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastpConfig setTaxIds(int[] taxIds) {
    strCache = null;
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public BlastpConfig setTaxIds(Collection<Integer> taxIds) {
    strCache = null;
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public BlastpConfig setWordSize(Integer size) {
    strCache = null;
    (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    cli.appendNonNull(ToolOption.Task, task);
    lazy(
      cli,
      wordSize,
      gapCost,
      scoringMatrix,
      compBasedStats,
      subject,
      seg,
      giList,
      seqIdList,
      taxIdList,
      taxIds,
      ipgList,
      dbMask,
      cullingLimit,
      bestHit,
      xDrop,
      ungapped,
      sw
    );
  }
}
