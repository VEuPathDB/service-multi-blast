package org.veupathdb.service.multiblast.generated.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.veupathdb.service.multiblast.model.blast.BlastTool;
import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.n.BlastNTask;
import org.veupathdb.service.multiblast.model.io.JsonKeys;

@JsonTypeName("blastn")
@JsonDeserialize(as = IOBlastnConfigImpl.class)
public interface IOBlastnConfig extends IOBlastConfig {
  BlastTool _DISCRIMINATOR_TYPE_NAME = BlastTool.BlastN;

  @JsonProperty(JsonKeys.Strand)
  QueryStrand getStrand();

  @JsonProperty(JsonKeys.Strand)
  void setStrand(QueryStrand strand);

  @JsonProperty(JsonKeys.Task)
  BlastNTask getTask();

  @JsonProperty(JsonKeys.Task)
  void setTask(BlastNTask task);

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

  @JsonProperty(JsonKeys.Penalty)
  Integer getPenalty();

  @JsonProperty(JsonKeys.Penalty)
  void setPenalty(Integer penalty);

  @JsonProperty(JsonKeys.Reward)
  Integer getReward();

  @JsonProperty(JsonKeys.Reward)
  void setReward(Integer reward);

  @JsonProperty(JsonKeys.UseIndex)
   Boolean getUseIndex();

  @JsonProperty(JsonKeys.UseIndex)
  void setUseIndex (Boolean useIndex);

  @JsonProperty(JsonKeys.IndexName)
  String getIndexName();

  @JsonProperty(JsonKeys.IndexName)
  void setIndexName(String indexName);

  @JsonProperty(JsonKeys.Dust)
  IOBlastnDust getDust();

  @JsonProperty(JsonKeys.Dust)
  void setDust(IOBlastnDust dust);

  @JsonProperty(JsonKeys.WindowMaskerTaxID)
  Integer getWindowMaskerTaxid();

  @JsonProperty(JsonKeys.WindowMaskerTaxID)
  void setWindowMaskerTaxid(Integer windowMaskerTaxid);

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

  @JsonProperty(JsonKeys.PercentIdentity)
   Double getPercIdentity();

  @JsonProperty(JsonKeys.PercentIdentity)
  void setPercIdentity (Double percIdentity);

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

  @JsonProperty(JsonKeys.TemplateType)
  IOBlastnDcTemplateType getTemplateType();

  @JsonProperty(JsonKeys.TemplateType)
  void setTemplateType(IOBlastnDcTemplateType templateType);

  @JsonProperty(JsonKeys.TemplateLength)
   Byte getTemplateLength();

  @JsonProperty(JsonKeys.TemplateLength)
  void setTemplateLength (Byte templateLength);

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

  @JsonProperty(JsonKeys.NonGreedy)
   Boolean getNoGreedy();

  @JsonProperty(JsonKeys.NonGreedy)
  void setNoGreedy (Boolean noGreedy);

  @JsonProperty(JsonKeys.MinRawGappedScore)
  Integer getMinRawGappedScore();

  @JsonProperty(JsonKeys.MinRawGappedScore)
  void setMinRawGappedScore(Integer minRawGappedScore);

  @JsonProperty(JsonKeys.Ungapped)
   Boolean getUngapped();

  @JsonProperty(JsonKeys.Ungapped)
  void setUngapped (Boolean ungapped);

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  Integer getWindowSize();

  @JsonProperty(JsonKeys.MultiHitWindowSize)
  void setWindowSize(Integer windowSize);

  @JsonProperty(JsonKeys.OffDiagonalRange)
  Integer getOffDiagonalRange();

  @JsonProperty(JsonKeys.OffDiagonalRange)
  void setOffDiagonalRange(Integer offDiagonalRange);
}
