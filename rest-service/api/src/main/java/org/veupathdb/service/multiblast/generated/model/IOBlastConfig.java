package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = JsonKeys.Tool
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

  @JsonProperty(JsonKeys.Tool)
  IOBlastTool getTool();

  @JsonProperty(JsonKeys.Query)
  String getQuery();

  @JsonProperty(JsonKeys.Query)
  void setQuery(String query);

  @JsonProperty(JsonKeys.QueryLocation)
  IOBlastLocation getQueryLoc();

  @JsonProperty(JsonKeys.QueryLocation)
  void setQueryLoc(IOBlastLocation queryLoc);

  @JsonProperty(JsonKeys.ExpectValue)
  String getEValue();

  @JsonProperty(JsonKeys.ExpectValue)
  void setEValue(String eValue);

  @JsonProperty(JsonKeys.OutFormat)
  IOBlastReportFormat getOutFormat();

  @JsonProperty(JsonKeys.OutFormat)
  void setOutFormat(IOBlastReportFormat outFormat);

  @JsonProperty(JsonKeys.NumDescriptions)
  Integer getNumDescriptions();

  @JsonProperty(JsonKeys.NumDescriptions)
  void setNumDescriptions(Integer numDescriptions);

  @JsonProperty(JsonKeys.NumAlignments)
  Integer getNumAlignments();

  @JsonProperty(JsonKeys.NumAlignments)
  void setNumAlignments(Integer numAlignments);

  @JsonProperty(JsonKeys.LineLength)
  Integer getLineLength();

  @JsonProperty(JsonKeys.LineLength)
  void setLineLength(Integer lineLength);

  @JsonProperty(JsonKeys.SortHits)
  IOHitSorting getSortHits();

  @JsonProperty(JsonKeys.SortHits)
  void setSortHits(IOHitSorting sortHits);

  @JsonProperty(JsonKeys.SortHSPs)
  IOHSPSorting getSortHSPs();

  @JsonProperty(JsonKeys.SortHSPs)
  void setSortHSPs(IOHSPSorting sortHSPs);

  @JsonProperty(JsonKeys.LowercaseMasking)
   Boolean getLcaseMasking();

  @JsonProperty(JsonKeys.LowercaseMasking)
  void setLcaseMasking (Boolean lcaseMasking);

  @JsonProperty(JsonKeys.QueryCoverageHSPPercent)
   Double getQCovHSPPerc();

  @JsonProperty(JsonKeys.QueryCoverageHSPPercent)
  void setQCovHSPPerc (Double qCovHSPPerc);

  @JsonProperty(JsonKeys.MaxHSPs)
  Integer getMaxHSPs();

  @JsonProperty(JsonKeys.MaxHSPs)
  void setMaxHSPs(Integer maxHSPs);

  @JsonProperty(JsonKeys.MaxTargetSequences)
  Integer getMaxTargetSeqs();

  @JsonProperty(JsonKeys.MaxTargetSequences)
  void setMaxTargetSeqs(Integer maxTargetSeqs);

  @JsonProperty(JsonKeys.DBSize)
   Byte getDbSize();

  @JsonProperty(JsonKeys.DBSize)
  void setDbSize (Byte dbSize);

  @JsonProperty(JsonKeys.SearchSpace)
   Byte getSearchSpace();

  @JsonProperty(JsonKeys.SearchSpace)
  void setSearchSpace (Byte searchSpace);

  @JsonProperty(JsonKeys.XDropUngap)
   Double getXDropUngap();

  @JsonProperty(JsonKeys.XDropUngap)
  void setXDropUngap (Double xDropUngap);

  @JsonProperty(JsonKeys.ParseDefLines)
   Boolean getParseDefLines();

  @JsonProperty(JsonKeys.ParseDefLines)
  void setParseDefLines (Boolean parseDefLines);
}
