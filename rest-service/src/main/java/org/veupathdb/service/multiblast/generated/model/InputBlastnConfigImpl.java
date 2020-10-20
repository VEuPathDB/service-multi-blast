package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
@JsonPropertyOrder({
    "tool",
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
    "task",
    "wordSize",
    "gapOpen",
    "gapExtend",
    "reward",
    "penalty",
    "strand",
    "dust",
    "filteringDb",
    "windowMaskerTaxid",
    "windowMaskerDb",
    "softMasking",
    "lcaseMasking",
    "dbSoftMask",
    "dbHardMask",
    "percIdentity",
    "templateType",
    "templateLength",
    "useIndex",
    "indexName",
    "xdropUngap",
    "xdropGap",
    "xdropGapFinal",
    "noGreedy",
    "minRawGappedScore",
    "ungapped",
    "windowSize"
})
public class InputBlastnConfigImpl implements InputBlastnConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

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

  @JsonProperty("task")
  private InputBlastnTask task;

  @JsonProperty("wordSize")
  private short wordSize;

  @JsonProperty("gapOpen")
  private byte gapOpen;

  @JsonProperty("gapExtend")
  private byte gapExtend;

  @JsonProperty("reward")
  private byte reward;

  @JsonProperty("penalty")
  private byte penalty;

  @JsonProperty("strand")
  private InputBlastStrand strand;

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  private InputBlastnDust dust;

  @JsonProperty("filteringDb")
  private String filteringDb;

  @JsonProperty("windowMaskerTaxid")
  private long windowMaskerTaxid;

  @JsonProperty("windowMaskerDb")
  private String windowMaskerDb;

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
  )
  private boolean softMasking;

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  private boolean lcaseMasking;

  @JsonProperty("dbSoftMask")
  private int dbSoftMask;

  @JsonProperty("dbHardMask")
  private long dbHardMask;

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0"
  )
  private int percIdentity;

  @JsonProperty("templateType")
  private InputBlastDcTemplateType templateType;

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  private int templateLength;

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  private boolean useIndex;

  @JsonProperty("indexName")
  private String indexName;

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  private double xdropUngap;

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  private double xdropGap;

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  private double xdropGapFinal;

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  private boolean noGreedy;

  @JsonProperty("minRawGappedScore")
  private int minRawGappedScore;

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  private boolean ungapped;

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  private short windowSize;

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

  @JsonProperty("task")
  public InputBlastnTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(InputBlastnTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public short getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(short wordSize) {
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

  @JsonProperty("gapExtend")
  public byte getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty("gapExtend")
  public void setGapExtend(byte gapExtend) {
    this.gapExtend = gapExtend;
  }

  @JsonProperty("reward")
  public byte getReward() {
    return this.reward;
  }

  @JsonProperty("reward")
  public void setReward(byte reward) {
    this.reward = reward;
  }

  @JsonProperty("penalty")
  public byte getPenalty() {
    return this.penalty;
  }

  @JsonProperty("penalty")
  public void setPenalty(byte penalty) {
    this.penalty = penalty;
  }

  @JsonProperty("strand")
  public InputBlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(InputBlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  public InputBlastnDust getDust() {
    return this.dust;
  }

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  public void setDust(InputBlastnDust dust) {
    this.dust = dust;
  }

  @JsonProperty("filteringDb")
  public String getFilteringDb() {
    return this.filteringDb;
  }

  @JsonProperty("filteringDb")
  public void setFilteringDb(String filteringDb) {
    this.filteringDb = filteringDb;
  }

  @JsonProperty("windowMaskerTaxid")
  public long getWindowMaskerTaxid() {
    return this.windowMaskerTaxid;
  }

  @JsonProperty("windowMaskerTaxid")
  public void setWindowMaskerTaxid(long windowMaskerTaxid) {
    this.windowMaskerTaxid = windowMaskerTaxid;
  }

  @JsonProperty("windowMaskerDb")
  public String getWindowMaskerDb() {
    return this.windowMaskerDb;
  }

  @JsonProperty("windowMaskerDb")
  public void setWindowMaskerDb(String windowMaskerDb) {
    this.windowMaskerDb = windowMaskerDb;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
  )
  public boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
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
  public int getDbSoftMask() {
    return this.dbSoftMask;
  }

  @JsonProperty("dbSoftMask")
  public void setDbSoftMask(int dbSoftMask) {
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
      value = "percIdentity",
      defaultValue = "0"
  )
  public int getPercIdentity() {
    return this.percIdentity;
  }

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0"
  )
  public void setPercIdentity(int percIdentity) {
    this.percIdentity = percIdentity;
  }

  @JsonProperty("templateType")
  public InputBlastDcTemplateType getTemplateType() {
    return this.templateType;
  }

  @JsonProperty("templateType")
  public void setTemplateType(InputBlastDcTemplateType templateType) {
    this.templateType = templateType;
  }

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  public int getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  public void setTemplateLength(int templateLength) {
    this.templateLength = templateLength;
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
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  public double getXdropUngap() {
    return this.xdropUngap;
  }

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  public void setXdropUngap(double xdropUngap) {
    this.xdropUngap = xdropUngap;
  }

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  public double getXdropGap() {
    return this.xdropGap;
  }

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  public void setXdropGap(double xdropGap) {
    this.xdropGap = xdropGap;
  }

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  public double getXdropGapFinal() {
    return this.xdropGapFinal;
  }

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  public void setXdropGapFinal(double xdropGapFinal) {
    this.xdropGapFinal = xdropGapFinal;
  }

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  public boolean getNoGreedy() {
    return this.noGreedy;
  }

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  public void setNoGreedy(boolean noGreedy) {
    this.noGreedy = noGreedy;
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
      value = "ungapped",
      defaultValue = "false"
  )
  public boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  public void setUngapped(boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  public short getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  public void setWindowSize(short windowSize) {
    this.windowSize = windowSize;
  }
}
