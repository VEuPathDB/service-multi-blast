package org.veupathdb.service.demo.generated.model;

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
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  void setSoftMasking(boolean softMasking);

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  boolean getLowercaseMasking();

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  void setLowercaseMasking(boolean lowercaseMasking);

  @JsonProperty("queryCoverageHSPPercent")
  double getQueryCoverageHSPPercent();

  @JsonProperty("queryCoverageHSPPercent")
  void setQueryCoverageHSPPercent(double queryCoverageHSPPercent);

  @JsonProperty("maxHSPs")
  int getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(int maxHSPs);

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  int getMaxTargetSequences();

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  void setMaxTargetSequences(int maxTargetSequences);

  @JsonProperty("dbSize")
  byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("xDropoffUngapped")
  double getXDropoffUngapped();

  @JsonProperty("xDropoffUngapped")
  void setXDropoffUngapped(double xDropoffUngapped);

  @JsonProperty("windowSize")
  int getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(int windowSize);

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
  int getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(int wordSize);

  @JsonProperty("gapOpen")
  int getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(int gapOpen);

  @JsonProperty("gapExtend")
  int getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(int gapExtend);

  @JsonProperty("penalty")
  int getPenalty();

  @JsonProperty("penalty")
  void setPenalty(int penalty);

  @JsonProperty("reward")
  int getReward();

  @JsonProperty("reward")
  void setReward(int reward);

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
  double getPercentIdentity();

  @JsonProperty("percentIdentity")
  void setPercentIdentity(double percentIdentity);

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

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  boolean getSubjectBestHit();

  @JsonProperty(
      value = "subjectBestHit",
      defaultValue = "false"
  )
  void setSubjectBestHit(boolean subjectBestHit);

  @JsonProperty("templateType")
  BlastNTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(BlastNTemplateType templateType);

  @JsonProperty("templateLength")
  int getTemplateLength();

  @JsonProperty("templateLength")
  void setTemplateLength(int templateLength);

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  boolean getSumStats();

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  void setSumStats(boolean sumStats);

  @JsonProperty("xDropoffPrelimGapped")
  double getXDropoffPrelimGapped();

  @JsonProperty("xDropoffPrelimGapped")
  void setXDropoffPrelimGapped(double xDropoffPrelimGapped);

  @JsonProperty("xDropoffFinalGapped")
  double getXDropoffFinalGapped();

  @JsonProperty("xDropoffFinalGapped")
  void setXDropoffFinalGapped(double xDropoffFinalGapped);

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  boolean getNonGreedy();

  @JsonProperty(
      value = "nonGreedy",
      defaultValue = "false"
  )
  void setNonGreedy(boolean nonGreedy);

  @JsonProperty("minRawGappedScore")
  int getMinRawGappedScore();

  @JsonProperty("minRawGappedScore")
  void setMinRawGappedScore(int minRawGappedScore);

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  boolean getUngappedOnly();

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  void setUngappedOnly(boolean ungappedOnly);

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  int getOffDiagonalRange();

  @JsonProperty(
      value = "offDiagonalRange",
      defaultValue = "0"
  )
  void setOffDiagonalRange(int offDiagonalRange);
}
