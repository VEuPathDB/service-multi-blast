package mb.api.model.blast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.blast.impl.IOBlastConfigImpl;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.model.IOHSPSorting;
import mb.lib.blast.model.IOHitSorting;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.field.Location;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = JsonKeys.Tool
)
@JsonSubTypes({
    @JsonSubTypes.Type(IOTBlastxConfig.class),
    @JsonSubTypes.Type(IOTBlastnConfig.class),
    @JsonSubTypes.Type(IOBlastpConfig.class),
    @JsonSubTypes.Type(IOBlastxConfig.class),
    @JsonSubTypes.Type(IOBlastnConfig.class),
    @JsonSubTypes.Type(IOBlastConfig.class)
})
@JsonDeserialize(as = IOBlastConfigImpl.class)
public interface IOBlastConfig {
  @JsonProperty(JsonKeys.Tool)
  BlastTool getTool();

  @JsonProperty(JsonKeys.Query)
  String getQuery();

  @JsonProperty(JsonKeys.Query)
  void setQuery(String query);

  @JsonProperty(JsonKeys.QueryLocation)
  Location getQueryLoc();

  @JsonProperty(JsonKeys.QueryLocation)
  void setQueryLoc(Location queryLoc);

  @JsonProperty(JsonKeys.ExpectValue)
  String getEValue();

  @JsonProperty(JsonKeys.ExpectValue)
  void setEValue(String eValue);

  @JsonProperty(JsonKeys.OutFormat)
  IOBlastReportFormat getOutFormat();

  @JsonProperty(JsonKeys.OutFormat)
  void setOutFormat(IOBlastReportFormat outFormat);

  @JsonProperty(JsonKeys.NumDescriptions)
  Long getNumDescriptions();

  @JsonProperty(JsonKeys.NumDescriptions)
  void setNumDescriptions(Long numDescriptions);

  @JsonProperty(JsonKeys.NumAlignments)
  Long getNumAlignments();

  @JsonProperty(JsonKeys.NumAlignments)
  void setNumAlignments(Long numAlignments);

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
  Long getMaxHSPs();

  @JsonProperty(JsonKeys.MaxHSPs)
  void setMaxHSPs(Long maxHSPs);

  @JsonProperty(JsonKeys.MaxTargetSequences)
  Long getMaxTargetSeqs();

  @JsonProperty(JsonKeys.MaxTargetSequences)
  void setMaxTargetSeqs(Long maxTargetSeqs);

  @JsonProperty(JsonKeys.DBSize)
   Byte getDbSize();

  @JsonProperty(JsonKeys.DBSize)
  void setDbSize (Byte dbSize);

  @JsonProperty(JsonKeys.SearchSpace)
  Short getSearchSpace();

  @JsonProperty(JsonKeys.SearchSpace)
  void setSearchSpace (Short searchSpace);

  @JsonProperty(JsonKeys.XDropUngap)
   Double getXDropUngap();

  @JsonProperty(JsonKeys.XDropUngap)
  void setXDropUngap (Double xDropUngap);

  @JsonProperty(JsonKeys.ParseDefLines)
   Boolean getParseDefLines();

  @JsonProperty(JsonKeys.ParseDefLines)
  void setParseDefLines (Boolean parseDefLines);
}
