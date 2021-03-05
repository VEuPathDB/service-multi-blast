package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeName("tblastn")
@JsonDeserialize(as = IOTBlastnConfigImpl.class)
public interface IOTBlastnConfig extends IOBlastConfig {
  IOBlastTool _DISCRIMINATOR_TYPE_NAME = IOBlastTool.TBLASTN;

  @JsonProperty("task")
  IOTBlastnTask getTask();

  @JsonProperty("task")
  void setTask(IOTBlastnTask task);

  @JsonProperty("wordSize")
  Integer getWordSize();

  @JsonProperty("wordSize")
  void setWordSize(Integer wordSize);

  @JsonProperty("gapOpen")
  Integer getGapOpen();

  @JsonProperty("gapOpen")
  void setGapOpen(Integer gapOpen);

  @JsonProperty("gapExtend")
  Integer getGapExtend();

  @JsonProperty("gapExtend")
  void setGapExtend(Integer gapExtend);

  @JsonProperty("dbGencode")
   Byte getDbGencode();

  @JsonProperty("dbGencode")
  void setDbGencode (Byte dbGencode);

  @JsonProperty("maxIntronLength")
  Integer getMaxIntronLength();

  @JsonProperty("maxIntronLength")
  void setMaxIntronLength(Integer maxIntronLength);

  @JsonProperty("matrix")
  IOTBlastnScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(IOTBlastnScoringMatrix matrix);

  @JsonProperty("threshold")
   Double getThreshold();

  @JsonProperty("threshold")
  void setThreshold (Double threshold);

  @JsonProperty("compBasedStats")
  IOBlastCompBasedStats getCompBasedStats();

  @JsonProperty("compBasedStats")
  void setCompBasedStats(IOBlastCompBasedStats compBasedStats);

  @JsonProperty("seg")
  IOBlastSegMask getSeg();

  @JsonProperty("seg")
  void setSeg(IOBlastSegMask seg);

  @JsonProperty("softMasking")
   Boolean getSoftMasking();

  @JsonProperty("softMasking")
  void setSoftMasking (Boolean softMasking);

  @JsonProperty("taxIds")
  List<Integer> getTaxIds();

  @JsonProperty("taxIds")
  void setTaxIds(List<Integer> taxIds);

  @JsonProperty("negativeTaxIds")
  List<Integer> getNegativeTaxIds();

  @JsonProperty("negativeTaxIds")
  void setNegativeTaxIds(List<Integer> negativeTaxIds);

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
  void setCullingLimit(Integer cullingLimit);

  @JsonProperty("bestHitOverhang")
   Double getBestHitOverhang();

  @JsonProperty("bestHitOverhang")
  void setBestHitOverhang (Double bestHitOverhang);

  @JsonProperty("bestHitScoreEdge")
   Double getBestHitScoreEdge();

  @JsonProperty("bestHitScoreEdge")
  void setBestHitScoreEdge (Double bestHitScoreEdge);

  @JsonProperty("subjectBestHit")
   Boolean getSubjectBestHit();

  @JsonProperty("subjectBestHit")
  void setSubjectBestHit (Boolean subjectBestHit);

  @JsonProperty("sumStats")
   Boolean getSumStats();

  @JsonProperty("sumStats")
  void setSumStats (Boolean sumStats);

  @JsonProperty("xDropGap")
   Double getXDropGap();

  @JsonProperty("xDropGap")
  void setXDropGap (Double xDropGap);

  @JsonProperty("xDropGapFinal")
   Double getXDropGapFinal();

  @JsonProperty("xDropGapFinal")
  void setXDropGapFinal (Double xDropGapFinal);

  @JsonProperty("ungapped")
   Boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped (Boolean ungapped);

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty("useSWTraceback")
   Boolean getUseSWTraceback();

  @JsonProperty("useSWTraceback")
  void setUseSWTraceback (Boolean useSWTraceback);
}
