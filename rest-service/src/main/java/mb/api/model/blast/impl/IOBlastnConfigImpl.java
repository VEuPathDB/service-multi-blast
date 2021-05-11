package mb.api.model.blast.impl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import mb.api.model.blast.IOBlastnConfig;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.field.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("blastn")
public class IOBlastnConfigImpl extends IOBlastConfigImpl implements IOBlastnConfig
{
  private final BlastTool tool = IOBlastnConfig._DISCRIMINATOR_TYPE_NAME;

  private Strand         strand;
  private BlastNTask     task;
  private Long           wordSize;
  private Integer        gapOpen;
  private Integer        gapExtend;
  private Integer        penalty;
  private Long           reward;
  private Boolean        useIndex;
  private String         indexName;
  private Dust           dust;
  private Integer        windowMaskerTaxid;
  private Boolean        softMasking;
  private List<Integer>  taxIds;
  private List<Integer>  negativeTaxIds;
  private String         dbSoftMask;
  private String         dbHardMask;
  private Double         percIdentity;
  private Long           cullingLimit;
  private Double         bestHitOverhang;
  private Double         bestHitScoreEdge;
  private Boolean        subjectBestHit;
  private TemplateType   templateType;
  private TemplateLength templateLength;
  private Boolean        sumStats;
  private Double         xDropGap;
  private Double         xDropGapFinal;
  private Boolean        noGreedy;
  private Integer        minRawGappedScore;
  private Boolean        ungapped;
  private Long           windowSize;
  private Long           offDiagonalRange;

  @JsonProperty("tool")
  public BlastTool getTool() {
    return this.tool;
  }

  @JsonProperty("strand")
  public Strand getStrand() {
    return this.strand;
  }

  @JsonProperty("strand")
  public void setStrand(Strand strand) {
    this.strand = strand;
  }

  @JsonProperty("task")
  public BlastNTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(BlastNTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public Long getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(Long wordSize) {
    this.wordSize = wordSize;
  }

  @JsonProperty("gapOpen")
  public Integer getGapOpen() {
    return this.gapOpen;
  }

  @JsonProperty("gapOpen")
  public void setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
  }

  @JsonProperty("gapExtend")
  public Integer getGapExtend() {
    return this.gapExtend;
  }

