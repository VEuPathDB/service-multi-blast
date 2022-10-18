package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("rpstblastn")
@JsonPropertyOrder({
    "tool",
    "queryLocation",
    "eValue",
    "softMasking",
    "lowercaseMasking",
    "queryCoverageHSPPercent",
    "maxHSPs",
    "maxTargetSequences",
    "dbSize",
    "searchSpace",
    "xDropoffUngapped",
    "windowSize",
    "parseDefLines",
    "queryGenCode",
    "strand",
    "compBasedStats",
    "seg",
    "sumStats",
    "xDropoffPrelimGapped",
    "xDropoffFinalGapped",
    "ungappedOnly",
    "useSWTraceback"
})
public class RPSTBlastNConfigImpl implements RPSTBlastNConfig {
  @JsonProperty("tool")
  private final BlastQueryTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("queryLocation")
  private BlastLocation queryLocation;

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  private String eValue;

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  private Boolean softMasking;

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  private Boolean lowercaseMasking;

  @JsonProperty("queryCoverageHSPPercent")
  private Double queryCoverageHSPPercent;

  @JsonProperty("maxHSPs")
  private Integer maxHSPs;

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  private Integer maxTargetSequences;

  @JsonProperty("dbSize")
  private Byte dbSize;

  @JsonProperty("searchSpace")
  private Byte searchSpace;

  @JsonProperty("xDropoffUngapped")
  private Double xDropoffUngapped;

  @JsonProperty("windowSize")
  private Integer windowSize;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private Boolean parseDefLines;

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  private Byte queryGenCode;

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  private BlastStrand strand;

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-stats"
  )
  private RPSTBlastNCompBasedStats compBasedStats;

  @JsonProperty("seg")
  private BlastSeg seg;

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  private Boolean sumStats;

  @JsonProperty("xDropoffPrelimGapped")
  private Double xDropoffPrelimGapped;

  @JsonProperty("xDropoffFinalGapped")
  private Double xDropoffFinalGapped;

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  private Boolean ungappedOnly;

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  private Boolean useSWTraceback;

  @JsonProperty("tool")
  public BlastQueryTool getTool() {
    return this.tool;
  }

  @JsonProperty("queryLocation")
  public BlastLocation getQueryLocation() {
    return this.queryLocation;
  }

  @JsonProperty("queryLocation")
  public void setQueryLocation(BlastLocation queryLocation) {
    this.queryLocation = queryLocation;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  public String getEValue() {
    return this.eValue;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10"
  )
  public void setEValue(String eValue) {
    this.eValue = eValue;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public Boolean getLowercaseMasking() {
    return this.lowercaseMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public void setLowercaseMasking(Boolean lowercaseMasking) {
    this.lowercaseMasking = lowercaseMasking;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public Double getQueryCoverageHSPPercent() {
    return this.queryCoverageHSPPercent;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public void setQueryCoverageHSPPercent(Double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
  }

  @JsonProperty("maxHSPs")
  public Integer getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(Integer maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public Integer getMaxTargetSequences() {
    return this.maxTargetSequences;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public void setMaxTargetSequences(Integer maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
  }

  @JsonProperty("dbSize")
  public Byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(Byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public Byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(Byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropoffUngapped")
  public Double getXDropoffUngapped() {
    return this.xDropoffUngapped;
  }

  @JsonProperty("xDropoffUngapped")
  public void setXDropoffUngapped(Double xDropoffUngapped) {
    this.xDropoffUngapped = xDropoffUngapped;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
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

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  public Byte getQueryGenCode() {
    return this.queryGenCode;
  }

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  public void setQueryGenCode(Byte queryGenCode) {
    this.queryGenCode = queryGenCode;
  }

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  public BlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty(
      value = "strand",
      defaultValue = "both"
  )
  public void setStrand(BlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-stats"
  )
  public RPSTBlastNCompBasedStats getCompBasedStats() {
    return this.compBasedStats;
  }

  @JsonProperty(
      value = "compBasedStats",
      defaultValue = "comp-based-stats"
  )
  public void setCompBasedStats(RPSTBlastNCompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
  }

  @JsonProperty("seg")
  public BlastSeg getSeg() {
    return this.seg;
  }

  @JsonProperty("seg")
  public void setSeg(BlastSeg seg) {
    this.seg = seg;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public Double getXDropoffPrelimGapped() {
    return this.xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public void setXDropoffPrelimGapped(Double xDropoffPrelimGapped) {
    this.xDropoffPrelimGapped = xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public Double getXDropoffFinalGapped() {
    return this.xDropoffFinalGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public void setXDropoffFinalGapped(Double xDropoffFinalGapped) {
    this.xDropoffFinalGapped = xDropoffFinalGapped;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public Boolean getUngappedOnly() {
    return this.ungappedOnly;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public void setUngappedOnly(Boolean ungappedOnly) {
    this.ungappedOnly = ungappedOnly;
  }

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public Boolean getUseSWTraceback() {
    return this.useSWTraceback;
  }

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public void setUseSWTraceback(Boolean useSWTraceback) {
    this.useSWTraceback = useSWTraceback;
  }
}
