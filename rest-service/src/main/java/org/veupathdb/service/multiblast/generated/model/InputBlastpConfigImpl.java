package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastp")
@JsonPropertyOrder({
    "tool",
    "query",
    "queryLoc",
    "task",
    "eValue",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "matrix",
    "threshold",
    "compBasedStats",
    "subjectLoc",
    "outFormat",
    "showGIs",
    "numDescriptions",
    "numAlignments",
    "lineLength",
    "sortHits",
    "sortHSPs",
    "seg",
    "softMasking",
    "lcaseMasking",
    "taxIds",
    "negativeTaxIds",
    "dbSoftMask",
    "dbHardMask",
    "qCovHspPerc",
    "maxHsps",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "subjectBesthit",
    "maxTargetSeqs",
    "dbSize",
    "searchSpace",
    "xDropUngap",
    "xDropGap",
    "xDropGapFinal",
    "windowSize",
    "ungapped",
    "parseDeflines",
    "useSwTback"
})
public class InputBlastpConfigImpl implements InputBlastpConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("query")
  private String query;

  @JsonProperty("queryLoc")
  private InputBlastLocation queryLoc;

  @JsonProperty("task")
  private InputBlastpTask task;

  @JsonProperty("eValue")
  private String eValue;

  @JsonProperty("wordSize")
  private int wordSize;

  @JsonProperty("gapOpen")
  private int gapOpen;

  @JsonProperty("gapExtend")
  private int gapExtend;

  @JsonProperty("matrix")
  private InputBlastpScoringMatrix matrix;

  @JsonProperty("threshold")
  private double threshold;

  @JsonProperty("compBasedStats")
  private InputBlastCompBasedStats compBasedStats;

  @JsonProperty("subjectLoc")
  private InputBlastLocation subjectLoc;

  @JsonProperty("outFormat")
  private InputBlastOutFmt outFormat;

  @JsonProperty("showGIs")
  private boolean showGIs;

  @JsonProperty("numDescriptions")
  private int numDescriptions;

  @JsonProperty("numAlignments")
  private int numAlignments;

  @JsonProperty("lineLength")
  private int lineLength;

  @JsonProperty("sortHits")
  private InputHitSorting sortHits;

  @JsonProperty("sortHSPs")
  private InputHSPSorting sortHSPs;

  @JsonProperty("seg")
  private InputBlastSegMask seg;

  @JsonProperty("softMasking")
  private boolean softMasking;

  @JsonProperty("lcaseMasking")
  private boolean lcaseMasking;

  @JsonProperty("taxIds")
  private List<String> taxIds;

  @JsonProperty("negativeTaxIds")
  private List<String> negativeTaxIds;

  @JsonProperty("dbSoftMask")
  private String dbSoftMask;

  @JsonProperty("dbHardMask")
  private String dbHardMask;

  @JsonProperty("qCovHspPerc")
  private double qCovHspPerc;

  @JsonProperty("maxHsps")
  private int maxHsps;

  @JsonProperty("cullingLimit")
  private int cullingLimit;

  @JsonProperty("bestHitOverhang")
  private double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private double bestHitScoreEdge;

  @JsonProperty("subjectBesthit")
  private boolean subjectBesthit;

  @JsonProperty("maxTargetSeqs")
  private int maxTargetSeqs;

  @JsonProperty("dbSize")
  private byte dbSize;

  @JsonProperty("searchSpace")
  private byte searchSpace;

  @JsonProperty("xDropUngap")
  private double xDropUngap;

  @JsonProperty("xDropGap")
  private double xDropGap;

  @JsonProperty("xDropGapFinal")
  private double xDropGapFinal;

  @JsonProperty("windowSize")
  private int windowSize;

  @JsonProperty("ungapped")
  private boolean ungapped;

  @JsonProperty("parseDeflines")
  private boolean parseDeflines;

  @JsonProperty("useSwTback")
  private boolean useSwTback;

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

  @JsonProperty("task")
  public InputBlastpTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(InputBlastpTask task) {
    this.task = task;
  }

  @JsonProperty("eValue")
  public String getEValue() {
    return this.eValue;
  }

  @JsonProperty("eValue")
  public void setEValue(String eValue) {
    this.eValue = eValue;
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

  @JsonProperty("matrix")
  public InputBlastpScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(InputBlastpScoringMatrix matrix) {
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

  @JsonProperty("outFormat")
  public InputBlastOutFmt getOutFormat() {
    return this.outFormat;
  }

  @JsonProperty("outFormat")
  public void setOutFormat(InputBlastOutFmt outFormat) {
    this.outFormat = outFormat;
  }

  @JsonProperty("showGIs")
  public boolean getShowGIs() {
    return this.showGIs;
  }

  @JsonProperty("showGIs")
  public void setShowGIs(boolean showGIs) {
    this.showGIs = showGIs;
  }

  @JsonProperty("numDescriptions")
  public int getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty("numDescriptions")
  public void setNumDescriptions(int numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty("numAlignments")
  public int getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty("numAlignments")
  public void setNumAlignments(int numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty("lineLength")
  public int getLineLength() {
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

  @JsonProperty("seg")
  public InputBlastSegMask getSeg() {
    return this.seg;
  }

  @JsonProperty("seg")
  public void setSeg(InputBlastSegMask seg) {
    this.seg = seg;
  }

  @JsonProperty("softMasking")
  public boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty("softMasking")
  public void setSoftMasking(boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty("lcaseMasking")
  public boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty("lcaseMasking")
  public void setLcaseMasking(boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
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

  @JsonProperty("qCovHspPerc")
  public double getQCovHspPerc() {
    return this.qCovHspPerc;
  }

  @JsonProperty("qCovHspPerc")
  public void setQCovHspPerc(double qCovHspPerc) {
    this.qCovHspPerc = qCovHspPerc;
  }

  @JsonProperty("maxHsps")
  public int getMaxHsps() {
    return this.maxHsps;
  }

  @JsonProperty("maxHsps")
  public void setMaxHsps(int maxHsps) {
    this.maxHsps = maxHsps;
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

  @JsonProperty("subjectBesthit")
  public boolean getSubjectBesthit() {
    return this.subjectBesthit;
  }

  @JsonProperty("subjectBesthit")
  public void setSubjectBesthit(boolean subjectBesthit) {
    this.subjectBesthit = subjectBesthit;
  }

  @JsonProperty("maxTargetSeqs")
  public int getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty("maxTargetSeqs")
  public void setMaxTargetSeqs(int maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
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

  @JsonProperty("xDropUngap")
  public double getXDropUngap() {
    return this.xDropUngap;
  }

  @JsonProperty("xDropUngap")
  public void setXDropUngap(double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  @JsonProperty("xDropGap")
  public double getXDropGap() {
    return this.xDropGap;
  }

  @JsonProperty("xDropGap")
  public void setXDropGap(double xDropGap) {
    this.xDropGap = xDropGap;
  }

  @JsonProperty("xDropGapFinal")
  public double getXDropGapFinal() {
    return this.xDropGapFinal;
  }

  @JsonProperty("xDropGapFinal")
  public void setXDropGapFinal(double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
  }

  @JsonProperty("windowSize")
  public int getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty("ungapped")
  public boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped(boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty("parseDeflines")
  public boolean getParseDeflines() {
    return this.parseDeflines;
  }

  @JsonProperty("parseDeflines")
  public void setParseDeflines(boolean parseDeflines) {
    this.parseDeflines = parseDeflines;
  }

  @JsonProperty("useSwTback")
  public boolean getUseSwTback() {
    return this.useSwTback;
  }

  @JsonProperty("useSwTback")
  public void setUseSwTback(boolean useSwTback) {
    this.useSwTback = useSwTback;
  }
}
