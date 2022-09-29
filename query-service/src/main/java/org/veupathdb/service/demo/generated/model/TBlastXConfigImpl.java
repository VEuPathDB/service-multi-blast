package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastx")
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
    "queryGenCode",
    "wordSize",
    "maxIntronLength",
    "matrix",
    "threshold",
    "dbGenCode",
    "seg",
    "dbSoftMask",
    "dbHardMask",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBestHit",
    "sumStats",
    "xDropoffPrelimGapped",
    "xDropoffFinalGapped"
})
public class TBlastXConfigImpl implements TBlastXConfig {
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
      value = "queryGenCode",
      defaultValue = "1"
  )
  private byte queryGenCode;

  @JsonProperty("wordSize")
  private int wordSize;

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  private int maxIntronLength;

  @JsonProperty("matrix")
  private TBlastXMatrix matrix;

  @JsonProperty("threshold")
  private double threshold;

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  private byte dbGenCode;

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

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  private boolean sumStats;

  @JsonProperty("xDropoffPrelimGapped")
  private double xDropoffPrelimGapped;

  @JsonProperty("xDropoffFinalGapped")
  private double xDropoffFinalGapped;

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
      value = "queryGenCode",
      defaultValue = "1"
  )
  public byte getQueryGenCode() {
    return this.queryGenCode;
  }

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  public void setQueryGenCode(byte queryGenCode) {
    this.queryGenCode = queryGenCode;
  }

  @JsonProperty("wordSize")
  public int getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(int wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public int getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public void setMaxIntronLength(int maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }

  @JsonProperty("matrix")
  public TBlastXMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(TBlastXMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty("threshold")
  public double getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public byte getDbGenCode() {
    return this.dbGenCode;
  }

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public void setDbGenCode(byte dbGenCode) {
    this.dbGenCode = dbGenCode;
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
}
