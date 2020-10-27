package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tool",
    "query",
    "query_loc",
    "eValue",
    "numDescriptions",
    "numAlignments",
    "maxTargetSeqs",
    "maxHSPs",
    "cullingLimit",
    "bestHitOverhang",
    "bestHitScoreEdge",
    "dbSize",
    "searchSpace",
    "parseDefLines",
    "outFmt"
})
public class InputBlastConfigImpl implements InputBlastConfig {
  @JsonProperty("tool")
  private final InputBlastTool tool = _DISCRIMINATOR_TYPE_NAME;

  @JsonProperty("query")
  private String query;

  @JsonProperty("query_loc")
  private InputBlastLocation queryLoc;

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  private double eValue;

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  private short numDescriptions;

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  private int numAlignments;

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  private int maxTargetSeqs;

  @JsonProperty("maxHSPs")
  private short maxHSPs;

  @JsonProperty("cullingLimit")
  private int cullingLimit;

  @JsonProperty("bestHitOverhang")
  private double bestHitOverhang;

  @JsonProperty("bestHitScoreEdge")
  private double bestHitScoreEdge;

  @JsonProperty("dbSize")
  private long dbSize;

  @JsonProperty("searchSpace")
  private long searchSpace;

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  private boolean parseDefLines;

  @JsonProperty("outFmt")
  private InputBlastOutFmt outFmt;

  @JsonProperty("tool")
  public InputBlastTool getTool() {
    return this.tool;
  }

  @JsonProperty("query")
  public String getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  @JsonProperty("query_loc")
  public InputBlastLocation getQueryLoc() {
    return this.queryLoc;
  }

  @JsonProperty("query_loc")
  public void setQueryLoc(InputBlastLocation queryLoc) {
    this.queryLoc = queryLoc;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  public double getEValue() {
    return this.eValue;
  }

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  public void setEValue(double eValue) {
    this.eValue = eValue;
  }

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  public short getNumDescriptions() {
    return this.numDescriptions;
  }

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  public void setNumDescriptions(short numDescriptions) {
    this.numDescriptions = numDescriptions;
  }

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  public int getNumAlignments() {
    return this.numAlignments;
  }

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  public void setNumAlignments(int numAlignments) {
    this.numAlignments = numAlignments;
  }

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  public int getMaxTargetSeqs() {
    return this.maxTargetSeqs;
  }

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  public void setMaxTargetSeqs(int maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
  }

  @JsonProperty("maxHSPs")
  public short getMaxHSPs() {
    return this.maxHSPs;
  }

  @JsonProperty("maxHSPs")
  public void setMaxHSPs(short maxHSPs) {
    this.maxHSPs = maxHSPs;
  }

  @JsonProperty("cullingLimit")
  public int getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(int cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  @JsonProperty("bestHitOverhang")
  public double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang(double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge(double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty("dbSize")
  public long getDbSize() {
    return this.dbSize;
  }

  @JsonProperty("dbSize")
  public void setDbSize(long dbSize) {
    this.dbSize = dbSize;
  }

  @JsonProperty("searchSpace")
  public long getSearchSpace() {
    return this.searchSpace;
  }

  @JsonProperty("searchSpace")
  public void setSearchSpace(long searchSpace) {
    this.searchSpace = searchSpace;
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

  @JsonProperty("outFmt")
  public InputBlastOutFmt getOutFmt() {
    return this.outFmt;
  }

  @JsonProperty("outFmt")
  public void setOutFmt(InputBlastOutFmt outFmt) {
    this.outFmt = outFmt;
  }
}
