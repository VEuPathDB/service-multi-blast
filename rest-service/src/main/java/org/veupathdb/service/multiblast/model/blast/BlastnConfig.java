package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.service.jobs.BlastnValidator;

public class BlastnConfig extends StdBlastConfig implements CLISerializable, Validatable
{
  private QueryStrand   strand;
  private BlastnTask    task;
  private Integer       gapOpen;
  private Integer       gapExtend;
  private Integer       penalty;
  private Integer       reward;
  private Boolean       useIndex;
  private String        indexName;
  private Dust          dust;
  private File          filteringDb;
  private Integer       windowMaskerTaxID;
  private File          windowMaskerDB;
  private Double        percIdentity;
  private Integer       cullingLimit;
  private Double        bestHitOverhang;
  private Double        bestHitScoreEdge;
  private Boolean       subjectBestHit;
  private TemplateType  templateType;
  private Integer       templateLength;
  private Boolean       sumStats;

  @Override
  public ErrorMap validate() {
    return BlastnValidator.validate(this);
  }

  public QueryStrand getStrand() {
    return strand;
  }

  public void setStrand(QueryStrand strand) {
    this.strand = strand;
  }

  public BlastnTask getTask() {
    return task;
  }

  public void setTask(BlastnTask task) {
    this.task = task;
  }

  public Integer getGapOpen() {
    return gapOpen;
  }

  public void setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
  }

  public Integer getGapExtend() {
    return gapExtend;
  }

  public void setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
  }

  public Integer getPenalty() {
    return penalty;
  }

  public void setPenalty(Integer penalty) {
    this.penalty = penalty;
  }

  public Integer getReward() {
    return reward;
  }

  public void setReward(Integer reward) {
    this.reward = reward;
  }

  public Boolean getUseIndex() {
    return useIndex;
  }

  public void setUseIndex(Boolean useIndex) {
    this.useIndex = useIndex;
  }

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public Dust getDust() {
    return dust;
  }

  public void setDust(Dust dust) {
    this.dust = dust;
  }

  public File getFilteringDb() {
    return filteringDb;
  }

  public void setFilteringDb(File filteringDb) {
    this.filteringDb = filteringDb;
  }

  public Integer getWindowMaskerTaxID() {
    return windowMaskerTaxID;
  }

  public void setWindowMaskerTaxID(Integer windowMaskerTaxID) {
    this.windowMaskerTaxID = windowMaskerTaxID;
  }

  public File getWindowMaskerDB() {
    return windowMaskerDB;
  }

  public void setWindowMaskerDB(File windowMaskerDB) {
    this.windowMaskerDB = windowMaskerDB;
  }

  public Double getPercIdentity() {
    return percIdentity;
  }

  public void setPercIdentity(Double percIdentity) {
    this.percIdentity = percIdentity;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public void setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public void setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public void setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public void setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  public TemplateType getTemplateType() {
    return templateType;
  }

  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  public Integer getTemplateLength() {
    return templateLength;
  }

  public void setTemplateLength(Integer templateLength) {
    this.templateLength = templateLength;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }


}
