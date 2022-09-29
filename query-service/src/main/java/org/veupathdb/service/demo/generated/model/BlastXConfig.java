package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("blastx")
@JsonDeserialize(
    as = BlastXConfigImpl.class
)
public interface BlastXConfig extends BlastQueryConfig {
  BlastQueryTool _DISCRIMINATOR_TYPE_NAME = BlastQueryTool.BLASTX;

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
      value = "queryGenCode",
      defaultValue = "1"
  )
  byte getQueryGenCode();

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  void setQueryGenCode(byte queryGenCode);

  @JsonProperty(
      value = "task",
      defaultValue = "blastx"
  )
  BlastXTask getTask();

  @JsonProperty(
      value = "task",
      defaultValue = "blastx"
  )
  void setTask(BlastXTask task);

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

  @JsonProperty("matrix")
  BlastXMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(BlastXMatrix matrix);

  @JsonProperty("threshold")
  double getThreshold();

  @JsonProperty("threshold")
  void setThreshold(double threshold);

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-score-adjustment-conditional"
  )
  BlastXCompBasedStats getCompBasedStats();

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-score-adjustment-conditional"
  )
  void setCompBasedStats(BlastXCompBasedStats compBasedStats);

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  BlastSeg getSeg();

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  void setSeg(BlastSeg seg);

  @JsonProperty("dbSoftMask")
  String getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(String dbSoftMask);

  @JsonProperty("dbHardMask")
  String getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(String dbHardMask);

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
      value = "useSWTraceback",
      defaultValue = "false"
  )
  boolean getUseSWTraceback();

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  void setUseSWTraceback(boolean useSWTraceback);
}
