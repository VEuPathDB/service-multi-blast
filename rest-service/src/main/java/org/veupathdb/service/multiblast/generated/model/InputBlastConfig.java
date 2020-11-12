package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "tool"
)
@JsonSubTypes({
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputTBlastnConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputTBlastxConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputBlastpConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputBlastxConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputBlastnConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.InputBlastConfig.class)
})
@JsonDeserialize(
    as = InputBlastConfigImpl.class
)
public interface InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = null;

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

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  double getEValue();

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  void setEValue(double eValue);

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  int getLineLength();

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  void setLineLength(int lineLength);

  @JsonProperty("sortHits")
  InputHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(InputHitSorting sortHits);

  @JsonProperty("seqIdList")
  String getSeqIdList();

  @JsonProperty("seqIdList")
  void setSeqIdList(String seqIdList);

  @JsonProperty("negativeSeqIdList")
  String getNegativeSeqIdList();

  @JsonProperty("negativeSeqIdList")
  void setNegativeSeqIdList(String negativeSeqIdList);

  @JsonProperty("taxIds")
  String getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(String taxIds);

  @JsonProperty("negativeTaxIds")
  String getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(String negativeTaxIds);

  @JsonProperty("taxIdList")
  String getTaxIdList();

  @JsonProperty("taxIdList")
  void setTaxIdList(String taxIdList);

  @JsonProperty("negativeTaxIdList")
  String getNegativeTaxIdList();

  @JsonProperty("negativeTaxIdList")
  void setNegativeTaxIdList(String negativeTaxIdList);

  @JsonProperty("sortHSPs")
  InputHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(InputHSPSorting sortHSPs);

  @JsonProperty("qCovHSPPerc")
  Number getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc(Number qCovHSPPerc);

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  short getNumDescriptions();

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  void setNumDescriptions(short numDescriptions);

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  int getNumAlignments();

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  void setNumAlignments(int numAlignments);

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  int getMaxTargetSeqs();

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("maxHSPs")
  short getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(short maxHSPs);

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

  @JsonProperty("dbSize")
  long getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(long dbSize);

  @JsonProperty("searchSpace")
  long getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(long searchSpace);

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

  @JsonProperty("outFmt")
  InputBlastOutFmt getOutFmt();

  @JsonProperty("outFmt")
  void setOutFmt(InputBlastOutFmt outFmt);
}
