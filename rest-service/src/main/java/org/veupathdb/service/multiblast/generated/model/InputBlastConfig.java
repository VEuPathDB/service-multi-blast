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
}
