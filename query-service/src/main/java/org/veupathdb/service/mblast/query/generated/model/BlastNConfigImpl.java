package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
@JsonPropertyOrder({
    "tool",
    "queryLocation",
    "eValue",
    "softMasking",
    "lowercaseMasking",
    "queryCoverageHSPPercent",
    "maxHSPs",
    "maxTargetSequences",
    "dbSize",
    "searchSpace",
    "xDropoffUngapped",
    "windowSize",
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
    "dust",
    "dbSoftMask",
    "dbHardMask",
    "percentIdentity",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBestHit",
    "templateType",
    "templateLength",
    "sumStats",
    "xDropoffPrelimGapped",
    "xDropoffFinalGapped",
    "nonGreedy",
    "minRawGappedScore",
    "ungappedOnly",
    "offDiagonalRange"
})
public class BlastNConfigImpl implements BlastNConfig {
  @JsonProperty("tool")
  private final BlastQueryTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("queryLocation")
  private BlastLocation queryLocation;

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  private String eValue;

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  private Boolean softMasking;

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  private Boolean lowercaseMasking;

  @JsonProperty("queryCoverageHSPPercent")
  private Double queryCoverageHSPPercent;

  @JsonProperty("maxHSPs")
  private Integer maxHSPs;

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  private Integer maxTargetSequences;

  @JsonProperty("dbSize")
  private Byte dbSize;

  @JsonProperty("searchSpace")
  private Byte searchSpace;

  @JsonProperty("xDropoffUngapped")
  private Double xDropoffUngapped;

  @JsonProperty("windowSize")
  private Integer windowSize;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private Boolean parseDefLines;

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  private BlastStrand strand;

  @JsonProperty(
      value = "task",
      defaultValue = "megablast"
  )
  private BlastNTask task;

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

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  private Boolean useIndex;

  @JsonProperty("indexName")
  private String indexName;

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  private BlastNDust dust;

  @JsonProperty("dbSoftMask")
  private String dbSoftMask;

  @JsonProperty("dbHardMask")
  private String dbHardMask;

  @JsonProperty("percentIdentity")
  private Double percentIdentity;

  @JsonProperty("cullingLimit")
  private Integer cullingLimit;

  @JsonProperty("bestHitOverhang")
  private Double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private Double bestHitScoreEdge;

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  private Boolean subjectBestHit;

  @JsonProperty("templateType")
  private BlastNTemplateType templateType;

  @JsonProperty("templateLength")
  private Integer templateLength;

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  private Boolean sumStats;

  @JsonProperty("xDropoffPrelimGapped")
  private Double xDropoffPrelimGapped;

  @JsonProperty("xDropoffFinalGapped")
  private Double xDropoffFinalGapped;

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  private Boolean nonGreedy;

  @JsonProperty("minRawGappedScore")
  private Integer minRawGappedScore;

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  private Boolean ungappedOnly;

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  private Integer offDiagonalRange;

  @JsonProperty("tool")
  public BlastQueryTool getTool() {
    return this.tool;
  }

  @JsonProperty("queryLocation")
  public BlastLocation getQueryLocation() {
    return this.queryLocation;
  }

  @JsonProperty("queryLocation")
  public void setQueryLocation(BlastLocation queryLocation) {
    this.queryLocation = queryLocation;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  public String getEValue() {
    return this.eValue;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  public void setEValue(String eValue) {
    this.eValue = eValue;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public Boolean getLowercaseMasking() {
    return this.lowercaseMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public void setLowercaseMasking(Boolean lowercaseMasking) {
    this.lowercaseMasking = lowercaseMasking;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public Double getQueryCoverageHSPPercent() {
    return this.queryCoverageHSPPercent;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public void setQueryCoverageHSPPercent(Double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
  }

  @JsonProperty("maxHSPs")
  public Integer getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(Integer maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public Integer getMaxTargetSequences() {
    return this.maxTargetSequences;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public void setMaxTargetSequences(Integer maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
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

  @JsonProperty("xDropoffUngapped")
  public Double getXDropoffUngapped() {
    return this.xDropoffUngapped;
  }

  @JsonProperty("xDropoffUngapped")
  public void setXDropoffUngapped(Double xDropoffUngapped) {
    this.xDropoffUngapped = xDropoffUngapped;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public void setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  public BlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  public void setStrand(BlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty(
      value = "task",
      defaultValue = "megablast"
  )
  public BlastNTask getTask() {
    return this.task;
  }

  @JsonProperty(
      value = "task",
      defaultValue = "megablast"
  )
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

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  public Boolean getUseIndex() {
    return this.useIndex;
  }

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
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

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  public BlastNDust getDust() {
    return this.dust;
  }

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  public void setDust(BlastNDust dust) {
    this.dust = dust;
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

  @JsonProperty("percentIdentity")
  public Double getPercentIdentity() {
    return this.percentIdentity;
  }

  @JsonProperty("percentIdentity")
  public void setPercentIdentity(Double percentIdentity) {
    this.percentIdentity = percentIdentity;
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

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  public Boolean getSubjectBestHit() {
    return this.subjectBestHit;
  }

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  public void setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  @JsonProperty("templateType")
  public BlastNTemplateType getTemplateType() {
    return this.templateType;
  }

  @JsonProperty("templateType")
  public void setTemplateType(BlastNTemplateType templateType) {
    this.templateType = templateType;
  }

  @JsonProperty("templateLength")
  public Integer getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty("templateLength")
  public void setTemplateLength(Integer templateLength) {
    this.templateLength = templateLength;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public Double getXDropoffPrelimGapped() {
    return this.xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public void setXDropoffPrelimGapped(Double xDropoffPrelimGapped) {
    this.xDropoffPrelimGapped = xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public Double getXDropoffFinalGapped() {
    return this.xDropoffFinalGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public void setXDropoffFinalGapped(Double xDropoffFinalGapped) {
    this.xDropoffFinalGapped = xDropoffFinalGapped;
  }

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  public Boolean getNonGreedy() {
    return this.nonGreedy;
  }

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  public void setNonGreedy(Boolean nonGreedy) {
    this.nonGreedy = nonGreedy;
  }

  @JsonProperty("minRawGappedScore")
  public Integer getMinRawGappedScore() {
    return this.minRawGappedScore;
  }

  @JsonProperty("minRawGappedScore")
  public void setMinRawGappedScore(Integer minRawGappedScore) {
    this.minRawGappedScore = minRawGappedScore;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public Boolean getUngappedOnly() {
    return this.ungappedOnly;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public void setUngappedOnly(Boolean ungappedOnly) {
    this.ungappedOnly = ungappedOnly;
  }

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  public Integer getOffDiagonalRange() {
    return this.offDiagonalRange;
  }

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  public void setOffDiagonalRange(Integer offDiagonalRange) {
    this.offDiagonalRange = offDiagonalRange;
  }
}