  @JsonProperty("gapExtend")
  public void setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
  }

  @JsonProperty("penalty")
  public Integer getPenalty() {
    return this.penalty;
  }

  @JsonProperty("penalty")
  public void setPenalty(Integer penalty) {
    this.penalty = penalty;
  }

  @JsonProperty("reward")
  public Long getReward() {
    return this.reward;
  }

  @JsonProperty("reward")
  public void setReward(Long reward) {
    this.reward = reward;
  }

  @JsonProperty("useIndex")
  public Boolean getUseIndex() {
    return this.useIndex;
  }

  @JsonProperty("useIndex")
  public void setUseIndex(Boolean useIndex) {
    this.useIndex = useIndex;
  }

  @JsonProperty("indexName")
  public String getIndexName() {
    return this.indexName;
  }

  @JsonProperty("indexName")
  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  @JsonProperty("dust")
  public Dust getDust() {
    return this.dust;
  }

  @JsonProperty("dust")
  public void setDust(Dust dust) {
    this.dust = dust;
  }

  @JsonProperty("windowMaskerTaxid")
  public Integer getWindowMaskerTaxid() {
    return this.windowMaskerTaxid;
  }

  @JsonProperty("windowMaskerTaxid")
  public void setWindowMaskerTaxid(Integer windowMaskerTaxid) {
    this.windowMaskerTaxid = windowMaskerTaxid;
  }

  @JsonProperty("softMasking")
  public Boolean getSoftMasking() {
    return this.softMasking;
  }

  @JsonProperty("softMasking")
  public void setSoftMasking(Boolean softMasking) {
    this.softMasking = softMasking;
  }

  @JsonProperty("taxIds")
  public List<Integer> getTaxIds() {
    return this.taxIds;
  }

  @JsonProperty("taxIds")
  public void setTaxIds(List<Integer> taxIds) {
    this.taxIds = taxIds;
  }

  @JsonProperty("negativeTaxIds")
  public List<Integer> getNegativeTaxIds() {
    return this.negativeTaxIds;
  }

  @JsonProperty("negativeTaxIds")
  public void setNegativeTaxIds(List<Integer> negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
  }

  @JsonProperty("dbSoftMask")
  public String getDbSoftMask() {
    return this.dbSoftMask;
  }

  @JsonProperty("dbSoftMask")
  public void setDbSoftMask(String dbSoftMask) {
    this.dbSoftMask = dbSoftMask;
  }

  @JsonProperty("dbHardMask")
  public String getDbHardMask() {
    return this.dbHardMask;
  }

  @JsonProperty("dbHardMask")
  public void setDbHardMask(String dbHardMask) {
    this.dbHardMask = dbHardMask;
  }

  @JsonProperty("percIdentity")
  public Double getPercIdentity() {
    return this.percIdentity;
  }

  @JsonProperty("percIdentity")
  public void setPercIdentity(Double percIdentity) {
    this.percIdentity = percIdentity;
  }

  @JsonProperty("cullingLimit")
  public Long getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(Long cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  @JsonProperty("bestHitOverhang")
  public Double getBestHitOverhang() {
    return this.bestHitOverhang;
  }

  @JsonProperty("bestHitOverhang")
  public void setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  @JsonProperty("bestHitScoreEdge")
  public Double getBestHitScoreEdge() {
    return this.bestHitScoreEdge;
  }

  @JsonProperty("bestHitScoreEdge")
  public void setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  @JsonProperty("subjectBestHit")
  public Boolean getSubjectBestHit() {
    return this.subjectBestHit;
  }

  @JsonProperty("subjectBestHit")
  public void setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  @JsonProperty("templateType")
  public TemplateType getTemplateType() {
    return this.templateType;
  }

  @JsonProperty("templateType")
  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  @JsonProperty("templateLength")
  public TemplateLength getTemplateLength() {
    return this.templateLength;
  }

  @JsonProperty("templateLength")
  public void setTemplateLength(TemplateLength templateLength) {
    this.templateLength = templateLength;
  }

  @JsonProperty("sumStats")
  public Boolean getSumStats() {
    return this.sumStats;
  }

  @JsonProperty("sumStats")
  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  @JsonProperty("xDropGap")
  public Double getXDropGap() {
    return this.xDropGap;
  }

  @JsonProperty("xDropGap")
  public void setXDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
  }

  @JsonProperty("xDropGapFinal")
  public Double getXDropGapFinal() {
    return this.xDropGapFinal;
  }

  @JsonProperty("xDropGapFinal")
  public void setXDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
  }

  @JsonProperty("noGreedy")
  public Boolean getNoGreedy() {
    return this.noGreedy;
  }

  @JsonProperty("noGreedy")
  public void setNoGreedy(Boolean noGreedy) {
    this.noGreedy = noGreedy;
  }

  @JsonProperty("minRawGappedScore")
  public Integer getMinRawGappedScore() {
    return this.minRawGappedScore;
  }

  @JsonProperty("minRawGappedScore")
  public void setMinRawGappedScore(Integer minRawGappedScore) {
    this.minRawGappedScore = minRawGappedScore;
  }

  @JsonProperty("ungapped")
  public Boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty("windowSize")
  public Long getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Long windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty("offDiagonalRange")
  public Long getOffDiagonalRange() {
    return this.offDiagonalRange;
  }

  @JsonProperty("offDiagonalRange")
  public void setOffDiagonalRange(Long offDiagonalRange) {
    this.offDiagonalRange = offDiagonalRange;
  }
}
