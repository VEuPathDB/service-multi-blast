package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("tblastn")
@JsonDeserialize(
    as = InputTBlastnConfigImpl.class
)
public interface InputTBlastnConfig extends InputBlastConfig {
  String _DISCRIMINATOR_TYPE_NAME = "tblastn";

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
  InputTBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputTBlastnTask task);

  @JsonProperty("wordSize")
  byte getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(byte wordSize);

  @JsonProperty(
      value = "gapOpen",
      defaultValue = "11"
  )
  byte getGapOpen();

  @JsonProperty(
      value = "gapOpen",
      defaultValue = "11"
  )
  void setGapOpen(byte gapOpen);

  @JsonProperty(
      value = "gapExtend",
      defaultValue = "1"
  )
  byte getGapExtend();

  @JsonProperty(
      value = "gapExtend",
      defaultValue = "1"
  )
  void setGapExtend(byte gapExtend);

  @JsonProperty("matrix")
  InputBlastScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputBlastScoringMatrix matrix);

  @JsonProperty("threshold")
  byte getThreshold();

  @JsonProperty("threshold")
  void setThreshold(byte threshold);

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  InputBlastSegMask getSeg();

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  void setSeg(InputBlastSegMask seg);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
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
  long getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(long dbSoftMask);

  @JsonProperty("dbHardMask")
  long getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(long dbHardMask);

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "25.0"
  )
  double getXdropGapFinal();

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "25.0"
  )
  void setXdropGapFinal(double xdropGapFinal);

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

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  byte getDbGenCode();

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  void setDbGenCode(byte dbGenCode);

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  int getMaxIntronLength();

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  void setMaxIntronLength(int maxIntronLength);

  @JsonProperty("compBasedStats")
  InputBlastCompBasedStats getCompBasedStats();

  @JsonProperty("compBasedStats")
  void setCompBasedStats(InputBlastCompBasedStats compBasedStats);
}
