package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonTypeName("blastp")
@JsonDeserialize(
    as = IOBlastpConfigImpl.class
)
public interface IOBlastpConfig extends IOBlastConfig {
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = IOBlastTool.BLASTP;

  @JsonProperty("tool")
  IOBlastTool getTool();

  @JsonProperty("query")
  String getQuery();

  @JsonProperty("query")
  void setQuery(String query);

  @JsonProperty("queryLoc")
  IOBlastLocation getQueryLoc();

  @JsonProperty("queryLoc")
  void setQueryLoc(IOBlastLocation queryLoc);

  @JsonProperty("eValue")
  String getEValue();

  @JsonProperty("eValue")
  void setEValue(String eValue);

  @JsonProperty("outFormat")
  IOBlastReportFormat getOutFormat();

  @JsonProperty("outFormat")
  void setOutFormat(IOBlastReportFormat outFormat);

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
  IOHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(IOHitSorting sortHits);

  @JsonProperty("sortHSPs")
  IOHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(IOHSPSorting sortHSPs);

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
  IOBlastpTask getTask();

  @JsonProperty("task")
  void setTask(IOBlastpTask task);

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
  IOBlastpScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(IOBlastpScoringMatrix matrix);

  @JsonProperty("threshold")
   Double getThreshold();

  @JsonProperty("threshold")
  void setThreshold (Double threshold);

  @JsonProperty("compBasedStats")
  IOBlastCompBasedStats getCompBasedStats();

  @JsonProperty("compBasedStats")
  void setCompBasedStats(IOBlastCompBasedStats compBasedStats);

  @JsonProperty("seg")
  IOBlastSegMask getSeg();

  @JsonProperty("seg")
  void setSeg(IOBlastSegMask seg);

  @JsonProperty("softMasking")
   Boolean getSoftMasking();

  @JsonProperty("softMasking")
  void setSoftMasking (Boolean softMasking);

  @JsonProperty("taxIds")
  List<Integer> getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(List<Integer> taxIds);

  @JsonProperty("negativeTaxIds")
  List<Integer> getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(List<Integer> negativeTaxIds);

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

  @JsonProperty("subjectBestHit")
   Boolean getSubjectBestHit();

  @JsonProperty("subjectBestHit")
  void setSubjectBestHit (Boolean subjectBestHit);

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
