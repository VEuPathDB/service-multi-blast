package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastx")
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
    "outFmt",
    "wordSize",
    "matrix",
    "threshold",
    "seg",
    "softMasking",
    "lcaseMasking",
    "strand",
    "dbSoftMask",
    "dbHardMask",
    "queryGeneticCode",
    "dbGenCode",
    "maxIntronLength"
})
public class InputTBlastxConfigImpl implements InputTBlastxConfig {
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

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  private byte wordSize;

  @JsonProperty("matrix")
  private InputBlastScoringMatrix matrix;

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  private byte threshold;

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  private InputBlastSegMask seg;

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  private boolean softMasking;

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  private boolean lcaseMasking;

  @JsonProperty("strand")
  private InputBlastStrand strand;

  @JsonProperty("dbSoftMask")
  private long dbSoftMask;

  @JsonProperty("dbHardMask")
  private long dbHardMask;

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  private byte queryGeneticCode;

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  private byte dbGenCode;

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  private int maxIntronLength;

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

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  public byte getWordSize() {
    return this.wordSize;
  }

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  public void setWordSize(byte wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("matrix")
  public InputBlastScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(InputBlastScoringMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  public byte getThreshold() {
    return this.threshold;
  }

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  public void setThreshold(byte threshold) {
    this.threshold = threshold;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public InputBlastSegMask getSeg() {
    return this.seg;
  }

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  public void setSeg(InputBlastSegMask seg) {
    this.seg = seg;
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
      value = "lcaseMasking",
      defaultValue = "false"
  )
  public boolean getLcaseMasking() {
    return this.lcaseMasking;
  }

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  public void setLcaseMasking(boolean lcaseMasking) {
    this.lcaseMasking = lcaseMasking;
  }

  @JsonProperty("strand")
  public InputBlastStrand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(InputBlastStrand strand) {
    this.strand = strand;
  }

  @JsonProperty("dbSoftMask")
  public long getDbSoftMask() {
    return this.dbSoftMask;
  }

  @JsonProperty("dbSoftMask")
  public void setDbSoftMask(long dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  @JsonProperty("dbHardMask")
  public long getDbHardMask() {
    return this.dbHardMask;
  }

  @JsonProperty("dbHardMask")
  public void setDbHardMask(long dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  public byte getQueryGeneticCode() {
    return this.queryGeneticCode;
  }

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  public void setQueryGeneticCode(byte queryGeneticCode) {
    this.queryGeneticCode = queryGeneticCode;
  }

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public byte getDbGenCode() {
    return this.dbGenCode;
  }

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  public void setDbGenCode(byte dbGenCode) {
    this.dbGenCode = dbGenCode;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public int getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  public void setMaxIntronLength(int maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }
}
