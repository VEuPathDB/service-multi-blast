package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("blastn")
@JsonDeserialize(
    as = IOBlastnConfigImpl.class
)
public interface IOBlastnConfig extends IOBlastConfig {
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = IOBlastTool.BLASTN;

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

  @JsonProperty("strand")
  IOBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(IOBlastStrand strand);

  @JsonProperty("task")
  IOBlastnTask getTask();

  @JsonProperty("task")
  void setTask(IOBlastnTask task);

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

  @JsonProperty("penalty")
  Integer getPenalty();

  @JsonProperty("penalty")
  void setPenalty(Integer penalty);

  @JsonProperty("reward")
  Integer getReward();

  @JsonProperty("reward")
  void setReward(Integer reward);

  @JsonProperty("useIndex")
   Boolean getUseIndex();

  @JsonProperty("useIndex")
  void setUseIndex (Boolean useIndex);

  @JsonProperty("indexName")
  String getIndexName();

  @JsonProperty("indexName")
  void setIndexName(String indexName);

  @JsonProperty("dust")
  IOBlastnDust getDust();

  @JsonProperty("dust")
  void setDust(IOBlastnDust dust);

  @JsonProperty("windowMaskerTaxid")
  Integer getWindowMaskerTaxid();

  @JsonProperty("windowMaskerTaxid")
  void setWindowMaskerTaxid(Integer windowMaskerTaxid);

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

  @JsonProperty("percIdentity")
   Double getPercIdentity();

  @JsonProperty("percIdentity")
  void setPercIdentity (Double percIdentity);

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

  @JsonProperty("templateType")
  IOBlastnDcTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(IOBlastnDcTemplateType templateType);

  @JsonProperty("templateLength")
   Byte getTemplateLength();

  @JsonProperty("templateLength")
  void setTemplateLength (Byte templateLength);

  @JsonProperty("sumStats")
   Boolean getSumStats();

  @JsonProperty("sumStats")
  void setSumStats (Boolean sumStats);

  @JsonProperty("xDropGap")
   Double getXDropGap();

  @JsonProperty("xDropGap")
  void setXDropGap (Double xDropGap);

  @JsonProperty("xDropGapFinal")
   Double getXDropGapFinal();

  @JsonProperty("xDropGapFinal")
  void setXDropGapFinal (Double xDropGapFinal);

  @JsonProperty("noGreedy")
   Boolean getNoGreedy();

  @JsonProperty("noGreedy")
  void setNoGreedy (Boolean noGreedy);

  @JsonProperty("minRawGappedScore")
  Integer getMinRawGappedScore();

  @JsonProperty("minRawGappedScore")
  void setMinRawGappedScore(Integer minRawGappedScore);

  @JsonProperty("ungapped")
   Boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped (Boolean ungapped);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty("offDiagonalRange")
  Integer getOffDiagonalRange();

  @JsonProperty("offDiagonalRange")
  void setOffDiagonalRange(Integer offDiagonalRange);
}
