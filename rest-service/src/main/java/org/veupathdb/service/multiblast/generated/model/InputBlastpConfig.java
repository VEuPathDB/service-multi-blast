package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonTypeName("blastp")
@JsonDeserialize(
    as = InputBlastpConfigImpl.class
)
public interface InputBlastpConfig extends InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = InputBlastTool.BLASTP;

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
  void setShowGIs (Boolean showGIs);

  @JsonProperty("numDescriptions")
  Integer getNumDescriptions();

  @JsonProperty("numDescriptions")
  void setNumDescriptions(Integer numDescriptions);

  @JsonProperty("numAlignments")
  Integer getNumAlignments();

  @JsonProperty("numAlignments")
  void setNumAlignments(Integer numAlignments);

  @JsonProperty("lineLength")
  Integer getLineLength();

  @JsonProperty("lineLength")
  void setLineLength(Integer lineLength);

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
  void setLcaseMasking (Boolean lcaseMasking);

  @JsonProperty("qCovHSPPerc")
   Double getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc (Double qCovHSPPerc);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(Integer maxHSPs);

  @JsonProperty("maxTargetSeqs")
  Integer getMaxTargetSeqs();

  @JsonProperty("maxTargetSeqs")
  void setMaxTargetSeqs(Integer maxTargetSeqs);

  @JsonProperty("dbSize")
   Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize (Byte dbSize);

  @JsonProperty("searchSpace")
   Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace (Byte searchSpace);

  @JsonProperty("xDropUngap")
   Double getXDropUngap();

  @JsonProperty("xDropUngap")
  void setXDropUngap (Double xDropUngap);

  @JsonProperty("parseDefLines")
   Boolean getParseDefLines();

  @JsonProperty("parseDefLines")
  void setParseDefLines (Boolean parseDefLines);

  @JsonProperty("task")
  InputBlastpTask getTask();

  @JsonProperty("task")
  void setTask(InputBlastpTask task);

  @JsonProperty("wordSize")
  Integer getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(Integer wordSize);

  @JsonProperty("gapOpen")
  Integer getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(Integer gapOpen);

  @JsonProperty("gapExtend")
  Integer getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(Integer gapExtend);

  @JsonProperty("matrix")
  InputBlastpScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputBlastpScoringMatrix matrix);

  @JsonProperty("threshold")
   Double getThreshold();

  @JsonProperty("threshold")
  void setThreshold (Double threshold);

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
  void setSoftMasking (Boolean softMasking);

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
  void setCullingLimit(Integer cullingLimit);

  @JsonProperty("bestHitOverhang")
   Double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang (Double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
   Double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge (Double bestHitScoreEdge);

  @JsonProperty("subjectBesthit")
   Boolean getSubjectBesthit();

  @JsonProperty("subjectBesthit")
  void setSubjectBesthit (Boolean subjectBesthit);

  @JsonProperty("xDropGap")
   Double getXDropGap();

  @JsonProperty("xDropGap")
  void setXDropGap (Double xDropGap);

  @JsonProperty("xDropGapFinal")
   Double getXDropGapFinal();

  @JsonProperty("xDropGapFinal")
  void setXDropGapFinal (Double xDropGapFinal);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty("ungapped")
   Boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped (Boolean ungapped);

  @JsonProperty("useSWTraceback")
   Boolean getUseSWTraceback();

  @JsonProperty("useSWTraceback")
  void setUseSWTraceback (Boolean useSWTraceback);
}
