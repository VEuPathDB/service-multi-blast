package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonTypeName("tblastn")
@JsonDeserialize(
    as = InputTBlastnConfigImpl.class
)
public interface InputTBlastnConfig extends InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = InputBlastTool.TBLASTN;

  @JsonProperty("tool")
  InputBlastTool getTool();

  @JsonProperty("query")
  String getQuery();

  @JsonProperty("query")
  void setQuery(String query);

  @JsonProperty("queryLoc")
  InputBlastLocation getQueryLoc();

  @JsonProperty("queryLoc")
  void setQueryLoc(InputBlastLocation queryLoc);

  @JsonProperty("task")
  InputTBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputTBlastnTask task);

  @JsonProperty("eValue")
  String getEValue();

  @JsonProperty("eValue")
  void setEValue(String eValue);

  @JsonProperty("wordSize")
  int getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(int wordSize);

  @JsonProperty("gapOpen")
  byte getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(byte gapOpen);

  @JsonProperty("gapExtend")
  byte getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(byte gapExtend);

  @JsonProperty("dbGencode")
  byte getDbGencode();

  @JsonProperty("dbGencode")
  void setDbGencode(byte dbGencode);

  @JsonProperty("maxIntronLength")
  int getMaxIntronLength();

  @JsonProperty("maxIntronLength")
  void setMaxIntronLength(int maxIntronLength);

  @JsonProperty("matrix")
  InputTBlastnScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputTBlastnScoringMatrix matrix);

  @JsonProperty("threshold")
  double getThreshold();

  @JsonProperty("threshold")
  void setThreshold(double threshold);

  @JsonProperty("compBasedStats")
  InputBlastCompBasedStats getCompBasedStats();

  @JsonProperty("compBasedStats")
  void setCompBasedStats(InputBlastCompBasedStats compBasedStats);

  @JsonProperty("subjectLoc")
  InputBlastLocation getSubjectLoc();

  @JsonProperty("subjectLoc")
  void setSubjectLoc(InputBlastLocation subjectLoc);

  @JsonProperty("outFormat")
  InputBlastOutFmt getOutFormat();

  @JsonProperty("outFormat")
  void setOutFormat(InputBlastOutFmt outFormat);

  @JsonProperty("showGIs")
  boolean getShowGIs();

  @JsonProperty("showGIs")
  void setShowGIs(boolean showGIs);

  @JsonProperty("numDescriptions")
  int getNumDescriptions();

  @JsonProperty("numDescriptions")
  void setNumDescriptions(int numDescriptions);

  @JsonProperty("numAlignments")
  int getNumAlignments();

  @JsonProperty("numAlignments")
  void setNumAlignments(int numAlignments);

  @JsonProperty("lineLength")
  int getLineLength();

  @JsonProperty("lineLength")
  void setLineLength(int lineLength);

  @JsonProperty("sortHits")
  InputHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(InputHitSorting sortHits);

  @JsonProperty("sortHSPs")
  InputHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(InputHSPSorting sortHSPs);

  @JsonProperty("seg")
  InputBlastSegMask getSeg();

  @JsonProperty("seg")
  void setSeg(InputBlastSegMask seg);

  @JsonProperty("softMasking")
  boolean getSoftMasking();

  @JsonProperty("softMasking")
  void setSoftMasking(boolean softMasking);

  @JsonProperty("lcaseMasking")
  boolean getLcaseMasking();

  @JsonProperty("lcaseMasking")
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("taxIds")
  List<String> getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(List<String> taxIds);

  @JsonProperty("negativeTaxIds")
  List<String> getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(List<String> negativeTaxIds);

  @JsonProperty("dbSoftMask")
  String getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(String dbSoftMask);

  @JsonProperty("dbHardMask")
  String getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(String dbHardMask);

  @JsonProperty("qCovHspPerc")
  double getQCovHspPerc();

  @JsonProperty("qCovHspPerc")
  void setQCovHspPerc(double qCovHspPerc);

  @JsonProperty("maxHsps")
  int getMaxHsps();

  @JsonProperty("maxHsps")
  void setMaxHsps(int maxHsps);

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

  @JsonProperty("subjectBesthit")
  boolean getSubjectBesthit();

  @JsonProperty("subjectBesthit")
  void setSubjectBesthit(boolean subjectBesthit);

  @JsonProperty("maxTargetSeqs")
  int getMaxTargetSeqs();

  @JsonProperty("maxTargetSeqs")
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("dbSize")
  byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("sumStats")
  boolean getSumStats();

  @JsonProperty("sumStats")
  void setSumStats(boolean sumStats);

  @JsonProperty("xDropUngap")
  double getXDropUngap();

  @JsonProperty("xDropUngap")
  void setXDropUngap(double xDropUngap);

  @JsonProperty("xDropGap")
  double getXDropGap();

  @JsonProperty("xDropGap")
  void setXDropGap(double xDropGap);

  @JsonProperty("xDropGapFinal")
  double getXDropGapFinal();

  @JsonProperty("xDropGapFinal")
  void setXDropGapFinal(double xDropGapFinal);

  @JsonProperty("ungapped")
  boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped(boolean ungapped);

  @JsonProperty("windowSize")
  int getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(int windowSize);

  @JsonProperty("parseDeflines")
  boolean getParseDeflines();

  @JsonProperty("parseDeflines")
  void setParseDeflines(boolean parseDeflines);

  @JsonProperty("useSwTback")
  boolean getUseSwTback();

  @JsonProperty("useSwTback")
  void setUseSwTback(boolean useSwTback);

  @JsonProperty("in_pssm")
  String getInPssm();

  @JsonProperty("in_pssm")
  void setInPssm(String inPssm);
}
