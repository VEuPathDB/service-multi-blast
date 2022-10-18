package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("blastn")
@JsonDeserialize(
    as = BlastNConfigImpl.class
)
public interface BlastNConfig extends BlastQueryConfig {
  BlastQueryTool _DISCRIMINATOR_TYPE_NAME = BlastQueryTool.BLASTN;

  @JsonProperty("tool")
  BlastQueryTool getTool();

  @JsonProperty("queryLocation")
  BlastLocation getQueryLocation();

  @JsonProperty("queryLocation")
  void setQueryLocation(BlastLocation queryLocation);

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  String getEValue();

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  void setEValue(String eValue);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  Boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  void setSoftMasking(Boolean softMasking);

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  Boolean getLowercaseMasking();

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  void setLowercaseMasking(Boolean lowercaseMasking);

  @JsonProperty("queryCoverageHSPPercent")
  Double getQueryCoverageHSPPercent();

  @JsonProperty("queryCoverageHSPPercent")
  void setQueryCoverageHSPPercent(Double queryCoverageHSPPercent);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(Integer maxHSPs);

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  Integer getMaxTargetSequences();

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  void setMaxTargetSequences(Integer maxTargetSequences);

  @JsonProperty("dbSize")
  Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(Byte dbSize);

  @JsonProperty("searchSpace")
  Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(Byte searchSpace);

  @JsonProperty("xDropoffUngapped")
  Double getXDropoffUngapped();

  @JsonProperty("xDropoffUngapped")
  void setXDropoffUngapped(Double xDropoffUngapped);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  Boolean getParseDefLines();

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  void setParseDefLines(Boolean parseDefLines);

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  BlastStrand getStrand();

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  void setStrand(BlastStrand strand);

  @JsonProperty(
      value = "task",
      defaultValue = "megablast"
  )
  BlastNTask getTask();

  @JsonProperty(
      value = "task",
      defaultValue = "megablast"
  )
  void setTask(BlastNTask task);

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

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  Boolean getUseIndex();

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  void setUseIndex(Boolean useIndex);

  @JsonProperty("indexName")
  String getIndexName();

  @JsonProperty("indexName")
  void setIndexName(String indexName);

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  BlastNDust getDust();

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  void setDust(BlastNDust dust);

  @JsonProperty("dbSoftMask")
  String getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(String dbSoftMask);

  @JsonProperty("dbHardMask")
  String getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(String dbHardMask);

  @JsonProperty("percentIdentity")
  Double getPercentIdentity();

  @JsonProperty("percentIdentity")
  void setPercentIdentity(Double percentIdentity);

  @JsonProperty("cullingLimit")
  Integer getCullingLimit();

  @JsonProperty("cullingLimit")
  void setCullingLimit(Integer cullingLimit);

  @JsonProperty("bestHitOverhang")
  Double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang(Double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
  Double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge(Double bestHitScoreEdge);

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  Boolean getSubjectBestHit();

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  void setSubjectBestHit(Boolean subjectBestHit);

  @JsonProperty("templateType")
  BlastNTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(BlastNTemplateType templateType);

  @JsonProperty("templateLength")
  Integer getTemplateLength();

  @JsonProperty("templateLength")
  void setTemplateLength(Integer templateLength);

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  Boolean getSumStats();

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  void setSumStats(Boolean sumStats);

  @JsonProperty("xDropoffPrelimGapped")
  Double getXDropoffPrelimGapped();

  @JsonProperty("xDropoffPrelimGapped")
  void setXDropoffPrelimGapped(Double xDropoffPrelimGapped);

  @JsonProperty("xDropoffFinalGapped")
  Double getXDropoffFinalGapped();

  @JsonProperty("xDropoffFinalGapped")
  void setXDropoffFinalGapped(Double xDropoffFinalGapped);

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  Boolean getNonGreedy();

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  void setNonGreedy(Boolean nonGreedy);

  @JsonProperty("minRawGappedScore")
  Integer getMinRawGappedScore();

  @JsonProperty("minRawGappedScore")
  void setMinRawGappedScore(Integer minRawGappedScore);

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  Boolean getUngappedOnly();

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  void setUngappedOnly(Boolean ungappedOnly);

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  Integer getOffDiagonalRange();

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  void setOffDiagonalRange(Integer offDiagonalRange);
}
