package mb.api.model.blast.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mb.api.model.blast.IOBlastConfig;
import mb.api.model.blast.IOBlastReportFormat;
import mb.lib.blast.model.IOHSPSorting;
import mb.lib.blast.model.IOHitSorting;
import org.veupathdb.lib.blast.field.*;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
abstract public class IOBlastConfigImpl implements IOBlastConfig
{
  private String              query;
  private Location            queryLoc;
  private String              eValue;
  private IOBlastReportFormat outFormat;
  private Long                numDescriptions;
  private Long                numAlignments;
  private Integer             lineLength;
  private IOHitSorting        sortHits;
  private IOHSPSorting        sortHSPs;
  private Boolean             lcaseMasking;
  private Double              qCovHSPPerc;
  private Long                maxHSPs;
  private Long                maxTargetSeqs;
  private Byte                dbSize;
  private Short               searchSpace;
  private Double              xDropUngap;
  private Boolean             parseDefLines;

  @JsonProperty("query")
  public String getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  @JsonProperty("queryLoc")
  public Location getQueryLoc() {
    return this.queryLoc;
  }

  @JsonProperty("queryLoc")
  public void setQueryLoc(Location queryLoc) {
    this.queryLoc = queryLoc;
  }

  @JsonProperty("eValue")
  public String getEValue() {
    return this.eValue;
  }

  @JsonProperty("eValue")
  public void setEValue(String eValue) {
    this.eValue = eValue;
  }

  @JsonIgnore
  public void setEValue(ExpectValue val) {
    if (val != null)
      setEValue(val.value());
  }

  @JsonProperty("outFormat")
  public IOBlastReportFormat getOutFormat() {
    return this.outFormat;
  }

  @JsonProperty("outFormat")
  public void setOutFormat(IOBlastReportFormat outFormat) {
    this.outFormat = outFormat;
  }

  @JsonProperty("numDescriptions")
  public Long getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty("numDescriptions")
  public void setNumDescriptions(Long numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonIgnore
  public void setNumDescriptions(NumDescriptions val) {
    if (val != null)
      setNumDescriptions(val.value());
  }

  @JsonProperty("numAlignments")
  public Long getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty("numAlignments")
  public void setNumAlignments(Long numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonIgnore
  public void setNumAlignments(NumAlignments val) {
    if (val != null) {
      setNumAlignments(val.value());
    }
  }

  @JsonProperty("lineLength")
  public Integer getLineLength() {
    return this.lineLength;
  }

  @JsonProperty("lineLength")
  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }

  @JsonIgnore
  public void setLineLength(LineLength val) {
    if (val != null) {
      setLineLength(val.value());
    }
  }

  @JsonProperty("sortHits")
  public IOHitSorting getSortHits() {
    return this.sortHits;
  }

  @JsonProperty("sortHits")
  public void setSortHits(IOHitSorting sortHits) {
    this.sortHits = sortHits;
  }

  @JsonProperty("sortHSPs")
  public IOHSPSorting getSortHSPs() {
    return this.sortHSPs;
  }

  @JsonProperty("sortHSPs")
  public void setSortHSPs(IOHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
  }

  @JsonProperty("lcaseMasking")
  public Boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty("lcaseMasking")
  public void setLcaseMasking(Boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("qCovHSPPerc")
  public Double getQCovHSPPerc() {
    return this.qCovHSPPerc;
  }

  @JsonProperty("qCovHSPPerc")
  public void setQCovHSPPerc(Double qCovHSPPerc) {
    this.qCovHSPPerc = qCovHSPPerc;
  }

  @JsonProperty("maxHSPs")
  public Long getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(Long maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty("maxTargetSeqs")
  public Long getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty("maxTargetSeqs")
  public void setMaxTargetSeqs(Long maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
  }

  @JsonIgnore
  public void setMaxTargetSeqs(MaxTargetSeqs al) {
    if (al != null) {
      setMaxTargetSeqs(al.value());
    }
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
  public Short getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(Short searchSpace) {
    this.searchSpace = searchSpace;
  }

  @JsonProperty("xDropUngap")
  public Double getXDropUngap() {
    return this.xDropUngap;
  }

  @JsonProperty("xDropUngap")
  public void setXDropUngap(Double xDropUngap) {
    this.xDropUngap = xDropUngap;
  }

  @JsonProperty("parseDefLines")
  public Boolean getParseDefLines() {
    return this.parseDefLines;
  }

  @JsonProperty("parseDefLines")
  public void setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
  }
}
