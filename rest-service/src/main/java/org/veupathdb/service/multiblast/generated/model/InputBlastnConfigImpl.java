package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
@JsonPropertyOrder({
    "tool",
    "query",
    "queryLoc",
    "eValue",
    "outFormat",
    "showGIs",
    "numDescriptions",
    "numAlignments",
    "lineLength",
    "sortHits",
    "sortHSPs",
    "lcaseMasking",
    "qCovHSPPerc",
    "maxHSPs",
    "maxTargetSeqs",
    "dbSize",
    "searchSpace",
    "xDropUngap",
    "parseDefLines",
    "strand",
    "task",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "penalty",
    "reward",
    "useIndex",
    "indexName",
    "subjectLoc",
    "dust",
    "windowMaskerTaxid",
    "softMasking",
    "taxIds",
    "negativeTaxIds",
    "dbSoftMask",
    "dbHardMask",
    "percIdentity",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBesthit",
    "templateType",
    "templateLength",
    "sumStats",
    "xDropGap",
    "xDropGapFinal",
    "noGreedy",
    "minRawGappedScore",
    "ungapped",
    "windowSize",
    "offDiagonalRange"
})
public class InputBlastnConfigImpl implements InputBlastnConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("query")
  private String query;

  @JsonProperty("queryLoc")
  private InputBlastLocation queryLoc;

  @JsonProperty("eValue")
  private String eValue;

  @JsonProperty("outFormat")
  private InputBlastOutFmt outFormat;

  @JsonProperty("showGIs")
  private  Boolean showGIs;

  @JsonProperty("numDescriptions")
  private Integer numDescriptions;

  @JsonProperty("numAlignments")
  private Integer numAlignments;

  @JsonProperty("lineLength")
  private Integer lineLength;

  @JsonProperty("sortHits")
  private InputHitSorting sortHits;

  @JsonProperty("sortHSPs")
  private InputHSPSorting sortHSPs;

  @JsonProperty("lcaseMasking")
  private  Boolean lcaseMasking;

  @JsonProperty("qCovHSPPerc")
  private  Double qCovHSPPerc;

  @JsonProperty("maxHSPs")
  private Integer maxHSPs;

  @JsonProperty("maxTargetSeqs")
  private Integer maxTargetSeqs;

  @JsonProperty("dbSize")
  private  Byte dbSize;

  @JsonProperty("searchSpace")
  private  Byte searchSpace;

  @JsonProperty("xDropUngap")
  private  Double xDropUngap;

  @JsonProperty("parseDefLines")
  private  Boolean parseDefLines;

  @JsonProperty("strand")
  private InputBlastStrand strand;

  @JsonProperty("task")
  private InputBlastnTask task;

  @JsonProperty("wordSize")
  private Integer wordSize;

  @JsonProperty("gapOpen")
  private Integer gapOpen;

  @JsonProperty("gapExtend")
  private Integer gapExtend;

  @JsonProperty("penalty")
  private Integer penalty;

  @JsonProperty("reward")
  private Integer reward;

  @JsonProperty("useIndex")
  private  Boolean useIndex;

  @JsonProperty("indexName")
  private String indexName;

  @JsonProperty("subjectLoc")
  private InputBlastLocation subjectLoc;

  @JsonProperty("dust")
  private InputBlastnDust dust;

  @JsonProperty("windowMaskerTaxid")
  private Integer windowMaskerTaxid;

  @JsonProperty("softMasking")
  private  Boolean softMasking;

  @JsonProperty("taxIds")
  private List<String> taxIds;

  @JsonProperty("negativeTaxIds")
  private List<String> negativeTaxIds;

  @JsonProperty("dbSoftMask")
  private String dbSoftMask;

  @JsonProperty("dbHardMask")
  private String dbHardMask;

  @JsonProperty("percIdentity")
  private  Double percIdentity;

  @JsonProperty("cullingLimit")
  private Integer cullingLimit;

  @JsonProperty("bestHitOverhang")
  private  Double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private  Double bestHitScoreEdge;

  @JsonProperty("subjectBesthit")
  private  Boolean subjectBesthit;

  @JsonProperty("templateType")
  private InputBlastnDcTemplateType templateType;

  @JsonProperty("templateLength")
  private  Byte templateLength;

  @JsonProperty("sumStats")
  private  Boolean sumStats;

  @JsonProperty("xDropGap")
  private  Double xDropGap;

  @JsonProperty("xDropGapFinal")
  private  Double xDropGapFinal;

  @JsonProperty("noGreedy")
  private  Boolean noGreedy;

  @JsonProperty("minRawGappedScore")
  private Integer minRawGappedScore;

  @JsonProperty("ungapped")
  private  Boolean ungapped;

  @JsonProperty("windowSize")
  private Integer windowSize;

  @JsonProperty("offDiagonalRange")
  private Integer offDiagonalRange;

  @JsonProperty("tool")
  public InputBlastTool getTool() {
    return this.tool;
  }

  @JsonProperty("query")
  public String getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  @JsonProperty("queryLoc")
  public InputBlastLocation getQueryLoc() {
    return this.queryLoc;
  }

  @JsonProperty("queryLoc")
  public void setQueryLoc(InputBlastLocation queryLoc) {
    this.queryLoc = queryLoc;
  }

  @JsonProperty("eValue")
  public String getEValue() {
    return this.eValue;
  }

  @JsonProperty("eValue")
  public void setEValue(String eValue) {
    this.eValue = eValue;
  }

  @JsonProperty("outFormat")
  public InputBlastOutFmt getOutFormat() {
    return this.outFormat;
  }

  @JsonProperty("outFormat")
  public void setOutFormat(InputBlastOutFmt outFormat) {
    this.outFormat = outFormat;
  }

  @JsonProperty("showGIs")
  public  Boolean getShowGIs() {
    return this.showGIs;
  }

  @JsonProperty("showGIs")
  public void setShowGIs (Boolean showGIs) {
    this.showGIs = showGIs;
  }

  @JsonProperty("numDescriptions")
  public Integer getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty("numDescriptions")
  public void setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty("numAlignments")
  public Integer getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty("numAlignments")
  public void setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty("lineLength")
  public Integer getLineLength() {
    return this.lineLength;
  }

  @JsonProperty("lineLength")
  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }

  @JsonProperty("sortHits")
  public InputHitSorting getSortHits() {
    return this.sortHits;
  }

  @JsonProperty("sortHits")
  public void setSortHits(InputHitSorting sortHits) {
    this.sortHits = sortHits;
  }

  @JsonProperty("sortHSPs")
  public InputHSPSorting getSortHSPs() {
    return this.sortHSPs;
  }

  @JsonProperty("sortHSPs")
  public void setSortHSPs(InputHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
  }

  @JsonProperty("lcaseMasking")
  public  Boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty("lcaseMasking")
  public void setLcaseMasking (Boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("qCovHSPPerc")
  public  Double getQCovHSPPerc() {
    return this.qCovHSPPerc;
  }

  @JsonProperty("qCovHSPPerc")
  public void setQCovHSPPerc (Double qCovHSPPerc) {
    this.qCovHSPPerc = qCovHSPPerc;
  }

  @JsonProperty("maxHSPs")
  public Integer getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(Integer maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty("maxTargetSeqs")
  public Integer getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty("maxTargetSeqs")
  public void setMaxTargetSeqs(Integer maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
  }

  @JsonProperty("dbSize")
  public  Byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize (Byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public  Byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace (Byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropUngap")
  public  Double getXDropUngap() {
    return this.xDropUngap;
  }

  @JsonProperty("xDropUngap")
  public void setXDropUngap (Double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  @JsonProperty("parseDefLines")
  public  Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty("parseDefLines")
  public void setParseDefLines (Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }

  @JsonProperty("strand")
  public InputBlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(InputBlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty("task")
  public InputBlastnTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(InputBlastnTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public Integer getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(Integer wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("gapOpen")
  public Integer getGapOpen() {
    return this.gapOpen;
  }

  @JsonProperty("gapOpen")
  public void setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
  }

  @JsonProperty("gapExtend")
  public Integer getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty("gapExtend")
  public void setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
  }

  @JsonProperty("penalty")
  public Integer getPenalty() {
    return this.penalty;
  }

  @JsonProperty("penalty")
  public void setPenalty(Integer penalty) {
    this.penalty = penalty;
  }

  @JsonProperty("reward")
  public Integer getReward() {
    return this.reward;
  }

  @JsonProperty("reward")
  public void setReward(Integer reward) {
    this.reward = reward;
  }

  @JsonProperty("useIndex")
  public  Boolean getUseIndex() {
    return this.useIndex;
  }

  @JsonProperty("useIndex")
  public void setUseIndex (Boolean useIndex) {
    this.useIndex = useIndex;
  }

  @JsonProperty("indexName")
  public String getIndexName() {
    return this.indexName;
  }

  @JsonProperty("indexName")
  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  @JsonProperty("subjectLoc")
  public InputBlastLocation getSubjectLoc() {
    return this.subjectLoc;
  }

  @JsonProperty("subjectLoc")
  public void setSubjectLoc(InputBlastLocation subjectLoc) {
    this.subjectLoc = subjectLoc;
  }

  @JsonProperty("dust")
  public InputBlastnDust getDust() {
    return this.dust;
  }

  @JsonProperty("dust")
  public void setDust(InputBlastnDust dust) {
    this.dust = dust;
  }

  @JsonProperty("windowMaskerTaxid")
  public Integer getWindowMaskerTaxid() {
    return this.windowMaskerTaxid;
  }

  @JsonProperty("windowMaskerTaxid")
  public void setWindowMaskerTaxid(Integer windowMaskerTaxid) {
    this.windowMaskerTaxid = windowMaskerTaxid;
  }

  @JsonProperty("softMasking")
  public  Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty("softMasking")
  public void setSoftMasking (Boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty("taxIds")
  public List<String> getTaxIds() {
    return this.taxIds;
  }

  @JsonProperty("taxIds")
  public void setTaxIds(List<String> taxIds) {
    this.taxIds = taxIds;
  }

  @JsonProperty("negativeTaxIds")
  public List<String> getNegativeTaxIds() {
    return this.negativeTaxIds;
  }

  @JsonProperty("negativeTaxIds")
  public void setNegativeTaxIds(List<String> negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
  }

  @JsonProperty("dbSoftMask")
  public String getDbSoftMask() {
    return this.dbSoftMask;
  }

  @JsonProperty("dbSoftMask")
  public void setDbSoftMask(String dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  @JsonProperty("dbHardMask")
  public String getDbHardMask() {
    return this.dbHardMask;
  }

  @JsonProperty("dbHardMask")
  public void setDbHardMask(String dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  @JsonProperty("percIdentity")
  public  Double getPercIdentity() {
    return this.percIdentity;
  }

  @JsonProperty("percIdentity")
  public void setPercIdentity (Double percIdentity) {
    this.percIdentity = percIdentity;
  }

  @JsonProperty("cullingLimit")
  public Integer getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  @JsonProperty("bestHitOverhang")
  public  Double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang (Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public  Double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge (Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty("subjectBesthit")
  public  Boolean getSubjectBesthit() {
    return this.subjectBesthit;
  }

  @JsonProperty("subjectBesthit")
  public void setSubjectBesthit (Boolean subjectBesthit) {
    this.subjectBesthit = subjectBesthit;
  }

  @JsonProperty("templateType")
  public InputBlastnDcTemplateType getTemplateType() {
    return this.templateType;
  }

  @JsonProperty("templateType")
  public void setTemplateType(InputBlastnDcTemplateType templateType) {
    this.templateType = templateType;
  }

  @JsonProperty("templateLength")
  public  Byte getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty("templateLength")
  public void setTemplateLength (Byte templateLength) {
    this.templateLength = templateLength;
  }

  @JsonProperty("sumStats")
  public  Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty("sumStats")
  public void setSumStats (Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropGap")
  public  Double getXDropGap() {
    return this.xDropGap;
  }

  @JsonProperty("xDropGap")
  public void setXDropGap (Double xDropGap) {
    this.xDropGap = xDropGap;
  }

  @JsonProperty("xDropGapFinal")
  public  Double getXDropGapFinal() {
    return this.xDropGapFinal;
  }

  @JsonProperty("xDropGapFinal")
  public void setXDropGapFinal (Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
  }

  @JsonProperty("noGreedy")
  public  Boolean getNoGreedy() {
    return this.noGreedy;
  }

  @JsonProperty("noGreedy")
  public void setNoGreedy (Boolean noGreedy) {
    this.noGreedy = noGreedy;
  }

  @JsonProperty("minRawGappedScore")
  public Integer getMinRawGappedScore() {
    return this.minRawGappedScore;
  }

  @JsonProperty("minRawGappedScore")
  public void setMinRawGappedScore(Integer minRawGappedScore) {
    this.minRawGappedScore = minRawGappedScore;
  }

  @JsonProperty("ungapped")
  public  Boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped (Boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty("offDiagonalRange")
  public Integer getOffDiagonalRange() {
    return this.offDiagonalRange;
  }

  @JsonProperty("offDiagonalRange")
  public void setOffDiagonalRange(Integer offDiagonalRange) {
    this.offDiagonalRange = offDiagonalRange;
  }
}
