package org.veupathdb.service.demo.generated.model;

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
  private boolean softMasking;

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  private boolean lowercaseMasking;

  @JsonProperty("queryCoverageHSPPercent")
  private double queryCoverageHSPPercent;

  @JsonProperty("maxHSPs")
  private int maxHSPs;

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  private int maxTargetSequences;

  @JsonProperty("dbSize")
  private byte dbSize;

  @JsonProperty("searchSpace")
  private byte searchSpace;

  @JsonProperty("xDropoffUngapped")
  private double xDropoffUngapped;

  @JsonProperty("windowSize")
  private int windowSize;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private boolean parseDefLines;

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  private byte queryGenCode;

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
  private boolean sumStats;

  @JsonProperty("xDropoffPrelimGapped")
  private double xDropoffPrelimGapped;

  @JsonProperty("xDropoffFinalGapped")
  private double xDropoffFinalGapped;

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  private boolean ungappedOnly;

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  private boolean useSWTraceback;

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
  public boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  public void setSoftMasking(boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public boolean getLowercaseMasking() {
    return this.lowercaseMasking;
  }

  @JsonProperty(
      value = "lowercaseMasking",
      defaultValue = "false"
  )
  public void setLowercaseMasking(boolean lowercaseMasking) {
    this.lowercaseMasking = lowercaseMasking;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public double getQueryCoverageHSPPercent() {
    return this.queryCoverageHSPPercent;
  }

  @JsonProperty("queryCoverageHSPPercent")
  public void setQueryCoverageHSPPercent(double queryCoverageHSPPercent) {
    this.queryCoverageHSPPercent = queryCoverageHSPPercent;
  }

  @JsonProperty("maxHSPs")
  public int getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(int maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public int getMaxTargetSequences() {
    return this.maxTargetSequences;
  }

  @JsonProperty(
      value = "maxTargetSequences",
      defaultValue = "500"
  )
  public void setMaxTargetSequences(int maxTargetSequences) {
    this.maxTargetSequences = maxTargetSequences;
  }

  @JsonProperty("dbSize")
  public byte getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(byte dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public byte getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(byte searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropoffUngapped")
  public double getXDropoffUngapped() {
    return this.xDropoffUngapped;
  }

  @JsonProperty("xDropoffUngapped")
  public void setXDropoffUngapped(double xDropoffUngapped) {
    this.xDropoffUngapped = xDropoffUngapped;
  }

  @JsonProperty("windowSize")
  public int getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(int windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  public void setParseDefLines(boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  public byte getQueryGenCode() {
    return this.queryGenCode;
  }

  @JsonProperty(
      value = "queryGenCode",
      defaultValue = "1"
  )
  public void setQueryGenCode(byte queryGenCode) {
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
  public boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty(
      value = "sumStats",
      defaultValue = "false"
  )
  public void setSumStats(boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public double getXDropoffPrelimGapped() {
    return this.xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffPrelimGapped")
  public void setXDropoffPrelimGapped(double xDropoffPrelimGapped) {
    this.xDropoffPrelimGapped = xDropoffPrelimGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public double getXDropoffFinalGapped() {
    return this.xDropoffFinalGapped;
  }

  @JsonProperty("xDropoffFinalGapped")
  public void setXDropoffFinalGapped(double xDropoffFinalGapped) {
    this.xDropoffFinalGapped = xDropoffFinalGapped;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public boolean getUngappedOnly() {
    return this.ungappedOnly;
  }

  @JsonProperty(
      value = "ungappedOnly",
      defaultValue = "false"
  )
  public void setUngappedOnly(boolean ungappedOnly) {
    this.ungappedOnly = ungappedOnly;
  }

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public boolean getUseSWTraceback() {
    return this.useSWTraceback;
  }

  @JsonProperty(
      value = "useSWTraceback",
      defaultValue = "false"
  )
  public void setUseSWTraceback(boolean useSWTraceback) {
    this.useSWTraceback = useSWTraceback;
  }
}
