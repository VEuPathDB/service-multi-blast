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

  @JsonProperty("eValue")
  String getEValue();

  @JsonProperty("eValue")
  void setEValue(String eValue);

  @JsonProperty("outFormat")
  InputBlastOutFmt getOutFormat();

  @JsonProperty("outFormat")
  void setOutFormat(InputBlastOutFmt outFormat);

  @JsonProperty("showGIs")
  Boolean getShowGIs();

  @JsonProperty("showGIs")
  void setShowGIs(boolean showGIs);

  @JsonProperty("numDescriptions")
  Integer getNumDescriptions();

  @JsonProperty("numDescriptions")
  void setNumDescriptions(int numDescriptions);

  @JsonProperty("numAlignments")
  Integer getNumAlignments();

  @JsonProperty("numAlignments")
  void setNumAlignments(int numAlignments);

  @JsonProperty("lineLength")
  Integer getLineLength();

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

  @JsonProperty("lcaseMasking")
  Boolean getLcaseMasking();

  @JsonProperty("lcaseMasking")
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("qCovHSPPerc")
  Double getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc(double qCovHSPPerc);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(int maxHSPs);

  @JsonProperty("maxTargetSeqs")
  Integer getMaxTargetSeqs();

  @JsonProperty("maxTargetSeqs")
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("dbSize")
  Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("xDropUngap")
  Double getXDropUngap();

  @JsonProperty("xDropUngap")
  void setXDropUngap(double xDropUngap);

  @JsonProperty("parseDefLines")
  Boolean getParseDefLines();

  @JsonProperty("parseDefLines")
  void setParseDefLines(boolean parseDefLines);

  @JsonProperty("task")
  InputTBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputTBlastnTask task);

  @JsonProperty("wordSize")
  Integer getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(int wordSize);

  @JsonProperty("gapOpen")
  Byte getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(byte gapOpen);

  @JsonProperty("gapExtend")
  Byte getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(byte gapExtend);

  @JsonProperty("dbGencode")
  Byte getDbGencode();

  @JsonProperty("dbGencode")
  void setDbGencode(byte dbGencode);

  @JsonProperty("maxIntronLength")
  Integer getMaxIntronLength();

  @JsonProperty("maxIntronLength")
  void setMaxIntronLength(int maxIntronLength);

  @JsonProperty("matrix")
  InputTBlastnScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputTBlastnScoringMatrix matrix);

  @JsonProperty("threshold")
  Double getThreshold();

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

  @JsonProperty("seg")
  InputBlastSegMask getSeg();

  @JsonProperty("seg")
  void setSeg(InputBlastSegMask seg);

  @JsonProperty("softMasking")
  Boolean getSoftMasking();

  @JsonProperty("softMasking")
  void setSoftMasking(boolean softMasking);

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

  @JsonProperty("cullingLimit")
  Integer getCullingLimit();

  @JsonProperty("cullingLimit")
  void setCullingLimit(int cullingLimit);

  @JsonProperty("bestHitOverhang")
  Double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang(double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
  Double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge(double bestHitScoreEdge);

  @JsonProperty("subjectBesthit")
  Boolean getSubjectBesthit();

  @JsonProperty("subjectBesthit")
  void setSubjectBesthit(boolean subjectBesthit);

  @JsonProperty("sumStats")
  Boolean getSumStats();

  @JsonProperty("sumStats")
  void setSumStats(boolean sumStats);

  @JsonProperty("xDropGap")
  Double getXDropGap();

  @JsonProperty("xDropGap")
  void setXDropGap(double xDropGap);

  @JsonProperty("xDropGapFinal")
  Double getXDropGapFinal();

  @JsonProperty("xDropGapFinal")
  void setXDropGapFinal(double xDropGapFinal);

  @JsonProperty("ungapped")
  Boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped(boolean ungapped);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(int windowSize);

  @JsonProperty("useSWTraceback")
  Boolean getUseSWTraceback();

  @JsonProperty("useSWTraceback")
  void setUseSWTraceback(boolean useSWTraceback);

  @JsonProperty("in_pssm")
  String getInPssm();

  @JsonProperty("in_pssm")
  void setInPssm(String inPssm);
}
