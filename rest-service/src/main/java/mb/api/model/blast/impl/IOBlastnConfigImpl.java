package mb.api.model.blast.impl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import mb.api.model.blast.IOHSPSorting;
import mb.api.model.blast.IOBlastLocation;
import mb.api.model.blast.IOBlastReportFormat;
import mb.api.model.blast.IOBlastnConfig;
import mb.api.model.blast.IOBlastnDust;
import mb.lib.blast.model.BlastTool;
import mb.lib.blast.model.HitSorting;
import mb.lib.blast.model.QueryStrand;
import mb.lib.blast.model.n.BlastNTask;
import mb.lib.blast.model.n.DcTemplateType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
public class IOBlastnConfigImpl implements IOBlastnConfig
{
  private final BlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  private String              query;
  private IOBlastLocation     queryLoc;
  private String              eValue;
  private IOBlastReportFormat outFormat;
  private Integer             numDescriptions;
  private Integer             numAlignments;
  private Integer             lineLength;
  private HitSorting   sortHits;
  private IOHSPSorting sortHSPs;
  private Boolean      lcaseMasking;
  private Double              qCovHSPPerc;
  private Integer             maxHSPs;
  private Integer             maxTargetSeqs;
  private Byte                dbSize;
  private Byte                searchSpace;
  private Double              xDropUngap;
  private Boolean             parseDefLines;
  private QueryStrand         strand;
  private BlastNTask          task;
  private Integer             wordSize;
  private Integer             gapOpen;
  private Integer             gapExtend;
  private Integer             penalty;
  private Integer             reward;
  private Boolean             useIndex;
  private String              indexName;
  private IOBlastnDust        dust;
  private Integer             windowMaskerTaxid;
  private Boolean             softMasking;
  private List<Integer>       taxIds;
  private List<Integer>       negativeTaxIds;
  private String              dbSoftMask;
  private String              dbHardMask;
  private Double              percIdentity;
  private Integer             cullingLimit;
  private Double              bestHitOverhang;
  private Double              bestHitScoreEdge;
  private Boolean             subjectBestHit;
  private DcTemplateType      templateType;
  private Byte                templateLength;
  private Boolean             sumStats;
  private Double              xDropGap;
  private Double              xDropGapFinal;
  private Boolean             noGreedy;
  private Integer             minRawGappedScore;
  private Boolean             ungapped;
  private Integer             windowSize;
  private Integer             offDiagonalRange;

  @JsonProperty("tool")
  public BlastTool getTool() {
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
  public IOBlastLocation getQueryLoc() {
    return this.queryLoc;
  }

  @JsonProperty("queryLoc")
  public void setQueryLoc(IOBlastLocation queryLoc) {
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
  public IOBlastReportFormat getOutFormat() {
    return this.outFormat;
  }

  @JsonProperty("outFormat")
  public void setOutFormat(IOBlastReportFormat outFormat) {
    this.outFormat = outFormat;
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
  public HitSorting getSortHits() {
    return this.sortHits;
  }

  @JsonProperty("sortHits")
  public void setSortHits(HitSorting sortHits) {
    this.sortHits = sortHits;
  }

  @JsonProperty("sortHSPs")
  public IOHSPSorting getSortHSPs() {
    return this.sortHSPs;
  }

  @JsonProperty("sortHSPs")
  public void setSortHSPs(IOHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
  }

  @JsonProperty("lcaseMasking")
  public Boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty("lcaseMasking")
  public void setLcaseMasking(Boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("qCovHSPPerc")
  public Double getQCovHSPPerc() {
    return this.qCovHSPPerc;
  }

  @JsonProperty("qCovHSPPerc")
  public void setQCovHSPPerc(Double qCovHSPPerc) {
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
  public Byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(Byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public Byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(Byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropUngap")
  public Double getXDropUngap() {
    return this.xDropUngap;
  }

  @JsonProperty("xDropUngap")
  public void setXDropUngap(Double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  @JsonProperty("parseDefLines")
  public Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty("parseDefLines")
  public void setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }

  @JsonProperty("strand")
  public QueryStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(QueryStrand strand) {
    this.strand = strand;
  }

  @JsonProperty("task")
  public BlastNTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(BlastNTask task) {
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
  public Boolean getUseIndex() {
    return this.useIndex;
  }

  @JsonProperty("useIndex")
  public void setUseIndex(Boolean useIndex) {
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

  @JsonProperty("dust")
  public IOBlastnDust getDust() {
    return this.dust;
  }

  @JsonProperty("dust")
  public void setDust(IOBlastnDust dust) {
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
  public Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty("softMasking")
  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty("taxIds")
  public List<Integer> getTaxIds() {
    return this.taxIds;
  }

  @JsonProperty("taxIds")
  public void setTaxIds(List<Integer> taxIds) {
    this.taxIds = taxIds;
  }

  @JsonProperty("negativeTaxIds")
  public List<Integer> getNegativeTaxIds() {
    return this.negativeTaxIds;
  }

  @JsonProperty("negativeTaxIds")
  public void setNegativeTaxIds(List<Integer> negativeTaxIds) {
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
  public Double getPercIdentity() {
    return this.percIdentity;
  }

  @JsonProperty("percIdentity")
  public void setPercIdentity(Double percIdentity) {
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
  public Double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public Double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty("subjectBestHit")
  public Boolean getSubjectBestHit() {
    return this.subjectBestHit;
  }

  @JsonProperty("subjectBestHit")
  public void setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  @JsonProperty("templateType")
  public DcTemplateType getTemplateType() {
    return this.templateType;
  }

  @JsonProperty("templateType")
  public void setTemplateType(DcTemplateType templateType) {
    this.templateType = templateType;
  }

  @JsonProperty("templateLength")
  public Byte getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty("templateLength")
  public void setTemplateLength(Byte templateLength) {
    this.templateLength = templateLength;
  }

  @JsonProperty("sumStats")
  public Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty("sumStats")
  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropGap")
  public Double getXDropGap() {
    return this.xDropGap;
  }

  @JsonProperty("xDropGap")
  public void setXDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
  }

  @JsonProperty("xDropGapFinal")
  public Double getXDropGapFinal() {
    return this.xDropGapFinal;
  }

  @JsonProperty("xDropGapFinal")
  public void setXDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
  }

  @JsonProperty("noGreedy")
  public Boolean getNoGreedy() {
    return this.noGreedy;
  }

  @JsonProperty("noGreedy")
  public void setNoGreedy(Boolean noGreedy) {
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
  public Boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped(Boolean ungapped) {
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
