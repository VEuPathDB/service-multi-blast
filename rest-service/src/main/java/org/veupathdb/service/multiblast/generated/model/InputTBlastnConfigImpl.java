package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastn")
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
    "task",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "dbGencode",
    "maxIntronLength",
    "matrix",
    "threshold",
    "compBasedStats",
    "subjectLoc",
    "seg",
    "softMasking",
    "taxIds",
    "negativeTaxIds",
    "dbSoftMask",
    "dbHardMask",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBesthit",
    "sumStats",
    "xDropGap",
    "xDropGapFinal",
    "ungapped",
    "windowSize",
    "useSWTraceback",
    "in_pssm"
})
public class InputTBlastnConfigImpl implements InputTBlastnConfig {
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
  private Boolean showGIs;

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
  private Boolean lcaseMasking;

  @JsonProperty("qCovHSPPerc")
  private Double qCovHSPPerc;

  @JsonProperty("maxHSPs")
  private Integer maxHSPs;

  @JsonProperty("maxTargetSeqs")
  private Integer maxTargetSeqs;

  @JsonProperty("dbSize")
  private Byte dbSize;

  @JsonProperty("searchSpace")
  private Byte searchSpace;

  @JsonProperty("xDropUngap")
  private Double xDropUngap;

  @JsonProperty("parseDefLines")
  private Boolean parseDefLines;

  @JsonProperty("task")
  private InputTBlastnTask task;

  @JsonProperty("wordSize")
  private Integer wordSize;

  @JsonProperty("gapOpen")
  private Byte gapOpen;

  @JsonProperty("gapExtend")
  private Byte gapExtend;

  @JsonProperty("dbGencode")
  private Byte dbGencode;

  @JsonProperty("maxIntronLength")
  private Integer maxIntronLength;

  @JsonProperty("matrix")
  private InputTBlastnScoringMatrix matrix;

  @JsonProperty("threshold")
  private Double threshold;

  @JsonProperty("compBasedStats")
  private InputBlastCompBasedStats compBasedStats;

  @JsonProperty("subjectLoc")
  private InputBlastLocation subjectLoc;

  @JsonProperty("seg")
  private InputBlastSegMask seg;

  @JsonProperty("softMasking")
  private Boolean softMasking;

  @JsonProperty("taxIds")
  private List<String> taxIds;

  @JsonProperty("negativeTaxIds")
  private List<String> negativeTaxIds;

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

  @JsonProperty("subjectBesthit")
  private Boolean subjectBesthit;

  @JsonProperty("sumStats")
  private Boolean sumStats;

  @JsonProperty("xDropGap")
  private Double xDropGap;

  @JsonProperty("xDropGapFinal")
  private Double xDropGapFinal;

  @JsonProperty("ungapped")
  private Boolean ungapped;

  @JsonProperty("windowSize")
  private Integer windowSize;

  @JsonProperty("useSWTraceback")
  private Boolean useSWTraceback;

  @JsonProperty("in_pssm")
  private String inPssm;

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
  public Boolean getShowGIs() {
    return this.showGIs;
  }

  @JsonProperty("showGIs")
  public void setShowGIs(boolean showGIs) {
    this.showGIs = showGIs;
  }

