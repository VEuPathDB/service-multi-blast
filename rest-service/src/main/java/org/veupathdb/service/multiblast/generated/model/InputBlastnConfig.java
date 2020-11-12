package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("blastn")
@JsonDeserialize(
    as = InputBlastnConfigImpl.class
)
public interface InputBlastnConfig extends InputBlastConfig {
  InputBlastTool _DISCRIMINATOR_TYPE_NAME = InputBlastTool.BLASTN;

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

  @JsonProperty("task")
  InputBlastnTask getTask();

  @JsonProperty("task")
  void setTask(InputBlastnTask task);

  @JsonProperty("wordSize")
  short getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(short wordSize);

  @JsonProperty("gapOpen")
  byte getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(byte gapOpen);

  @JsonProperty("gapExtend")
  byte getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(byte gapExtend);

  @JsonProperty("reward")
  byte getReward();

  @JsonProperty("reward")
  void setReward(byte reward);

  @JsonProperty("penalty")
  byte getPenalty();

  @JsonProperty("penalty")
  void setPenalty(byte penalty);

  @JsonProperty("strand")
  InputBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(InputBlastStrand strand);

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  InputBlastnDust getDust();

  @JsonProperty(
      value = "dust",
      defaultValue = "{\n"
              + "\"enable\" : true,\n"
              + "\"level\" : 20,\n"
              + "\"window\" : 64,\n"
              + "\"linker\" : 1\n"
              + "}"
  )
  void setDust(InputBlastnDust dust);

  @JsonProperty("filteringDb")
  String getFilteringDb();

  @JsonProperty("filteringDb")
  void setFilteringDb(String filteringDb);

  @JsonProperty("windowMaskerTaxid")
  long getWindowMaskerTaxid();

  @JsonProperty("windowMaskerTaxid")
  void setWindowMaskerTaxid(long windowMaskerTaxid);

  @JsonProperty("windowMaskerDb")
  String getWindowMaskerDb();

  @JsonProperty("windowMaskerDb")
  void setWindowMaskerDb(String windowMaskerDb);

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
  )
  boolean getSoftMasking();

  @JsonProperty(
      value = "softMasking",
      defaultValue = "true"
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

  @JsonProperty("dbSoftMask")
  int getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(int dbSoftMask);

  @JsonProperty("dbHardMask")
  long getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(long dbHardMask);

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0.0"
  )
  Number getPercIdentity();

  @JsonProperty(
      value = "percIdentity",
      defaultValue = "0.0"
  )
  void setPercIdentity(Number percIdentity);

  @JsonProperty("templateType")
  InputBlastDcTemplateType getTemplateType();

  @JsonProperty("templateType")
  void setTemplateType(InputBlastDcTemplateType templateType);

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  int getTemplateLength();

  @JsonProperty(
      value = "templateLength",
      defaultValue = "18"
  )
  void setTemplateLength(int templateLength);

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  boolean getUseIndex();

  @JsonProperty(
      value = "useIndex",
      defaultValue = "false"
  )
  void setUseIndex(boolean useIndex);

  @JsonProperty("indexName")
  String getIndexName();

  @JsonProperty("indexName")
  void setIndexName(String indexName);

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  double getXdropUngap();

  @JsonProperty(
      value = "xdropUngap",
      defaultValue = "20.0"
  )
  void setXdropUngap(double xdropUngap);

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  double getXdropGap();

  @JsonProperty(
      value = "xdropGap",
      defaultValue = "30.0"
  )
  void setXdropGap(double xdropGap);

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  double getXdropGapFinal();

  @JsonProperty(
      value = "xdropGapFinal",
      defaultValue = "100.0"
  )
  void setXdropGapFinal(double xdropGapFinal);

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  boolean getNoGreedy();

  @JsonProperty(
      value = "noGreedy",
      defaultValue = "false"
  )
  void setNoGreedy(boolean noGreedy);

  @JsonProperty("minRawGappedScore")
  int getMinRawGappedScore();

  @JsonProperty("minRawGappedScore")
  void setMinRawGappedScore(int minRawGappedScore);

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  boolean getUngapped();

  @JsonProperty(
      value = "ungapped",
      defaultValue = "false"
  )
  void setUngapped(boolean ungapped);

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  short getWindowSize();

  @JsonProperty(
      value = "windowSize",
      defaultValue = "40"
  )
  void setWindowSize(short windowSize);
}
