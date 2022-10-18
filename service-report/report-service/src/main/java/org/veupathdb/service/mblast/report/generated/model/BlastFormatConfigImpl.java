package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "formatType",
    "formatFields",
    "showGIs",
    "numDescriptions",
    "numAlignments",
    "lineLength",
    "sortHits",
    "sortHSPs",
    "maxTargetSequences",
    "parseDefLines"
})
public class BlastFormatConfigImpl implements BlastFormatConfig {
  @JsonProperty(
      value = "formatType",
      defaultValue = "pairwise"
  )
  private BlastOutFormat formatType;

  @JsonProperty("formatFields")
  private List<BlastOutField> formatFields;

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  private Boolean showGIs;

  @JsonProperty("numDescriptions")
  private Integer numDescriptions;

  @JsonProperty("numAlignments")
  private Integer numAlignments;

  @JsonProperty("lineLength")
  private Integer lineLength;

  @JsonProperty("sortHits")
  private BlastHitSorting sortHits;

  @JsonProperty("sortHSPs")
  private BlastHSPSorting sortHSPs;

  @JsonProperty("maxTargetSequences")
  private Integer maxTargetSequences;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private Boolean parseDefLines;

  @JsonProperty(
      value = "formatType",
      defaultValue = "pairwise"
  )
  public BlastOutFormat getFormatType() {
    return this.formatType;
  }

  @JsonProperty(
      value = "formatType",
      defaultValue = "pairwise"
  )
  public void setFormatType(BlastOutFormat formatType) {
    this.formatType = formatType;
  }

  @JsonProperty("formatFields")
  public List<BlastOutField> getFormatFields() {
    return this.formatFields;
  }

  @JsonProperty("formatFields")
  public void setFormatFields(List<BlastOutField> formatFields) {
    this.formatFields = formatFields;
  }

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  public Boolean getShowGIs() {
    return this.showGIs;
  }

  @JsonProperty(
      value = "showGIs",
      defaultValue = "false"
  )
  public void setShowGIs(Boolean showGIs) {
    this.showGIs = showGIs;
  }

  @JsonProperty("numDescriptions")
  public Integer getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty("numDescriptions")
  public void setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty("numAlignments")
  public Integer getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty("numAlignments")
  public void setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty("lineLength")
  public Integer getLineLength() {
    return this.lineLength;
  }

  @JsonProperty("lineLength")
  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }

  @JsonProperty("sortHits")
  public BlastHitSorting getSortHits() {
    return this.sortHits;
  }

  @JsonProperty("sortHits")
  public void setSortHits(BlastHitSorting sortHits) {
    this.sortHits = sortHits;
  }

  @JsonProperty("sortHSPs")
  public BlastHSPSorting getSortHSPs() {
    return this.sortHSPs;
  }

  @JsonProperty("sortHSPs")
  public void setSortHSPs(BlastHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
  }

  @JsonProperty("maxTargetSequences")
  public Integer getMaxTargetSequences() {
    return this.maxTargetSequences;
  }

  @JsonProperty("maxTargetSequences")
  public void setMaxTargetSequences(Integer maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public void setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }
}
