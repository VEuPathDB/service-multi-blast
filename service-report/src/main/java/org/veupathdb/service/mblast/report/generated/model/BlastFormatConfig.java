package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(
    as = BlastFormatConfigImpl.class
)
public interface BlastFormatConfig {
  @JsonProperty(
      value = "formatType",
      defaultValue = "pairwise"
  )
  BlastOutFormat getFormatType();

  @JsonProperty(
      value = "formatType",
      defaultValue = "pairwise"
  )
  void setFormatType(BlastOutFormat formatType);

  @JsonProperty("formatFields")
  List<BlastOutField> getFormatFields();

  @JsonProperty("formatFields")
  void setFormatFields(List<BlastOutField> formatFields);

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  Boolean getShowGIs();

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  void setShowGIs(Boolean showGIs);

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
  BlastHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(BlastHitSorting sortHits);

  @JsonProperty("sortHSPs")
  BlastHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(BlastHSPSorting sortHSPs);

  @JsonProperty("maxTargetSequences")
  Integer getMaxTargetSequences();

  @JsonProperty("maxTargetSequences")
  void setMaxTargetSequences(Integer maxTargetSequences);

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
}
