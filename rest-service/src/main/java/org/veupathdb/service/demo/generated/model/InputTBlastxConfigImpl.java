package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastx")
@JsonPropertyOrder({
    "tool",
    "db",
    "query",
    "query_loc",
    "eValue",
    "subject",
    "subjectLoc",
    "showGIs",
    "numDescriptions",
    "numAlignments",
    "maxTargetSeqs",
    "maxHSPs",
    "html",
    "giList",
    "negativeGIList",
    "entrezQuery",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "dbSize",
    "searchSpace",
    "importSearchStrategy",
    "exportSearchStrategy",
    "parseDefLines",
    "numThreads",
    "remote",
    "outFmt",
    "wordSize",
    "matrix",
    "threshold",
    "seg",
    "softMasking",
    "lcaseMasking",
    "strand",
    "dbSoftMask",
    "dbHardMask",
    "queryGeneticCode",
    "dbGenCode",
    "maxIntronLength"
})
public class InputTBlastxConfigImpl implements InputTBlastxConfig {
  @JsonProperty("tool")
  private InputBlastTool tool;

  @JsonProperty("db")
  private String db;

  @JsonProperty("query")
  private String query;

  @JsonProperty("query_loc")
  private InputBlastLocation queryLoc;

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  private double eValue;

  @JsonProperty("subject")
  private String subject;

  @JsonProperty("subjectLoc")
  private InputBlastLocation subjectLoc;

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  private boolean showGIs;

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

  @JsonProperty(
      value = "html",
      defaultValue = "false"
  )
  private boolean html;

  @JsonProperty("giList")
  private String giList;

  @JsonProperty("negativeGIList")
  private String negativeGIList;

  @JsonProperty("entrezQuery")
  private String entrezQuery;

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

  @JsonProperty("importSearchStrategy")
  private String importSearchStrategy;

  @JsonProperty("exportSearchStrategy")
  private String exportSearchStrategy;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private boolean parseDefLines;

  @JsonProperty(
      value = "numThreads",
      defaultValue = "1"
  )
  private byte numThreads;

  @JsonProperty(
      value = "remote",
      defaultValue = "false"
  )
  private boolean remote;

  @JsonProperty("outFmt")
  private InputBlastOutFmt outFmt;

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  private byte wordSize;

  @JsonProperty("matrix")
  private InputBlastScoringMatrix matrix;

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  private byte threshold;

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
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

  @JsonProperty("strand")
  private InputBlastStrand strand;

  @JsonProperty("dbSoftMask")
  private long dbSoftMask;

  @JsonProperty("dbHardMask")
  private long dbHardMask;

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  private byte queryGeneticCode;

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  private byte dbGenCode;

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  private int maxIntronLength;

  @JsonProperty("tool")
  public InputBlastTool getTool() {
    return this.tool;
  }

  @JsonProperty("tool")
  public void setTool(InputBlastTool tool) {
    this.tool = tool;
  }

  @JsonProperty("db")
  public String getDb() {
    return this.db;
  }

  @JsonProperty("db")
  public void setDb(String db) {
    this.db = db;
  }

  @JsonProperty("query")
  public String getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  @JsonProperty("query_loc")
  public InputBlastLocation getQueryLoc() {
    return this.queryLoc;
  }

  @JsonProperty("query_loc")
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

  @JsonProperty("subject")
  public String getSubject() {
    return this.subject;
  }

  @JsonProperty("subject")
  public void setSubject(String subject) {
    this.subject = subject;
  }

  @JsonProperty("subjectLoc")
  public InputBlastLocation getSubjectLoc() {
    return this.subjectLoc;
  }

  @JsonProperty("subjectLoc")
  public void setSubjectLoc(InputBlastLocation subjectLoc) {
    this.subjectLoc = subjectLoc;
  }

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  public boolean getShowGIs() {
    return this.showGIs;
  }

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  public void setShowGIs(boolean showGIs) {
    this.showGIs = showGIs;
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

  @JsonProperty(
      value = "html",
      defaultValue = "false"
  )
  public boolean getHtml() {
    return this.html;
  }

  @JsonProperty(
      value = "html",
      defaultValue = "false"
  )
  public void setHtml(boolean html) {
    this.html = html;
  }

  @JsonProperty("giList")
  public String getGiList() {
    return this.giList;
  }

  @JsonProperty("giList")
  public void setGiList(String giList) {
    this.giList = giList;
  }

  @JsonProperty("negativeGIList")
  public String getNegativeGIList() {
    return this.negativeGIList;
  }

  @JsonProperty("negativeGIList")
  public void setNegativeGIList(String negativeGIList) {
    this.negativeGIList = negativeGIList;
  }

  @JsonProperty("entrezQuery")
  public String getEntrezQuery() {
    return this.entrezQuery;
  }

  @JsonProperty("entrezQuery")
  public void setEntrezQuery(String entrezQuery) {
    this.entrezQuery = entrezQuery;
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

  @JsonProperty("importSearchStrategy")
  public String getImportSearchStrategy() {
    return this.importSearchStrategy;
  }

  @JsonProperty("importSearchStrategy")
  public void setImportSearchStrategy(String importSearchStrategy) {
    this.importSearchStrategy = importSearchStrategy;
  }

  @JsonProperty("exportSearchStrategy")
  public String getExportSearchStrategy() {
    return this.exportSearchStrategy;
  }

  @JsonProperty("exportSearchStrategy")
  public void setExportSearchStrategy(String exportSearchStrategy) {
    this.exportSearchStrategy = exportSearchStrategy;
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
      value = "numThreads",
      defaultValue = "1"
  )
  public byte getNumThreads() {
    return this.numThreads;
  }

  @JsonProperty(
      value = "numThreads",
      defaultValue = "1"
  )
  public void setNumThreads(byte numThreads) {
    this.numThreads = numThreads;
  }

  @JsonProperty(
      value = "remote",
      defaultValue = "false"
  )
  public boolean getRemote() {
    return this.remote;
  }

  @JsonProperty(
      value = "remote",
      defaultValue = "false"
  )
  public void setRemote(boolean remote) {
    this.remote = remote;
  }

  @JsonProperty("outFmt")
  public InputBlastOutFmt getOutFmt() {
    return this.outFmt;
  }

  @JsonProperty("outFmt")
  public void setOutFmt(InputBlastOutFmt outFmt) {
    this.outFmt = outFmt;
  }

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  public byte getWordSize() {
    return this.wordSize;
  }

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  public void setWordSize(byte wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("matrix")
  public InputBlastScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(InputBlastScoringMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  public byte getThreshold() {
    return this.threshold;
  }

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  public void setThreshold(byte threshold) {
    this.threshold = threshold;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
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
              + "\"enabled\" : true,\n"
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

  @JsonProperty("strand")
  public InputBlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(InputBlastStrand strand) {
    this.strand = strand;
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
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  public byte getQueryGeneticCode() {
    return this.queryGeneticCode;
  }

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  public void setQueryGeneticCode(byte queryGeneticCode) {
    this.queryGeneticCode = queryGeneticCode;
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
}
