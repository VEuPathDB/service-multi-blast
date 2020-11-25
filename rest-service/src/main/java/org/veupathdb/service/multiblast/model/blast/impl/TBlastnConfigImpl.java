package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnConfig;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.tn.TBlastnTask;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;

public class TBlastnConfigImpl
  extends BlastConfigImpl<TBlastnConfig>
  implements TBlastnConfig
{
  private TBlastnTask                          task;
  private EWordSize                            wordSize;
  private EGapCost                             gapCost;
  private EDbGenCode                           dbGenCode;
  private EMaxIntronLength                     maxIntronLength;
  private EScoringMatrix<TBlastnScoringMatrix> matrix;
  private ECompBasedStats                      compBasedStats;
  private ESubject                             subject;
  private ESeg                                 seg;
  private EGiList                              giList;
  private ESeqIdList                           seqIdList;
  private ETaxIds                              taxIds;
  private ETaxIdList                           taxIdList;
  private EDbMask                              dbMask;
  private ECullingLimit                        cullingLimit;
  private EBestHit                             bestHit;
  private ESumStats                            sumStats;
  private EGapExtDrop                          xDrop;
  private EUngapped                            ungapped;
  private ESWTback                             sw;
  private EPssm                                pssm;

  @Override
  public TBlastnTask getTask() {
    return task;
  }

  @Override
  public TBlastnConfigImpl setTask(TBlastnTask task) {
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
    return bestHit != null && isSubjectBestHitEnabled();
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
  public Byte getDbTranslationGeneticCode() {
    return lazy(dbGenCode, EDbGenCode::getDbTranslationGeneticCode);
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
  public Integer getMaxIntronLength() {
    return lazy(maxIntronLength, EMaxIntronLength::getMaxIntronLength);
  }

  @Override
  public File getPositionSpecificScoringMatrixFile() {
    return lazy(pssm, EPssm::getPositionSpecificScoringMatrixFile);
  }

  @Override
  public TBlastnScoringMatrix getScoringMatrix() {
    return lazy(matrix, EScoringMatrix::getScoringMatrix);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(matrix, EScoringMatrix::getScoreThreshold);
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
  public TBlastnConfig enableSmithWatermanTraceback(boolean b) {
    (sw = lazy(sw, ESWTback::new)).enableSmithWatermanTraceback(b);
    return this;
  }

  @Override
  public TBlastnConfig enableSubjectBestHit(boolean b) {
    (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    return this;
  }

  @Override
  public TBlastnConfig enableSumStatistics(Boolean b) {
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public TBlastnConfig enableUngappedAlignmentOnly(boolean b) {
    (ungapped = lazy(ungapped, EUngapped::new)).enableUngappedAlignmentOnly(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public TBlastnConfig setBestHitOverhang(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public TBlastnConfig setBestHitScoreEdge(Double v) {
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public TBlastnConfig setCompBasedStatisticsType(CompBasedStats c) {
    (compBasedStats = lazy(compBasedStats, ECompBasedStats::new)).setCompBasedStatisticsType(c);
    return this;
  }

  @Override
  public TBlastnConfig setCullingLimit(Integer v) {
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public TBlastnConfig setDbHardMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastnConfig setDbSoftMaskAlgorithmId(String id) {
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastnConfig setDbTranslationGeneticCode(Byte i) {
    (dbGenCode = lazy(dbGenCode, EDbGenCode::new)).setDbTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastnConfig setExtensionDropoffFinalGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffFinalGapped(d);
    return this;
  }

  @Override
  public TBlastnConfig setGapCostExtend(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostExtend(cost);
    return this;
  }

  @Override
  public TBlastnConfig setGapCostOpen(Integer cost) {
    (gapCost = lazy(gapCost, EGapCost::new)).setGapCostOpen(cost);
    return this;
  }

  @Override
  public TBlastnConfig setGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setMaxIntronLength(Integer len) {
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeGenInfoIdListFile(File f) {
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeSequenceIdListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIdListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIds(int[] negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(negativeTaxIds);
    return this;
  }

  @Override
  public TBlastnConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(negativeTaxIds);
    return this;
  }

  @Override
  public TBlastnConfig setPositionSpecificScoringMatrixFile(File f) {
    (pssm = lazy(pssm, EPssm::new)).setPositionSpecificScoringMatrixFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setExtensionDropoffPreliminaryGapped(Double d) {
    (xDrop = lazy(xDrop, EGapExtDrop::new)).setExtensionDropoffPreliminaryGapped(d);
    return this;
  }

  @Override
  public TBlastnConfig setScoreThreshold(Double d) {
    (matrix = lazy(matrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public TBlastnConfig setScoringMatrix(TBlastnScoringMatrix matrix) {
    (this.matrix = lazy(this.matrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public TBlastnConfig setSeg(Seg seg) {
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public TBlastnConfig setSequenceIdListFile(File f) {
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setSubjectFile(File f) {
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setSubjectLocation(Location loc) {
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public TBlastnConfig setTaxIdListFile(File f) {
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIdListFile(f);
    return this;
  }

  @Override
  public TBlastnConfig setTaxIds(int[] taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(taxIds);
    return this;
  }

  @Override
  public TBlastnConfig setTaxIds(Collection<Integer> taxIds) {
    (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(taxIds);
    return this;
  }

  @Override
  public TBlastnConfig setWordSize(Integer size) {
    (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }
}
