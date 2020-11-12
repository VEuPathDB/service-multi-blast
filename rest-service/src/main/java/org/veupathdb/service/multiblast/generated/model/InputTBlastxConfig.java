package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("tblastx")
@JsonDeserialize(
    as = InputTBlastxConfigImpl.class
)
public interface InputTBlastxConfig extends InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = InputBlastTool.TBLASTX;

  @JsonProperty("tool")
  InputBlastTool getTool();

  @JsonProperty("query")
  String getQuery();

  @JsonProperty("query")
  void setQuery(String query);

  @JsonProperty("queryLoc")
  InputBlastLocation getQueryLoc();

  @JsonProperty("queryLoc")
  void setQueryLoc(InputBlastLocation queryLoc);

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  double getEValue();

  @JsonProperty(
      value = "eValue",
      defaultValue = "10.0"
  )
  void setEValue(double eValue);

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  int getLineLength();

  @JsonProperty(
      value = "lineLength",
      defaultValue = "60"
  )
  void setLineLength(int lineLength);

  @JsonProperty("sortHits")
  InputHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(InputHitSorting sortHits);

  @JsonProperty("seqIdList")
  String getSeqIdList();

  @JsonProperty("seqIdList")
  void setSeqIdList(String seqIdList);

  @JsonProperty("negativeSeqIdList")
  String getNegativeSeqIdList();

  @JsonProperty("negativeSeqIdList")
  void setNegativeSeqIdList(String negativeSeqIdList);

  @JsonProperty("taxIds")
  String getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(String taxIds);

  @JsonProperty("negativeTaxIds")
  String getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(String negativeTaxIds);

  @JsonProperty("taxIdList")
  String getTaxIdList();

  @JsonProperty("taxIdList")
  void setTaxIdList(String taxIdList);

  @JsonProperty("negativeTaxIdList")
  String getNegativeTaxIdList();

  @JsonProperty("negativeTaxIdList")
  void setNegativeTaxIdList(String negativeTaxIdList);

  @JsonProperty("sortHSPs")
  InputHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(InputHSPSorting sortHSPs);

  @JsonProperty("qCovHSPPerc")
  Number getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc(Number qCovHSPPerc);

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  short getNumDescriptions();

  @JsonProperty(
      value = "numDescriptions",
      defaultValue = "500"
  )
  void setNumDescriptions(short numDescriptions);

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  int getNumAlignments();

  @JsonProperty(
      value = "numAlignments",
      defaultValue = "250"
  )
  void setNumAlignments(int numAlignments);

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  int getMaxTargetSeqs();

  @JsonProperty(
      value = "maxTargetSeqs",
      defaultValue = "500"
  )
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("maxHSPs")
  short getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(short maxHSPs);

  @JsonProperty("cullingLimit")
  int getCullingLimit();

  @JsonProperty("cullingLimit")
  void setCullingLimit(int cullingLimit);

  @JsonProperty("bestHitOverhang")
  double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang(double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
  double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge(double bestHitScoreEdge);

  @JsonProperty("dbSize")
  long getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(long dbSize);

  @JsonProperty("searchSpace")
  long getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(long searchSpace);

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  boolean getParseDefLines();

  @JsonProperty(
      value = "parseDefLines",
      defaultValue = "false"
  )
  void setParseDefLines(boolean parseDefLines);

  @JsonProperty("outFmt")
  InputBlastOutFmt getOutFmt();

  @JsonProperty("outFmt")
  void setOutFmt(InputBlastOutFmt outFmt);

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  byte getWordSize();

  @JsonProperty(
      value = "wordSize",
      defaultValue = "3"
  )
  void setWordSize(byte wordSize);

  @JsonProperty("matrix")
  InputBlastScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputBlastScoringMatrix matrix);

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  byte getThreshold();

  @JsonProperty(
      value = "threshold",
      defaultValue = "13"
  )
  void setThreshold(byte threshold);

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  InputBlastSegMask getSeg();

  @JsonProperty(
      value = "seg",
      defaultValue = "{\n"
              + "\"enabled\" : true,\n"
              + "\"window\" : 12,\n"
              + "\"locut\" : 2.2,\n"
              + "\"hicut\" : 2.5\n"
              + "}"
  )
  void setSeg(InputBlastSegMask seg);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "false"
  )
  void setSoftMasking(boolean softMasking);

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  boolean getLcaseMasking();

  @JsonProperty(
      value = "lcaseMasking",
      defaultValue = "false"
  )
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("strand")
  InputBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(InputBlastStrand strand);

  @JsonProperty("dbSoftMask")
  long getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(long dbSoftMask);

  @JsonProperty("dbHardMask")
  long getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(long dbHardMask);

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  byte getQueryGeneticCode();

  @JsonProperty(
      value = "queryGeneticCode",
      defaultValue = "1"
  )
  void setQueryGeneticCode(byte queryGeneticCode);

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  byte getDbGenCode();

  @JsonProperty(
      value = "dbGenCode",
      defaultValue = "1"
  )
  void setDbGenCode(byte dbGenCode);

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  int getMaxIntronLength();

  @JsonProperty(
      value = "maxIntronLength",
      defaultValue = "0"
  )
  void setMaxIntronLength(int maxIntronLength);
}
