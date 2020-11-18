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
  void setShowGIs(boolean showGIs);

  @JsonProperty("numDescriptions")
  Integer getNumDescriptions();

  @JsonProperty("numDescriptions")
  void setNumDescriptions(int numDescriptions);

  @JsonProperty("numAlignments")
  Integer getNumAlignments();

  @JsonProperty("numAlignments")
  void setNumAlignments(int numAlignments);

  @JsonProperty("lineLength")
  Integer getLineLength();

  @JsonProperty("lineLength")
  void setLineLength(int lineLength);

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
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("qCovHSPPerc")
  Double getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc(double qCovHSPPerc);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(int maxHSPs);

  @JsonProperty("maxTargetSeqs")
  Integer getMaxTargetSeqs();

  @JsonProperty("maxTargetSeqs")
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("dbSize")
  Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("xDropUngap")
  Double getXDropUngap();

  @JsonProperty("xDropUngap")
  void setXDropUngap(double xDropUngap);

  @JsonProperty("parseDefLines")
  Boolean getParseDefLines();

  @JsonProperty("parseDefLines")
  void setParseDefLines(boolean parseDefLines);
}
