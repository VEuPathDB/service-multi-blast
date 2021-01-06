package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.x.BlastxConfig;
import org.veupathdb.service.multiblast.model.blast.x.BlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.x.BlastxTask;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;

public class BlastxConfigImpl
  extends BlastConfigImpl<BlastxConfig>
  implements BlastxConfig
{
  private BlastxTask                          task;
  private EStrand                             strand;
  private EQueryGenCode                       queryGenCode;
  private EWordSize                           wordSize;
  private EGapCost                            gapCost;
  private EMaxIntronLength                    maxIntronLength;
  private EScoringMatrix<BlastxScoringMatrix> scoringMatrix;
  private ECompBasedStats                     compBasedStats;
  private ESubject                            subject;
  private ESeg                                seg;
  private EGiList                             giList;
  private ESeqIdList                          seqIdList;
  private ETaxIds                             taxIds;
  private ETaxIdList                          taxIdList;
  private EIpgList                            ipgList;
  private EDbMask                             dbMask;
  private ECullingLimit                       cullingLimit;
  private EBestHit                            bestHit;
  private ESumStats                           sumStats;
  private EGapExtDrop                         xDrop;
  private EUngapped                           ungapped;
  private ESWTback                            sw;

  @Override
  public BlastxTask getTask() {
    return task;
  }

  @Override
  public BlastxConfigImpl setTask(BlastxTask task) {
    this.task = task;
    return this;
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
  public Integer getMaxIntronLength() {
    return lazy(maxIntronLength, EMaxIntronLength::getMaxIntronLength);
  }

  @Override
  public Byte getQueryTranslationGeneticCode() {
    return lazy(queryGenCode, EQueryGenCode::getQueryTranslationGeneticCode);
  }

  @Override
  public BlastxScoringMatrix getScoringMatrix() {
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
  public QueryStrand getStrand() {
    return lazy(strand, EStrand::getStrand);
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
  public BlastxConfig enableSmithWatermanTraceback(boolean b) {
    strCache = null;
    (sw = lazy(sw, ESWTback::new)).enableSmithWatermanTraceback(b);
    return this;
  }

  @Override
  public BlastxConfig enableSubjectBestHit(boolean b) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public BlastxConfig enableSumStatistics(Boolean b) {
    strCache = null;
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public BlastxConfig enableUngappedAlignmentOnly(boolean b) {
    strCache = null;
    (ungapped = lazy(ungapped, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public BlastxConfig setBestHitOverhang(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public BlastxConfig setBestHitScoreEdge(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public BlastxConfig setCompBasedStatisticsType(CompBasedStats c) {
    strCache = null;
    (compBasedStats = lazy(compBasedStats, ECompBasedStats::new)).setCompBasedStatisticsType(c);
    return this;
  }

  @Override
  public BlastxConfig setCullingLimit(Integer v) {
    strCache = null;
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public BlastxConfig setDbHardMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastxConfig setDbSoftMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public BlastxConfig setExtensionDropoffFinalGapped(Double d) {
    strCache = null;
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public BlastxConfig setGapCostExtend(Integer cost) {
    strCache = null;
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public BlastxConfig setGapCostOpen(Integer cost) {
    strCache = null;
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public BlastxConfig setGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setIdenticalProteinGroupListFile(File ipgList) {
    strCache = null;
    (this.ipgList = lazy(this.ipgList, EIpgList::new)).setIdenticalProteinGroupListFile(ipgList);
    return this;
  }

  @Override
  public BlastxConfig setMaxIntronLength(Integer len) {
    strCache = null;
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public BlastxConfig setNegativeGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeIdenticalProteinGroupListFile(File negIpgList) {
    strCache = null;
    (ipgList = lazy(ipgList, EIpgList::new)).setNegativeIdenticalProteinGroupListFile(negIpgList);
    return this;
  }

  @Override
  public BlastxConfig setNegativeSequenceIdListFile(File f) {
    strCache = null;
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIdListFile(File f) {
    strCache = null;
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIds(int[] negativeTaxIds) {
    strCache = null;
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : Arrays.copyOf(negativeTaxIds, negativeTaxIds.length)
    );
    return this;
  }

  @Override
  public BlastxConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    strCache = null;
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(
      negativeTaxIds == null ? null : new ArrayList<>(negativeTaxIds)
    );
    return this;
  }

  @Override
  public BlastxConfig setExtensionDropoffPreliminaryGapped(Double d) {
    strCache = null;
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public BlastxConfig setQueryTranslationGeneticCode(Byte i) {
    strCache = null;
    (queryGenCode = lazy(queryGenCode, EQueryGenCode::new)).setQueryTranslationGeneticCode(i);
    return this;
  }

  @Override
  public BlastxConfig setScoreThreshold(Double d) {
    strCache = null;
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public BlastxConfig setScoringMatrix(BlastxScoringMatrix matrix) {
    strCache = null;
    (scoringMatrix = lazy(scoringMatrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public BlastxConfig setSeg(Seg seg) {
    strCache = null;
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public BlastxConfig setSequenceIdListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setStrand(QueryStrand s) {
    (strand = lazy(strand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public BlastxConfig setSubjectFile(File f) {
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public BlastxConfig setSubjectLocation(Location loc) {
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public BlastxConfig setTaxIdListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIdListFile(f);
    return this;
  }

  @Override
  public BlastxConfig setTaxIds(int[] taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : Arrays.copyOf(taxIds, taxIds.length)
    );
    return this;
  }

  @Override
  public BlastxConfig setTaxIds(Collection<Integer> taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(
      taxIds == null ? null : new ArrayList<>(taxIds)
    );
    return this;
  }

  @Override
  public BlastxConfig setWordSize(Integer size) {
    (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }
}
