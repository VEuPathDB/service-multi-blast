package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.model.ErrorMap;
import org.veupathdb.service.multiblast.model.Validatable;
import org.veupathdb.service.multiblast.service.jobs.BlastnValidator;

public class BlastnConfig extends BlastConfig implements CLISerializable, Validatable
{
  private QueryStrand   strand = QueryStrand.BOTH;
  private BlastnTask    task   = BlastnTask.MEGABLAST;
  // TODO: Database fields
  private File          out;
  private Double        eValue = 10D;
  private Integer       wordSize;
  private Integer       gapOpen;
  private Integer       gapExtend;
  private Integer       penalty;
  private Integer       reward;
  private Boolean       useIndex;
  private String        indexName;
  private File          subject;
  private QueryLocation subjectLoc;
  private OutFormat     outFmt;
  private Boolean       showGIs;
  private Integer       numDescriptions;
  private Integer       numAlignments;
  private Integer       lineLength;
  private Boolean       html;
  private HitSorting    sortHits;
  private HspSorting    sortHsps;
  private Dust          dust;
  private File          filteringDb;
  private Integer       windowMaskerTaxID;
  private File          windowMaskerDB;
  private Boolean       softMasking;
  private Boolean       lowerCaseMasking;
  private String        giList;
  private String        seqIdList;
  private String        negativeGiList;
  private String        negativeSeqIdList;
  private String        taxIds;
  private String        negativeTaxIds;
  private String        taxIdList;
  private String        negativeTaxIdList;
  private String        entrezQuery;
  private String        dbSoftMask;
  private String        dbHardMask;
  private Double        percIdentity;
  private Double        qCovHspPerc;
  private Integer       maxHsps;
  private Integer       cullingLimit;
  private Double        bestHitOverhang;
  private Double        bestHitScoreEdge;
  private Boolean       subjectBestHit;
  private Integer       maxTargetSeqs;
  private TemplateType  templateType;
  private Integer       templateLength;
  private Byte          dbSize;
  private Byte          searchSp;
  private Boolean       sumStats;
  private File          importSearchStrategy;
  private File          exportSearchStrategy;

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

  public File getOut() {
    return out;
  }

  public void setOut(File out) {
    this.out = out;
  }

  public Double geteValue() {
    return eValue;
  }

  public void seteValue(Double eValue) {
    this.eValue = eValue;
  }

  public Integer getWordSize() {
    return wordSize;
  }

  public void setWordSize(Integer wordSize) {
    this.wordSize = wordSize;
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

  public File getSubject() {
    return subject;
  }

  public void setSubject(File subject) {
    this.subject = subject;
  }

  public QueryLocation getSubjectLoc() {
    return subjectLoc;
  }

  public void setSubjectLoc(QueryLocation subjectLoc) {
    this.subjectLoc = subjectLoc;
  }

  public OutFormat getOutFmt() {
    return outFmt;
  }

  public void setOutFmt(OutFormat outFmt) {
    this.outFmt = outFmt;
  }

  public Boolean getShowGIs() {
    return showGIs;
  }

  public void setShowGIs(Boolean showGIs) {
    this.showGIs = showGIs;
  }

  public Integer getNumDescriptions() {
    return numDescriptions;
  }

  public void setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  public Integer getNumAlignments() {
    return numAlignments;
  }

  public void setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
  }

  public Integer getLineLength() {
    return lineLength;
  }

  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }

  public Boolean getHtml() {
    return html;
  }

  public void setHtml(Boolean html) {
    this.html = html;
  }

  public HitSorting getSortHits() {
    return sortHits;
  }

  public void setSortHits(HitSorting sortHits) {
    this.sortHits = sortHits;
  }

  public HspSorting getSortHsps() {
    return sortHsps;
  }

  public void setSortHsps(HspSorting sortHsps) {
    this.sortHsps = sortHsps;
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

  public Boolean getSoftMasking() {
    return softMasking;
  }

  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  public Boolean getLowerCaseMasking() {
    return lowerCaseMasking;
  }

  public void setLowerCaseMasking(Boolean lowerCaseMasking) {
    this.lowerCaseMasking = lowerCaseMasking;
  }

  public String getGiList() {
    return giList;
  }

  public void setGiList(String giList) {
    this.giList = giList;
  }

  public String getSeqIdList() {
    return seqIdList;
  }

  public void setSeqIdList(String seqIdList) {
    this.seqIdList = seqIdList;
  }

  public String getNegativeGiList() {
    return negativeGiList;
  }

  public void setNegativeGiList(String negativeGiList) {
    this.negativeGiList = negativeGiList;
  }

  public String getNegativeSeqIdList() {
    return negativeSeqIdList;
  }

  public void setNegativeSeqIdList(String negativeSeqIdList) {
    this.negativeSeqIdList = negativeSeqIdList;
  }

  public String getTaxIds() {
    return taxIds;
  }

  public void setTaxIds(String taxIds) {
    this.taxIds = taxIds;
  }

  public String getNegativeTaxIds() {
    return negativeTaxIds;
  }

  public void setNegativeTaxIds(String negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
  }

  public String getTaxIdList() {
    return taxIdList;
  }

  public void setTaxIdList(String taxIdList) {
    this.taxIdList = taxIdList;
  }

  public String getNegativeTaxIdList() {
    return negativeTaxIdList;
  }

  public void setNegativeTaxIdList(String negativeTaxIdList) {
    this.negativeTaxIdList = negativeTaxIdList;
  }

  public String getEntrezQuery() {
    return entrezQuery;
  }

  public void setEntrezQuery(String entrezQuery) {
    this.entrezQuery = entrezQuery;
  }

  public String getDbSoftMask() {
    return dbSoftMask;
  }

  public void setDbSoftMask(String dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  public String getDbHardMask() {
    return dbHardMask;
  }

  public void setDbHardMask(String dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  public Double getPercIdentity() {
    return percIdentity;
  }

  public void setPercIdentity(Double percIdentity) {
    this.percIdentity = percIdentity;
  }

  public Double getqCovHspPerc() {
    return qCovHspPerc;
  }

  public void setqCovHspPerc(Double qCovHspPerc) {
    this.qCovHspPerc = qCovHspPerc;
  }

  public Integer getMaxHsps() {
    return maxHsps;
  }

  public void setMaxHsps(Integer maxHsps) {
    this.maxHsps = maxHsps;
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

  public Integer getMaxTargetSeqs() {
    return maxTargetSeqs;
  }

  public void setMaxTargetSeqs(Integer maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
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

  public Byte getDbSize() {
    return dbSize;
  }

  public void setDbSize(Byte dbSize) {
    this.dbSize = dbSize;
  }

  public Byte getSearchSp() {
    return searchSp;
  }

  public void setSearchSp(Byte searchSp) {
    this.searchSp = searchSp;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  public File getImportSearchStrategy() {
    return importSearchStrategy;
  }

  public void setImportSearchStrategy(File importSearchStrategy) {
    this.importSearchStrategy = importSearchStrategy;
  }

  public File getExportSearchStrategy() {
    return exportSearchStrategy;
  }

  public void setExportSearchStrategy(File exportSearchStrategy) {
    this.exportSearchStrategy = exportSearchStrategy;
  }
}
