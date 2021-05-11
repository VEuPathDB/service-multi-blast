package mb.api.model.blast;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mb.api.model.blast.impl.IOBlastpConfigImpl;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.model.CompBasedStats;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.field.BlastPTask;
import org.veupathdb.lib.blast.field.ScoringMatrix;
import org.veupathdb.lib.blast.field.Seg;

@JsonTypeName("blastp")
@JsonDeserialize(as = IOBlastpConfigImpl.class)
public interface IOBlastpConfig extends IOBlastConfig
{
  BlastTool _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastP;

  @JsonProperty(JsonKeys.Task)
  BlastPTask getTask();

  @JsonProperty(JsonKeys.Task)
  void setTask(BlastPTask task);

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

  @JsonProperty(JsonKeys.Matrix)
  ScoringMatrix getMatrix();

  @JsonProperty(JsonKeys.Matrix)
  void setMatrix(ScoringMatrix matrix);

  @JsonProperty(JsonKeys.Threshold)
   Double getThreshold();

  @JsonProperty(JsonKeys.Threshold)
  void setThreshold (Double threshold);

  @JsonProperty(JsonKeys.CompositionBasedStats)
  CompBasedStats getCompBasedStats();

  @JsonProperty(JsonKeys.CompositionBasedStats)
  void setCompBasedStats(CompBasedStats compBasedStats);

  @JsonProperty(JsonKeys.Seg)
  Seg getSeg();

  @JsonProperty(JsonKeys.Seg)
  void setSeg(Seg seg);

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

  @JsonProperty(JsonKeys.XDropGap)
   Double getXDropGap();

  @JsonProperty(JsonKeys.XDropGap)
  void setXDropGap (Double xDropGap);

  @JsonProperty(JsonKeys.XDropGapFinal)
   Double getXDropGapFinal();

  @JsonProperty(JsonKeys.XDropGapFinal)
  void setXDropGapFinal (Double xDropGapFinal);

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  Integer getWindowSize();

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  void setWindowSize(Integer windowSize);

  @JsonProperty(JsonKeys.Ungapped)
   Boolean getUngapped();

  @JsonProperty(JsonKeys.Ungapped)
  void setUngapped (Boolean ungapped);

  @JsonProperty(JsonKeys.UseSmithWatermanAligns)
   Boolean getUseSWTraceback();

  @JsonProperty(JsonKeys.UseSmithWatermanAligns)
  void setUseSWTraceback (Boolean useSWTraceback);
}
