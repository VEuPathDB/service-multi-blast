package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonTypeName("blastn")
@JsonDeserialize(
    as = InputBlastnConfigImpl.class
)
public interface InputBlastnConfig extends InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = InputBlastTool.BLASTN;

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

  @JsonProperty("strand")
  InputBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(InputBlastStrand strand);

  @JsonProperty("task")
  InputBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputBlastnTask task);

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

  @JsonProperty("subjectLoc")
  InputBlastLocation getSubjectLoc();

  @JsonProperty("subjectLoc")
  void setSubjectLoc(InputBlastLocation subjectLoc);

  @JsonProperty("dust")
  InputBlastnDust getDust();

  @JsonProperty("dust")
  void setDust(InputBlastnDust dust);

  @JsonProperty("windowMaskerTaxid")
  Integer getWindowMaskerTaxid();

  @JsonProperty("windowMaskerTaxid")
  void setWindowMaskerTaxid(Integer windowMaskerTaxid);

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

  @JsonProperty("subjectBesthit")
   Boolean getSubjectBesthit();

  @JsonProperty("subjectBesthit")
  void setSubjectBesthit (Boolean subjectBesthit);

  @JsonProperty("templateType")
  InputBlastnDcTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(InputBlastnDcTemplateType templateType);

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
