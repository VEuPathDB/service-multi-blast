package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("blastn")
@JsonDeserialize(
    as = InputBlastnConfigImpl.class
)
public interface InputBlastnConfig extends InputBlastConfig {
  String _DISCRIMINATOR_TYPE_NAME = "blastn";

  @JsonProperty("tool")
  InputBlastTool getTool();

  @JsonProperty("query")
  String getQuery();

  @JsonProperty("query")
  void setQuery(String query);

  @JsonProperty("query_loc")
  InputBlastLocation getQueryLoc();

  @JsonProperty("query_loc")
  void setQueryLoc(InputBlastLocation queryLoc);

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  double getEValue();

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  void setEValue(double eValue);

  @JsonProperty("subject")
  String getSubject();

  @JsonProperty("subject")
  void setSubject(String subject);

  @JsonProperty("subjectLoc")
  InputBlastLocation getSubjectLoc();

  @JsonProperty("subjectLoc")
  void setSubjectLoc(InputBlastLocation subjectLoc);

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  boolean getShowGIs();

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  void setShowGIs(boolean showGIs);

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  short getNumDescriptions();

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  void setNumDescriptions(short numDescriptions);

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  int getNumAlignments();

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  void setNumAlignments(int numAlignments);

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  int getMaxTargetSeqs();

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("maxHSPs")
  short getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(short maxHSPs);

  @JsonProperty(
      value = "html",
      defaultValue = "false"
  )
  boolean getHtml();

  @JsonProperty(
      value = "html",
      defaultValue = "false"
  )
  void setHtml(boolean html);

  @JsonProperty("giList")
  String getGiList();

  @JsonProperty("giList")
  void setGiList(String giList);

  @JsonProperty("negativeGIList")
  String getNegativeGIList();

  @JsonProperty("negativeGIList")
  void setNegativeGIList(String negativeGIList);

  @JsonProperty("entrezQuery")
  String getEntrezQuery();

  @JsonProperty("entrezQuery")
  void setEntrezQuery(String entrezQuery);

  @JsonProperty("cullingLimit")
  int getCullingLimit();

  @JsonProperty("cullingLimit")
  void setCullingLimit(int cullingLimit);

  @JsonProperty("bestHitOverhang")
  double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang(double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
  double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge(double bestHitScoreEdge);

  @JsonProperty("dbSize")
  long getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(long dbSize);

  @JsonProperty("searchSpace")
  long getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(long searchSpace);

  @JsonProperty("importSearchStrategy")
  String getImportSearchStrategy();

  @JsonProperty("importSearchStrategy")
  void setImportSearchStrategy(String importSearchStrategy);

  @JsonProperty("exportSearchStrategy")
  String getExportSearchStrategy();

  @JsonProperty("exportSearchStrategy")
  void setExportSearchStrategy(String exportSearchStrategy);

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  boolean getParseDefLines();

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  void setParseDefLines(boolean parseDefLines);

  @JsonProperty(
      value = "numThreads",
      defaultValue = "1"
  )
  byte getNumThreads();

  @JsonProperty(
      value = "numThreads",
      defaultValue = "1"
  )
  void setNumThreads(byte numThreads);

  @JsonProperty(
      value = "remote",
      defaultValue = "false"
  )
  boolean getRemote();

  @JsonProperty(
      value = "remote",
      defaultValue = "false"
  )
  void setRemote(boolean remote);

  @JsonProperty("outFmt")
  InputBlastOutFmt getOutFmt();

  @JsonProperty("outFmt")
  void setOutFmt(InputBlastOutFmt outFmt);

  @JsonProperty("task")
  InputBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputBlastnTask task);

  @JsonProperty("wordSize")
  short getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(short wordSize);

  @JsonProperty("gapOpen")
  byte getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(byte gapOpen);

  @JsonProperty("gapExtend")
  byte getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(byte gapExtend);

  @JsonProperty("reward")
  byte getReward();

  @JsonProperty("reward")
  void setReward(byte reward);

  @JsonProperty("penalty")
  byte getPenalty();

  @JsonProperty("penalty")
  void setPenalty(byte penalty);

  @JsonProperty("strand")
  InputBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(InputBlastStrand strand);

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  InputBlastnDust getDust();

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  void setDust(InputBlastnDust dust);

  @JsonProperty("filteringDb")
  String getFilteringDb();

  @JsonProperty("filteringDb")
  void setFilteringDb(String filteringDb);

  @JsonProperty("windowMaskerTaxid")
  long getWindowMaskerTaxid();

  @JsonProperty("windowMaskerTaxid")
  void setWindowMaskerTaxid(long windowMaskerTaxid);

  @JsonProperty("windowMaskerDb")
  String getWindowMaskerDb();

  @JsonProperty("windowMaskerDb")
  void setWindowMaskerDb(String windowMaskerDb);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
  )
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
  )
  void setSoftMasking(boolean softMasking);

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  boolean getLcaseMasking();

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("dbSoftMask")
  int getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(int dbSoftMask);

  @JsonProperty("dbHardMask")
  long getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(long dbHardMask);

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0"
  )
  int getPercIdentity();

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0"
  )
  void setPercIdentity(int percIdentity);

  @JsonProperty("templateType")
  InputBlastDcTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(InputBlastDcTemplateType templateType);

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  int getTemplateLength();

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  void setTemplateLength(int templateLength);

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  boolean getUseIndex();

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  void setUseIndex(boolean useIndex);

  @JsonProperty("indexName")
  String getIndexName();

  @JsonProperty("indexName")
  void setIndexName(String indexName);

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  double getXdropUngap();

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  void setXdropUngap(double xdropUngap);

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  double getXdropGap();

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  void setXdropGap(double xdropGap);

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  double getXdropGapFinal();

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  void setXdropGapFinal(double xdropGapFinal);

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  boolean getNoGreedy();

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  void setNoGreedy(boolean noGreedy);

  @JsonProperty("minRawGappedScore")
  int getMinRawGappedScore();

  @JsonProperty("minRawGappedScore")
  void setMinRawGappedScore(int minRawGappedScore);

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  boolean getUngapped();

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  void setUngapped(boolean ungapped);

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  short getWindowSize();

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  void setWindowSize(short windowSize);
}
