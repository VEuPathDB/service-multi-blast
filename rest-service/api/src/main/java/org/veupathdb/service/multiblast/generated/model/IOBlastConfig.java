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
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOTBlastxConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOTBlastnConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOBlastpConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOBlastxConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOBlastnConfig.class),
    @JsonSubTypes.Type(org.veupathdb.service.multiblast.generated.model.IOBlastConfig.class)
})
@JsonDeserialize(as = IOBlastConfigImpl.class)
public interface IOBlastConfig {
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = null;

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
}