  @JsonProperty("numDescriptions")
  public Integer getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty("numDescriptions")
  public void setNumDescriptions(int numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty("numAlignments")
  public Integer getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty("numAlignments")
  public void setNumAlignments(int numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty("lineLength")
  public Integer getLineLength() {
    return this.lineLength;
  }

  @JsonProperty("lineLength")
  public void setLineLength(int lineLength) {
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
  public Boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty("lcaseMasking")
  public void setLcaseMasking(boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("qCovHSPPerc")
  public Double getQCovHSPPerc() {
    return this.qCovHSPPerc;
  }

  @JsonProperty("qCovHSPPerc")
  public void setQCovHSPPerc(double qCovHSPPerc) {
    this.qCovHSPPerc = qCovHSPPerc;
  }

  @JsonProperty("maxHSPs")
  public Integer getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(int maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty("maxTargetSeqs")
  public Integer getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty("maxTargetSeqs")
  public void setMaxTargetSeqs(int maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
  }

  @JsonProperty("dbSize")
  public Byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public Byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropUngap")
  public Double getXDropUngap() {
    return this.xDropUngap;
  }

  @JsonProperty("xDropUngap")
  public void setXDropUngap(double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  @JsonProperty("parseDefLines")
  public Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty("parseDefLines")
  public void setParseDefLines(boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }

  @JsonProperty("task")
  public InputTBlastnTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(InputTBlastnTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public Integer getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(int wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("gapOpen")
  public Byte getGapOpen() {
    return this.gapOpen;
  }

  @JsonProperty("gapOpen")
  public void setGapOpen(byte gapOpen) {
    this.gapOpen = gapOpen;
  }

  @JsonProperty("gapExtend")
  public Byte getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty("gapExtend")
  public void setGapExtend(byte gapExtend) {
    this.gapExtend = gapExtend;
  }

  @JsonProperty("dbGencode")
  public Byte getDbGencode() {
    return this.dbGencode;
  }

  @JsonProperty("dbGencode")
  public void setDbGencode(byte dbGencode) {
    this.dbGencode = dbGencode;
  }

  @JsonProperty("maxIntronLength")
  public Integer getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty("maxIntronLength")
  public void setMaxIntronLength(int maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }

  @JsonProperty("matrix")
  public InputTBlastnScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(InputTBlastnScoringMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty("threshold")
  public Double getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  @JsonProperty("compBasedStats")
  public InputBlastCompBasedStats getCompBasedStats() {
    return this.compBasedStats;
  }

  @JsonProperty("compBasedStats")
  public void setCompBasedStats(InputBlastCompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
  }

  @JsonProperty("subjectLoc")
  public InputBlastLocation getSubjectLoc() {
    return this.subjectLoc;
  }

  @JsonProperty("subjectLoc")
  public void setSubjectLoc(InputBlastLocation subjectLoc) {
    this.subjectLoc = subjectLoc;
  }

  @JsonProperty("seg")
  public InputBlastSegMask getSeg() {
    return this.seg;
  }

  @JsonProperty("seg")
  public void setSeg(InputBlastSegMask seg) {
    this.seg = seg;
  }

  @JsonProperty("softMasking")
  public Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty("softMasking")
  public void setSoftMasking(boolean softMasking) {
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

  @JsonProperty("cullingLimit")
  public Integer getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(int cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  @JsonProperty("bestHitOverhang")
  public Double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang(double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public Double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge(double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty("subjectBesthit")
  public Boolean getSubjectBesthit() {
    return this.subjectBesthit;
  }

  @JsonProperty("subjectBesthit")
  public void setSubjectBesthit(boolean subjectBesthit) {
    this.subjectBesthit = subjectBesthit;
  }

  @JsonProperty("sumStats")
  public Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty("sumStats")
  public void setSumStats(boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropGap")
  public Double getXDropGap() {
    return this.xDropGap;
  }

  @JsonProperty("xDropGap")
  public void setXDropGap(double xDropGap) {
    this.xDropGap = xDropGap;
  }

  @JsonProperty("xDropGapFinal")
  public Double getXDropGapFinal() {
    return this.xDropGapFinal;
  }

  @JsonProperty("xDropGapFinal")
  public void setXDropGapFinal(double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
  }

  @JsonProperty("ungapped")
  public Boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped(boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty("useSWTraceback")
  public Boolean getUseSWTraceback() {
    return this.useSWTraceback;
  }

  @JsonProperty("useSWTraceback")
  public void setUseSWTraceback(boolean useSWTraceback) {
    this.useSWTraceback = useSWTraceback;
  }

  @JsonProperty("in_pssm")
  public String getInPssm() {
    return this.inPssm;
  }

  @JsonProperty("in_pssm")
  public void setInPssm(String inPssm) {
    this.inPssm = inPssm;
  }
}
