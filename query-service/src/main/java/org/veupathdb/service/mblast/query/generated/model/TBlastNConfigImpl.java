package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastn")
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
    "task",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "dbGenCode",
    "maxIntronLength",
    "matrix",
    "threshold",
    "compBasedStats",
    "seg",
    "dbSoftMask",
    "dbHardMask",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBestHit",
    "sumStats",
    "xDropoffPrelimGapped",
    "xDropoffFinalGapped",
    "ungappedOnly",
    "useSWTraceback"
})
public class TBlastNConfigImpl implements TBlastNConfig {
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
      value = "task",
      defaultValue = "tblastn"
  )
  private TBlastNTask task;

  @JsonProperty("wordSize")
  private Integer wordSize;

  @JsonProperty("gapOpen")
  private Integer gapOpen;

  @JsonProperty("gapExtend")
  private Integer gapExtend;

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  private Byte dbGenCode;

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  private Integer maxIntronLength;

  @JsonProperty("matrix")
  private TBlastNMatrix matrix;

  @JsonProperty("threshold")
  private Double threshold;

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-score-adjustment-conditional"
  )
  private TBlastNCompBasedStats compBasedStats;

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  private BlastSeg seg;

  @JsonProperty("dbSoftMask")
  private String dbSoftMask;

  @JsonProperty("dbHardMask")
  private String dbHardMask;

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
      value = "ungappedOnly",
      defaultValue = "false"
  )
  private Boolean ungappedOnly;

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  private Boolean useSWTraceback;

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
      value = "task",
      defaultValue = "tblastn"
  )
  public TBlastNTask getTask() {
    return this.task;
  }

  @JsonProperty(
      value = "task",
      defaultValue = "tblastn"
  )
  public void setTask(TBlastNTask task) {
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

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public Byte getDbGenCode() {
    return this.dbGenCode;
  }

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public void setDbGenCode(Byte dbGenCode) {
    this.dbGenCode = dbGenCode;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public Integer getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public void setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }

  @JsonProperty("matrix")
  public TBlastNMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(TBlastNMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty("threshold")
  public Double getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold(Double threshold) {
    this.threshold = threshold;
  }

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-score-adjustment-conditional"
  )
  public TBlastNCompBasedStats getCompBasedStats() {
    return this.compBasedStats;
  }

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-score-adjustment-conditional"
  )
  public void setCompBasedStats(TBlastNCompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public BlastSeg getSeg() {
    return this.seg;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public void setSeg(BlastSeg seg) {
    this.seg = seg;
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
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public Boolean getUseSWTraceback() {
    return this.useSWTraceback;
  }

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public void setUseSWTraceback(Boolean useSWTraceback) {
    this.useSWTraceback = useSWTraceback;
  }
}
