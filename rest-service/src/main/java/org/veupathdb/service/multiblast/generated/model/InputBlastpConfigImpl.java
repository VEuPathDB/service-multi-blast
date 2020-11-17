package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastp")
@JsonPropertyOrder({
    "tool",
    "query",
    "queryLoc",
    "eValue",
    "lineLength",
    "sortHits",
    "seqIdList",
    "negativeSeqIdList",
    "taxIds",
    "negativeTaxIds",
    "taxIdList",
    "negativeTaxIdList",
    "sortHSPs",
    "qCovHSPPerc",
    "numDescriptions",
    "numAlignments",
    "maxTargetSeqs",
    "maxHSPs",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "dbSize",
    "searchSpace",
    "parseDefLines",
    "outFmt",
    "task",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "matrix",
    "threshold",
    "compBasedStats",
    "seg",
    "softMasking",
    "lcaseMasking",
    "dbSoftMask",
    "dbHardMask",
    "xdropGapFinal",
    "windowSize",
    "useSwTBack"
})
public class InputBlastpConfigImpl implements InputBlastpConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("query")
  private String query;

  @JsonProperty("queryLoc")
  private InputBlastLocation queryLoc;

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  private double eValue;

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  private int lineLength;

  @JsonProperty("sortHits")
  private InputHitSorting sortHits;

  @JsonProperty("seqIdList")
  private String seqIdList;

  @JsonProperty("negativeSeqIdList")
  private String negativeSeqIdList;

  @JsonProperty("taxIds")
  private String taxIds;

  @JsonProperty("negativeTaxIds")
  private String negativeTaxIds;

  @JsonProperty("taxIdList")
  private String taxIdList;

  @JsonProperty("negativeTaxIdList")
  private String negativeTaxIdList;

  @JsonProperty("sortHSPs")
  private InputHSPSorting sortHSPs;

  @JsonProperty("qCovHSPPerc")
  private Number qCovHSPPerc;

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  private short numDescriptions;

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  private int numAlignments;

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  private int maxTargetSeqs;

  @JsonProperty("maxHSPs")
  private short maxHSPs;

  @JsonProperty("cullingLimit")
  private int cullingLimit;

  @JsonProperty("bestHitOverhang")
  private double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private double bestHitScoreEdge;

  @JsonProperty("dbSize")
  private long dbSize;

  @JsonProperty("searchSpace")
  private long searchSpace;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private boolean parseDefLines;

  @JsonProperty("outFmt")
  private InputBlastOutFmt outFmt;

  @JsonProperty("task")
  private InputBlastpTask task;

  @JsonProperty("wordSize")
  private byte wordSize;

  @JsonProperty("gapOpen")
  private byte gapOpen;

  @JsonProperty(
      value = "gapExtend",
      defaultValue = "1"
  )
  private byte gapExtend;

  @JsonProperty("matrix")
  private InputBlastpScoringMatrix matrix;

  @JsonProperty("threshold")
  private byte threshold;

  @JsonProperty("compBasedStats")
  private InputBlastCompBasedStats compBasedStats;

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : false,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  private InputBlastSegMask seg;

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  private boolean softMasking;

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  private boolean lcaseMasking;

  @JsonProperty("dbSoftMask")
  private long dbSoftMask;

  @JsonProperty("dbHardMask")
  private long dbHardMask;

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "25.0"
  )
  private double xdropGapFinal;

  @JsonProperty("windowSize")
  private short windowSize;

  @JsonProperty(
      value = "useSwTBack",
      defaultValue = "false"
  )
  private boolean useSwTBack;

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

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  public double getEValue() {
    return this.eValue;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  public void setEValue(double eValue) {
    this.eValue = eValue;
  }

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  public int getLineLength() {
    return this.lineLength;
  }

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
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

  @JsonProperty("seqIdList")
  public String getSeqIdList() {
    return this.seqIdList;
  }

  @JsonProperty("seqIdList")
  public void setSeqIdList(String seqIdList) {
    this.seqIdList = seqIdList;
  }

  @JsonProperty("negativeSeqIdList")
  public String getNegativeSeqIdList() {
    return this.negativeSeqIdList;
  }

  @JsonProperty("negativeSeqIdList")
  public void setNegativeSeqIdList(String negativeSeqIdList) {
    this.negativeSeqIdList = negativeSeqIdList;
  }

  @JsonProperty("taxIds")
  public String getTaxIds() {
    return this.taxIds;
  }

  @JsonProperty("taxIds")
  public void setTaxIds(String taxIds) {
    this.taxIds = taxIds;
  }

  @JsonProperty("negativeTaxIds")
  public String getNegativeTaxIds() {
    return this.negativeTaxIds;
  }

  @JsonProperty("negativeTaxIds")
  public void setNegativeTaxIds(String negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
  }

  @JsonProperty("taxIdList")
  public String getTaxIdList() {
    return this.taxIdList;
  }

  @JsonProperty("taxIdList")
  public void setTaxIdList(String taxIdList) {
    this.taxIdList = taxIdList;
  }

  @JsonProperty("negativeTaxIdList")
  public String getNegativeTaxIdList() {
    return this.negativeTaxIdList;
  }

  @JsonProperty("negativeTaxIdList")
  public void setNegativeTaxIdList(String negativeTaxIdList) {
    this.negativeTaxIdList = negativeTaxIdList;
  }

  @JsonProperty("sortHSPs")
  public InputHSPSorting getSortHSPs() {
    return this.sortHSPs;
  }

  @JsonProperty("sortHSPs")
  public void setSortHSPs(InputHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
  }

  @JsonProperty("qCovHSPPerc")
  public Number getQCovHSPPerc() {
    return this.qCovHSPPerc;
  }

  @JsonProperty("qCovHSPPerc")
  public void setQCovHSPPerc(Number qCovHSPPerc) {
    this.qCovHSPPerc = qCovHSPPerc;
  }

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  public short getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  public void setNumDescriptions(short numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  public int getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  public void setNumAlignments(int numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  public int getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  public void setMaxTargetSeqs(int maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
  }

  @JsonProperty("maxHSPs")
  public short getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(short maxHSPs) {
    this.maxHSPs = maxHSPs;
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

  @JsonProperty("dbSize")
  public long getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(long dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public long getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(long searchSpace) {
    this.searchSpace = searchSpace;
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

  @JsonProperty("outFmt")
  public InputBlastOutFmt getOutFmt() {
    return this.outFmt;
  }

  @JsonProperty("outFmt")
  public void setOutFmt(InputBlastOutFmt outFmt) {
    this.outFmt = outFmt;
  }

  @JsonProperty("task")
  public InputBlastpTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(InputBlastpTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public byte getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(byte wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("gapOpen")
  public byte getGapOpen() {
    return this.gapOpen;
  }

  @JsonProperty("gapOpen")
  public void setGapOpen(byte gapOpen) {
    this.gapOpen = gapOpen;
  }

  @JsonProperty(
      value = "gapExtend",
      defaultValue = "1"
  )
  public byte getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty(
      value = "gapExtend",
      defaultValue = "1"
  )
  public void setGapExtend(byte gapExtend) {
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
  public byte getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold(byte threshold) {
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

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : false,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public InputBlastSegMask getSeg() {
    return this.seg;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : false,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public void setSeg(InputBlastSegMask seg) {
    this.seg = seg;
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
      value = "lcaseMasking",
      defaultValue = "false"
  )
  public boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  public void setLcaseMasking(boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("dbSoftMask")
  public long getDbSoftMask() {
    return this.dbSoftMask;
  }

  @JsonProperty("dbSoftMask")
  public void setDbSoftMask(long dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  @JsonProperty("dbHardMask")
  public long getDbHardMask() {
    return this.dbHardMask;
  }

  @JsonProperty("dbHardMask")
  public void setDbHardMask(long dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "25.0"
  )
  public double getXdropGapFinal() {
    return this.xdropGapFinal;
  }

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "25.0"
  )
  public void setXdropGapFinal(double xdropGapFinal) {
    this.xdropGapFinal = xdropGapFinal;
  }

  @JsonProperty("windowSize")
  public short getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(short windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty(
      value = "useSwTBack",
      defaultValue = "false"
  )
  public boolean getUseSwTBack() {
    return this.useSwTBack;
  }

  @JsonProperty(
      value = "useSwTBack",
      defaultValue = "false"
  )
  public void setUseSwTBack(boolean useSwTBack) {
    this.useSwTBack = useSwTBack;
  }
}
