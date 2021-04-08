package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.x.BlastxTask;

@JsonTypeName("blastx")
@JsonDeserialize(as = IOBlastxConfigImpl.class)
public interface IOBlastxConfig extends IOBlastConfig {
  BlastTool _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastX;

  @JsonProperty("strand")
  QueryStrand getStrand();

  @JsonProperty("strand")
  void setStrand(QueryStrand strand);

  @JsonProperty("queryGeneticCode")
   Byte getQueryGeneticCode();

  @JsonProperty("queryGeneticCode")
  void setQueryGeneticCode (Byte queryGeneticCode);

  @JsonProperty("task")
  BlastxTask getTask();

  @JsonProperty("task")
  void setTask(BlastxTask task);

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

  @JsonProperty("maxIntronLength")
  Integer getMaxIntronLength();

  @JsonProperty("maxIntronLength")
  void setMaxIntronLength(Integer maxIntronLength);

  @JsonProperty("matrix")
  IOBlastxScoringMatrix getMatrix();

  @JsonProperty("matrix")
  void setMatrix(IOBlastxScoringMatrix matrix);

  @JsonProperty("threshold")
   Double getThreshold();

  @JsonProperty("threshold")
  void setThreshold (Double threshold);

  @JsonProperty("compBasedStats")
  CompBasedStats getCompBasedStats();

  @JsonProperty("compBasedStats")
  void setCompBasedStats(CompBasedStats compBasedStats);

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

  @JsonProperty("windowSize")
  Integer getWindowSize();

  @JsonProperty("windowSize")
  void setWindowSize(Integer windowSize);

  @JsonProperty("ungapped")
   Boolean getUngapped();

  @JsonProperty("ungapped")
  void setUngapped (Boolean ungapped);

  @JsonProperty("useSWTraceback")
   Boolean getUseSWTraceback();

  @JsonProperty("useSWTraceback")
  void setUseSWTraceback (Boolean useSWTraceback);
}
