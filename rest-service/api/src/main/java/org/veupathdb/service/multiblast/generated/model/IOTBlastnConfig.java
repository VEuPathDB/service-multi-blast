package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonTypeName("tblastn")
@JsonDeserialize(as = IOTBlastnConfigImpl.class)
public interface IOTBlastnConfig extends IOBlastConfig {
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = IOBlastTool.TBLASTN;

  @JsonProperty(JsonKeys.Task)
  IOTBlastnTask getTask();

  @JsonProperty(JsonKeys.Task)
  void setTask(IOTBlastnTask task);

  @JsonProperty(JsonKeys.WordSize)
  Integer getWordSize();

  @JsonProperty(JsonKeys.WordSize)
  void setWordSize(Integer wordSize);

  @JsonProperty(JsonKeys.GapOpen)
  Integer getGapOpen();

  @JsonProperty(JsonKeys.GapOpen)
  void setGapOpen(Integer gapOpen);

  @JsonProperty(JsonKeys.GapExtend)
  Integer getGapExtend();

  @JsonProperty(JsonKeys.GapExtend)
  void setGapExtend(Integer gapExtend);

  @JsonProperty(JsonKeys.DBGeneticCode)
   Byte getDbGencode();

  @JsonProperty(JsonKeys.DBGeneticCode)
  void setDbGencode (Byte dbGencode);

  @JsonProperty(JsonKeys.MaxIntronLength)
  Integer getMaxIntronLength();

  @JsonProperty(JsonKeys.MaxIntronLength)
  void setMaxIntronLength(Integer maxIntronLength);

  @JsonProperty(JsonKeys.Matrix)
  IOTBlastnScoringMatrix getMatrix();

  @JsonProperty(JsonKeys.Matrix)
  void setMatrix(IOTBlastnScoringMatrix matrix);

  @JsonProperty(JsonKeys.Threshold)
   Double getThreshold();

  @JsonProperty(JsonKeys.Threshold)
  void setThreshold (Double threshold);

  @JsonProperty(JsonKeys.CompositionBasedStats)
  IOBlastCompBasedStats getCompBasedStats();

  @JsonProperty(JsonKeys.CompositionBasedStats)
  void setCompBasedStats(IOBlastCompBasedStats compBasedStats);

  @JsonProperty(JsonKeys.Seg)
  IOBlastSegMask getSeg();

  @JsonProperty(JsonKeys.Seg)
  void setSeg(IOBlastSegMask seg);

  @JsonProperty(JsonKeys.SoftMasking)
   Boolean getSoftMasking();

  @JsonProperty(JsonKeys.SoftMasking)
  void setSoftMasking (Boolean softMasking);

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
  void setBestHitOverhang (Double bestHitOverhang);

  @JsonProperty(JsonKeys.BestHitScoreEdge)
   Double getBestHitScoreEdge();

  @JsonProperty(JsonKeys.BestHitScoreEdge)
  void setBestHitScoreEdge (Double bestHitScoreEdge);

  @JsonProperty(JsonKeys.SubjectBestHit)
   Boolean getSubjectBestHit();

  @JsonProperty(JsonKeys.SubjectBestHit)
  void setSubjectBestHit (Boolean subjectBestHit);

  @JsonProperty(JsonKeys.SumStats)
   Boolean getSumStats();

  @JsonProperty(JsonKeys.SumStats)
  void setSumStats (Boolean sumStats);

  @JsonProperty(JsonKeys.XDropGap)
   Double getXDropGap();

  @JsonProperty(JsonKeys.XDropGap)
  void setXDropGap (Double xDropGap);

  @JsonProperty(JsonKeys.XDropGapFinal)
   Double getXDropGapFinal();

  @JsonProperty(JsonKeys.XDropGapFinal)
  void setXDropGapFinal (Double xDropGapFinal);

  @JsonProperty(JsonKeys.Ungapped)
   Boolean getUngapped();

  @JsonProperty(JsonKeys.Ungapped)
  void setUngapped (Boolean ungapped);

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  Integer getWindowSize();

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  void setWindowSize(Integer windowSize);

  @JsonProperty(JsonKeys.UseSmithWatermanAligns)
   Boolean getUseSWTraceback();

  @JsonProperty(JsonKeys.UseSmithWatermanAligns)
  void setUseSWTraceback (Boolean useSWTraceback);
}
