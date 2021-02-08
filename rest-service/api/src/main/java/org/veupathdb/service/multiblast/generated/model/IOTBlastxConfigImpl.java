package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastx")
@JsonPropertyOrder({
    "tool",
    "query",
    "queryLoc",
    "eValue",
    "outFormat",
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
    "queryGeneticCode",
    "wordSize",
    "maxIntronLength",
    "matrix",
    "threshold",
    "dbGencode",
    "seg",
    "softMasking",
    "taxIds",
    "negativeTaxIds",
    "dbSoftMask",
    "dbHardMask",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBestHit",
    "sumStats",
    "windowSize"
})
public class IOTBlastxConfigImpl implements IOTBlastxConfig {
  @JsonProperty("tool")
  private final IOBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("query")
  private String query;

  @JsonProperty("queryLoc")
  private IOBlastLocation queryLoc;

  @JsonProperty("eValue")
  private String eValue;

  @JsonProperty("outFormat")
  private IOBlastReportFormat outFormat;

  @JsonProperty("numDescriptions")
  private Integer numDescriptions;

  @JsonProperty("numAlignments")
  private Integer numAlignments;

  @JsonProperty("lineLength")
  private Integer lineLength;

  @JsonProperty("sortHits")
  private IOHitSorting sortHits;

  @JsonProperty("sortHSPs")
  private IOHSPSorting sortHSPs;

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
  private IOBlastStrand strand;

  @JsonProperty("queryGeneticCode")
  private  Byte queryGeneticCode;

  @JsonProperty("wordSize")
  private Integer wordSize;

  @JsonProperty("maxIntronLength")
  private Integer maxIntronLength;

  @JsonProperty("matrix")
  private IOTBlastxScoringMatrix matrix;

  @JsonProperty("threshold")
  private  Double threshold;

  @JsonProperty("dbGencode")
  private  Byte dbGencode;

  @JsonProperty("seg")
  private IOBlastSegMask seg;

  @JsonProperty("softMasking")
  private  Boolean softMasking;

  @JsonProperty("taxIds")
  private List<Integer> taxIds;

  @JsonProperty("negativeTaxIds")
  private List<Integer> negativeTaxIds;

  @JsonProperty("dbSoftMask")
  private String dbSoftMask;

  @JsonProperty("dbHardMask")
  private String dbHardMask;

  @JsonProperty("cullingLimit")
  private Integer cullingLimit;

  @JsonProperty("bestHitOverhang")
  private  Double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private  Double bestHitScoreEdge;

  @JsonProperty("subjectBestHit")
  private  Boolean subjectBestHit;

  @JsonProperty("sumStats")
  private  Boolean sumStats;

  @JsonProperty("windowSize")
  private Integer windowSize;

  @JsonProperty("tool")
  public IOBlastTool getTool() {
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
  public IOHitSorting getSortHits() {
    return this.sortHits;
  }

  @JsonProperty("sortHits")
  public void setSortHits(IOHitSorting sortHits) {
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
  public IOBlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(IOBlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty("queryGeneticCode")
  public  Byte getQueryGeneticCode() {
    return this.queryGeneticCode;
  }

  @JsonProperty("queryGeneticCode")
  public void setQueryGeneticCode (Byte queryGeneticCode) {
    this.queryGeneticCode = queryGeneticCode;
  }

  @JsonProperty("wordSize")
  public Integer getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(Integer wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("maxIntronLength")
  public Integer getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty("maxIntronLength")
  public void setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }

  @JsonProperty("matrix")
  public IOTBlastxScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(IOTBlastxScoringMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty("threshold")
  public  Double getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold (Double threshold) {
    this.threshold = threshold;
  }

  @JsonProperty("dbGencode")
  public  Byte getDbGencode() {
    return this.dbGencode;
  }

  @JsonProperty("dbGencode")
  public void setDbGencode (Byte dbGencode) {
    this.dbGencode = dbGencode;
  }

  @JsonProperty("seg")
  public IOBlastSegMask getSeg() {
    return this.seg;
  }

  @JsonProperty("seg")
  public void setSeg(IOBlastSegMask seg) {
    this.seg = seg;
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

  @JsonProperty("subjectBestHit")
  public  Boolean getSubjectBestHit() {
    return this.subjectBestHit;
  }

  @JsonProperty("subjectBestHit")
  public void setSubjectBestHit (Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  @JsonProperty("sumStats")
  public  Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty("sumStats")
  public void setSumStats (Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
  }
}