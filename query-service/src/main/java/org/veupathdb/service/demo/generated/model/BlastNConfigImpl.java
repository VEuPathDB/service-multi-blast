package org.veupathdb.service.demo.generated.model;

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
  private boolean softMasking;

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  private boolean lowercaseMasking;

  @JsonProperty("queryCoverageHSPPercent")
  private double queryCoverageHSPPercent;

  @JsonProperty("maxHSPs")
  private int maxHSPs;

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  private int maxTargetSequences;

  @JsonProperty("dbSize")
  private byte dbSize;

  @JsonProperty("searchSpace")
  private byte searchSpace;

  @JsonProperty("xDropoffUngapped")
  private double xDropoffUngapped;

  @JsonProperty("windowSize")
  private int windowSize;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private boolean parseDefLines;

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
  private int wordSize;

  @JsonProperty("gapOpen")
  private int gapOpen;

  @JsonProperty("gapExtend")
  private int gapExtend;

  @JsonProperty("penalty")
  private int penalty;

  @JsonProperty("reward")
  private int reward;

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  private boolean useIndex;

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
  private double percentIdentity;

  @JsonProperty("cullingLimit")
  private int cullingLimit;

  @JsonProperty("bestHitOverhang")
  private double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private double bestHitScoreEdge;

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  private boolean subjectBestHit;

  @JsonProperty("templateType")
  private BlastNTemplateType templateType;

  @JsonProperty("templateLength")
  private int templateLength;

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  private boolean sumStats;

  @JsonProperty("xDropoffPrelimGapped")
  private double xDropoffPrelimGapped;

  @JsonProperty("xDropoffFinalGapped")
  private double xDropoffFinalGapped;

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  private boolean nonGreedy;

  @JsonProperty("minRawGappedScore")
  private int minRawGappedScore;

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  private boolean ungappedOnly;

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  private int offDiagonalRange;

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
  public boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public void setSoftMasking(boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public boolean getLowercaseMasking() {
    return this.lowercaseMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public void setLowercaseMasking(boolean lowercaseMasking) {
    this.lowercaseMasking = lowercaseMasking;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public double getQueryCoverageHSPPercent() {
    return this.queryCoverageHSPPercent;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public void setQueryCoverageHSPPercent(double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
  }

  @JsonProperty("maxHSPs")
  public int getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(int maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public int getMaxTargetSequences() {
    return this.maxTargetSequences;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public void setMaxTargetSequences(int maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
  }

  @JsonProperty("dbSize")
  public byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropoffUngapped")
  public double getXDropoffUngapped() {
    return this.xDropoffUngapped;
  }

  @JsonProperty("xDropoffUngapped")
  public void setXDropoffUngapped(double xDropoffUngapped) {
    this.xDropoffUngapped = xDropoffUngapped;
  }

  @JsonProperty("windowSize")
  public int getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public void setParseDefLines(boolean parseDefLines) {
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
  public int getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(int wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("gapOpen")
  public int getGapOpen() {
    return this.gapOpen;
  }

  @JsonProperty("gapOpen")
  public void setGapOpen(int gapOpen) {
    this.gapOpen = gapOpen;
  }

  @JsonProperty("gapExtend")
  public int getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty("gapExtend")
  public void setGapExtend(int gapExtend) {
    this.gapExtend = gapExtend;
  }

  @JsonProperty("penalty")
  public int getPenalty() {
    return this.penalty;
  }

  @JsonProperty("penalty")
  public void setPenalty(int penalty) {
    this.penalty = penalty;
  }

  @JsonProperty("reward")
  public int getReward() {
    return this.reward;
  }

  @JsonProperty("reward")
  public void setReward(int reward) {
    this.reward = reward;
  }

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  public boolean getUseIndex() {
    return this.useIndex;
  }

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  public void setUseIndex(boolean useIndex) {
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
  public double getPercentIdentity() {
    return this.percentIdentity;
  }

  @JsonProperty("percentIdentity")
  public void setPercentIdentity(double percentIdentity) {
    this.percentIdentity = percentIdentity;
  }

  @JsonProperty("cullingLimit")
  public int getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(int cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  @JsonProperty("bestHitOverhang")
  public double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang(double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge(double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  public boolean getSubjectBestHit() {
    return this.subjectBestHit;
  }

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  public void setSubjectBestHit(boolean subjectBestHit) {
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
  public int getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty("templateLength")
  public void setTemplateLength(int templateLength) {
    this.templateLength = templateLength;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public void setSumStats(boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public double getXDropoffPrelimGapped() {
    return this.xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public void setXDropoffPrelimGapped(double xDropoffPrelimGapped) {
    this.xDropoffPrelimGapped = xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public double getXDropoffFinalGapped() {
    return this.xDropoffFinalGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public void setXDropoffFinalGapped(double xDropoffFinalGapped) {
    this.xDropoffFinalGapped = xDropoffFinalGapped;
  }

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  public boolean getNonGreedy() {
    return this.nonGreedy;
  }

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  public void setNonGreedy(boolean nonGreedy) {
    this.nonGreedy = nonGreedy;
  }

  @JsonProperty("minRawGappedScore")
  public int getMinRawGappedScore() {
    return this.minRawGappedScore;
  }

  @JsonProperty("minRawGappedScore")
  public void setMinRawGappedScore(int minRawGappedScore) {
    this.minRawGappedScore = minRawGappedScore;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public boolean getUngappedOnly() {
    return this.ungappedOnly;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public void setUngappedOnly(boolean ungappedOnly) {
    this.ungappedOnly = ungappedOnly;
  }

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  public int getOffDiagonalRange() {
    return this.offDiagonalRange;
  }

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  public void setOffDiagonalRange(int offDiagonalRange) {
    this.offDiagonalRange = offDiagonalRange;
  }
}
