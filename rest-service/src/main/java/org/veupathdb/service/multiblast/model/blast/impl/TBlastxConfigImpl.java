package org.veupathdb.service.multiblast.model.blast.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.veupathdb.service.multiblast.model.blast.Location;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastxConfig;
import org.veupathdb.service.multiblast.model.blast.tx.TBlastxScoringMatrix;
import org.veupathdb.service.multiblast.model.blast.impl.trait.*;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class TBlastxConfigImpl
  extends BlastConfigImpl<TBlastxConfig>
  implements TBlastxConfig
{
  private EStrand                              strand;
  private EQueryGenCode                        queryGenCode;
  private EWordSize                            wordSize;
  private EMaxIntronLength                     maxIntronLength;
  private EScoringMatrix<TBlastxScoringMatrix> matrix;
  private EDbGenCode                           dbGenCode;
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

  @Override
  public Double getBestHitOverhang() {
    return lazy(bestHit, EBestHit::getBestHitOverhang);
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
  public File getGenInfoIdListFile() {
    return lazy(giList, EGiList::getGenInfoIdListFile);
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
  public TBlastxScoringMatrix getScoringMatrix() {
    return lazy(matrix, EScoringMatrix::getScoringMatrix);
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
  public QueryStrand getStrand() {
    return lazy(strand, EStrand::getStrand);
  }

  @Override
  public File getSubjectFile() {
    return lazy(subject, ESubject::getSubjectFile);
  }

  @Override
  public File getTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getTaxIdListFile);
  }

  @Override
  public int[] getTaxIds() {
    return lazy(taxIds, ETaxIds::getTaxIds);
  }

  @Override
  public Integer getWordSize() {
    return lazy(wordSize, EWordSize::getWordSize);
  }

  @Override
  public int[] getNegativeTaxIds() {
    return lazy(taxIds, ETaxIds::getNegativeTaxIds);
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return lazy(taxIdList, ETaxIdList::getNegativeTaxIdListFile);
  }

  @Override
  public Location getSubjectLocation() {
    return lazy(subject, ESubject::getSubjectLocation);
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return lazy(seqIdList, ESeqIdList::getNegativeSequenceIdListFile);
  }

  @Override
  public Double getScoreThreshold() {
    return lazy(matrix, EScoringMatrix::getScoreThreshold);
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return lazy(giList, EGiList::getNegativeGenInfoIdListFile);
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return lazy(dbMask, EDbMask::getDbHardMaskAlgorithmId);
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
  public TBlastxConfig enableSubjectBestHit(boolean b) {
    if (b) {
      strCache = null;
      (bestHit = lazy(bestHit, EBestHit::new)).enableSubjectBestHit(b);
    }
    return this;
  }

  @Override
  public TBlastxConfig enableSumStatistics(Boolean b) {
    strCache = null;
    (sumStats = lazy(sumStats, ESumStats::new)).enableSumStatistics(b);
    return this;
  }

  @Override
  public Boolean isSumStatisticsEnabled() {
    return lazy(sumStats, ESumStats::isSumStatisticsEnabled);
  }

  @Override
  public TBlastxConfig setBestHitOverhang(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitOverhang(v);
    return this;
  }

  @Override
  public TBlastxConfig setBestHitScoreEdge(Double v) {
    strCache = null;
    (bestHit = lazy(bestHit, EBestHit::new)).setBestHitScoreEdge(v);
    return this;
  }

  @Override
  public TBlastxConfig setCullingLimit(Integer v) {
    strCache = null;
    (cullingLimit = lazy(cullingLimit, ECullingLimit::new)).setCullingLimit(v);
    return this;
  }

  @Override
  public TBlastxConfig setDbHardMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbHardMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastxConfig setDbSoftMaskAlgorithmId(String id) {
    strCache = null;
    (dbMask = lazy(dbMask, EDbMask::new)).setDbSoftMaskAlgorithmId(id);
    return this;
  }

  @Override
  public TBlastxConfig setDbTranslationGeneticCode(Byte i) {
    strCache = null;
    (dbGenCode = lazy(dbGenCode, EDbGenCode::new)).setDbTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastxConfig setGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setMaxIntronLength(Integer len) {
    strCache = null;
    (maxIntronLength = lazy(maxIntronLength, EMaxIntronLength::new)).setMaxIntronLength(len);
    return this;
  }

  @Override
  public TBlastxConfig setNegativeGenInfoIdListFile(File f) {
    strCache = null;
    (giList = lazy(giList, EGiList::new)).setNegativeGenInfoIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setNegativeSequenceIdListFile(File f) {
    strCache = null;
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setNegativeSequenceIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setNegativeTaxIdListFile(File f) {
    strCache = null;
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setNegativeTaxIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setNegativeTaxIds(int[] negativeTaxIds) {
    strCache = null;

    if (negativeTaxIds != null)
      (taxIds = lazy(taxIds, ETaxIds::new)).
        setNegativeTaxIds(Arrays.copyOf(negativeTaxIds, negativeTaxIds.length));
    else
      taxIds = null;

    return this;
  }

  @Override
  public TBlastxConfig setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    strCache = null;
    if (negativeTaxIds != null)
      (taxIds = lazy(taxIds, ETaxIds::new)).setNegativeTaxIds(new ArrayList<>(negativeTaxIds));
    else
      taxIds = null;
    return this;
  }

  @Override
  public TBlastxConfig setQueryTranslationGeneticCode(Byte i) {
    strCache = null;
    (queryGenCode = lazy(queryGenCode, EQueryGenCode::new)).setQueryTranslationGeneticCode(i);
    return this;
  }

  @Override
  public TBlastxConfig setScoreThreshold(Double d) {
    strCache = null;
    (matrix = lazy(matrix, EScoringMatrix::new)).setScoreThreshold(d);
    return this;
  }

  @Override
  public TBlastxConfig setScoringMatrix(TBlastxScoringMatrix matrix) {
    strCache = null;
    (this.matrix = lazy(this.matrix, EScoringMatrix::new)).setScoringMatrix(matrix);
    return this;
  }

  @Override
  public TBlastxConfig setSeg(Seg seg) {
    strCache = null;
    (this.seg = lazy(this.seg, ESeg::new)).setSeg(seg);
    return this;
  }

  @Override
  public TBlastxConfig setSequenceIdListFile(File f) {
    strCache = null;
    (seqIdList = lazy(seqIdList, ESeqIdList::new)).setSequenceIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setStrand(QueryStrand s) {
    strCache = null;
    (strand = lazy(strand, EStrand::new)).setStrand(s);
    return this;
  }

  @Override
  public TBlastxConfig setSubjectFile(File f) {
    strCache = null;
    (subject = lazy(subject, ESubject::new)).setSubjectFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setSubjectLocation(Location loc) {
    strCache = null;
    (subject = lazy(subject, ESubject::new)).setSubjectLocation(loc);
    return this;
  }

  @Override
  public TBlastxConfig setTaxIdListFile(File f) {
    strCache = null;
    (taxIdList = lazy(taxIdList, ETaxIdList::new)).setTaxIdListFile(f);
    return this;
  }

  @Override
  public TBlastxConfig setTaxIds(int[] taxIds) {
    strCache = null;
    if (taxIds != null)
      (this.taxIds = lazy(this.taxIds, ETaxIds::new)).
        setTaxIds(Arrays.copyOf(taxIds, taxIds.length));
    else
      this.taxIds = null;
    return this;
  }

  @Override
  public TBlastxConfig setTaxIds(Collection<Integer> taxIds) {
    strCache = null;
    if (taxIds != null)
      (this.taxIds = lazy(this.taxIds, ETaxIds::new)).setTaxIds(new ArrayList<>(taxIds));
    else
      taxIds = null;
    return this;
  }

  @Override
  public TBlastxConfig setWordSize(Integer size) {
    strCache = null;
    if (size != null)
      (wordSize = lazy(wordSize, EWordSize::new)).setWordSize(size);
    return this;
  }

  @Override
  public void toCli(CliBuilder cli) {
    super.toCli(cli);

    lazy(
      cli,
      strand,
      queryGenCode,
      wordSize,
      maxIntronLength,
      matrix,
      dbGenCode,
      subject,
      seg,
      giList,
      seqIdList,
      taxIds,
      taxIdList,
      dbMask,
      cullingLimit,
      bestHit,
      sumStats
    );
  }
}
