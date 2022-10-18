package org.veupathdb.service.mblast.query.generated.model;

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
      value = "useSWTraceback",
      defaultValue = "false"
  )
  Boolean getUseSWTraceback();

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  void setUseSWTraceback(Boolean useSWTraceback);
}
