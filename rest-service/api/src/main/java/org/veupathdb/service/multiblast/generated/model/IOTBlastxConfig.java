package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonTypeName("tblastx")
@JsonDeserialize(as = IOTBlastxConfigImpl.class)
public interface IOTBlastxConfig extends IOBlastConfig
{
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = IOBlastTool.TBLASTX;

  @JsonProperty(JsonKeys.Strand)
  IOBlastStrand getStrand();

  @JsonProperty(JsonKeys.Strand)
  void setStrand(IOBlastStrand strand);

  @JsonProperty(JsonKeys.QueryGeneticCode)
  Byte getQueryGeneticCode();

  @JsonProperty(JsonKeys.QueryGeneticCode)
  void setQueryGeneticCode(Byte queryGeneticCode);

  @JsonProperty(JsonKeys.WordSize)
  Integer getWordSize();

  @JsonProperty(JsonKeys.WordSize)
  void setWordSize(Integer wordSize);

  @JsonProperty(JsonKeys.MaxIntronLength)
  Integer getMaxIntronLength();

  @JsonProperty(JsonKeys.MaxIntronLength)
  void setMaxIntronLength(Integer maxIntronLength);

  @JsonProperty(JsonKeys.Matrix)
  IOTBlastxScoringMatrix getMatrix();

  @JsonProperty(JsonKeys.Matrix)
  void setMatrix(IOTBlastxScoringMatrix matrix);

  @JsonProperty(JsonKeys.Threshold)
  Double getThreshold();

  @JsonProperty(JsonKeys.Threshold)
  void setThreshold(Double threshold);

  @JsonProperty(JsonKeys.DBGeneticCode)
  Byte getDbGencode();

  @JsonProperty(JsonKeys.DBGeneticCode)
  void setDbGencode(Byte dbGencode);

  @JsonProperty(JsonKeys.Seg)
  IOBlastSegMask getSeg();

  @JsonProperty(JsonKeys.Seg)
  void setSeg(IOBlastSegMask seg);

  @JsonProperty(JsonKeys.SoftMasking)
  Boolean getSoftMasking();

  @JsonProperty(JsonKeys.SoftMasking)
  void setSoftMasking(Boolean softMasking);

  @JsonProperty(JsonKeys.TaxIDs)
  List<Integer> getTaxIds();

  @JsonProperty(JsonKeys.TaxIDs)
  void setTaxIds(List<Integer> taxIds);

  @JsonProperty(JsonKeys.NegativeTaxIDs)
  List<Integer> getNegativeTaxIds();

  @JsonProperty(JsonKeys.NegativeTaxIDs)
  void setNegativeTaxIds(List<Integer> negativeTaxIds);

  @JsonProperty(JsonKeys.DBSoftMask)
  String getDbSoftMask();

  @JsonProperty(JsonKeys.DBSoftMask)
  void setDbSoftMask(String dbSoftMask);

  @JsonProperty(JsonKeys.DBHardMask)
  String getDbHardMask();

  @JsonProperty(JsonKeys.DBHardMask)
  void setDbHardMask(String dbHardMask);

  @JsonProperty(JsonKeys.CullingLimit)
  Integer getCullingLimit();

  @JsonProperty(JsonKeys.CullingLimit)
  void setCullingLimit(Integer cullingLimit);

  @JsonProperty(JsonKeys.BestHitOverhang)
  Double getBestHitOverhang();

  @JsonProperty(JsonKeys.BestHitOverhang)
  void setBestHitOverhang(Double bestHitOverhang);

  @JsonProperty(JsonKeys.BestHitScoreEdge)
  Double getBestHitScoreEdge();

  @JsonProperty(JsonKeys.BestHitScoreEdge)
  void setBestHitScoreEdge(Double bestHitScoreEdge);

  @JsonProperty(JsonKeys.SubjectBestHit)
  Boolean getSubjectBestHit();

  @JsonProperty(JsonKeys.SubjectBestHit)
  void setSubjectBestHit(Boolean subjectBestHit);

  @JsonProperty(JsonKeys.SumStats)
  Boolean getSumStats();

  @JsonProperty(JsonKeys.SumStats)
  void setSumStats(Boolean sumStats);

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  Integer getWindowSize();

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  void setWindowSize(Integer windowSize);
}
