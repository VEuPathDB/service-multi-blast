package org.veupathdb.service.multiblast.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

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

  @JsonProperty("eValue")
  String getEValue();

  @JsonProperty("eValue")
  void setEValue(String eValue);

  @JsonProperty("outFormat")
  InputBlastOutFmt getOutFormat();

  @JsonProperty("outFormat")
  void setOutFormat(InputBlastOutFmt outFormat);

  @JsonProperty("showGIs")
  Boolean getShowGIs();

  @JsonProperty("showGIs")
  void setShowGIs(boolean showGIs);

  @JsonProperty("numDescriptions")
  Integer getNumDescriptions();

  @JsonProperty("numDescriptions")
  void setNumDescriptions(int numDescriptions);

  @JsonProperty("numAlignments")
  Integer getNumAlignments();

  @JsonProperty("numAlignments")
  void setNumAlignments(int numAlignments);

  @JsonProperty("lineLength")
  Integer getLineLength();

  @JsonProperty("lineLength")
  void setLineLength(int lineLength);

  @JsonProperty("sortHits")
  InputHitSorting getSortHits();

  @JsonProperty("sortHits")
  void setSortHits(InputHitSorting sortHits);

  @JsonProperty("sortHSPs")
  InputHSPSorting getSortHSPs();

  @JsonProperty("sortHSPs")
  void setSortHSPs(InputHSPSorting sortHSPs);

  @JsonProperty("lcaseMasking")
  Boolean getLcaseMasking();

  @JsonProperty("lcaseMasking")
  void setLcaseMasking(boolean lcaseMasking);

  @JsonProperty("qCovHSPPerc")
  Double getQCovHSPPerc();

  @JsonProperty("qCovHSPPerc")
  void setQCovHSPPerc(double qCovHSPPerc);

  @JsonProperty("maxHSPs")
  Integer getMaxHSPs();

  @JsonProperty("maxHSPs")
  void setMaxHSPs(int maxHSPs);

  @JsonProperty("maxTargetSeqs")
  Integer getMaxTargetSeqs();

  @JsonProperty("maxTargetSeqs")
  void setMaxTargetSeqs(int maxTargetSeqs);

  @JsonProperty("dbSize")
  Byte getDbSize();

  @JsonProperty("dbSize")
  void setDbSize(byte dbSize);

  @JsonProperty("searchSpace")
  Byte getSearchSpace();

  @JsonProperty("searchSpace")
  void setSearchSpace(byte searchSpace);

  @JsonProperty("xDropUngap")
  Double getXDropUngap();

  @JsonProperty("xDropUngap")
  void setXDropUngap(double xDropUngap);

  @JsonProperty("parseDefLines")
  Boolean getParseDefLines();

  @JsonProperty("parseDefLines")
  void setParseDefLines(boolean parseDefLines);

  @JsonProperty("strand")
  InputBlastStrand getStrand();

  @JsonProperty("strand")
  void setStrand(InputBlastStrand strand);

  @JsonProperty("queryGeneticCode")
  Byte getQueryGeneticCode();

  @JsonProperty("queryGeneticCode")
  void setQueryGeneticCode(byte queryGeneticCode);

  @JsonProperty("wordSize")
  Byte getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(byte wordSize);

  @JsonProperty("maxIntronLength")
  Integer getMaxIntronLength();

  @JsonProperty("maxIntronLength")
  void setMaxIntronLength(int maxIntronLength);

  @JsonProperty("matrix")
  InputTBlastxScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(InputTBlastxScoringMatrix matrix);

  @JsonProperty("threshold")
  Double getThreshold();

  @JsonProperty("threshold")
  void setThreshold(double threshold);

  @JsonProperty("dbGencode")
  Byte getDbGencode();

  @JsonProperty("dbGencode")
  void setDbGencode(byte dbGencode);

  @JsonProperty("subjectLoc")
  InputBlastLocation getSubjectLoc();

  @JsonProperty("subjectLoc")
  void setSubjectLoc(InputBlastLocation subjectLoc);

  @JsonProperty("seg")
  InputBlastSegMask getSeg();

  @JsonProperty("seg")
  void setSeg(InputBlastSegMask seg);

  @JsonProperty("softMasking")
  Boolean getSoftMasking();

  @JsonProperty("softMasking")
  void setSoftMasking(boolean softMasking);

  @JsonProperty("taxIds")
  List<String> getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(List<String> taxIds);

  @JsonProperty("negativeTaxIds")
  List<String> getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(List<String> negativeTaxIds);

  @JsonProperty("dbSoftMask")
  String getDbSoftMask();

  @JsonProperty("dbSoftMask")
  void setDbSoftMask(String dbSoftMask);

  @JsonProperty("dbHardMask")
  String getDbHardMask();

  @JsonProperty("dbHardMask")
  void setDbHardMask(String dbHardMask);

  @JsonProperty("cullingLimit")
  Integer getCullingLimit();

  @JsonProperty("cullingLimit")
  void setCullingLimit(int cullingLimit);

  @JsonProperty("bestHitOverhang")
  Double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang(double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
  Double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge(double bestHitScoreEdge);

  @JsonProperty("subjectBesthit")
  Boolean getSubjectBesthit();

  @JsonProperty("subjectBesthit")
  void setSubjectBesthit(boolean subjectBesthit);

  @JsonProperty("sumStats")
  Boolean getSumStats();

  @JsonProperty("sumStats")
  void setSumStats(boolean sumStats);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(int windowSize);
}
