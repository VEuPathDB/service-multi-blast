package org.veupathdb.service.demo.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("rpsblast")
@JsonDeserialize(
    as = RPSBlastConfigImpl.class
)
public interface RPSBlastConfig extends BlastQueryConfig {
  BlastQueryTool _DISCRIMINATOR_TYPE_NAME = BlastQueryTool.RPSBLAST;

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
      value = "compBasedStats",
      defaultValue = "comp-based-stats"
  )
  RPSBlastCompBasedStats getCompBasedStats();

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-stats"
  )
  void setCompBasedStats(RPSBlastCompBasedStats compBasedStats);

  @JsonProperty("seg")
  BlastSeg getSeg();

  @JsonProperty("seg")
  void setSeg(BlastSeg seg);

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
