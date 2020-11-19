package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

import org.veupathdb.service.multiblast.model.CLISerializable;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class BlastNConfig extends StdBlastConfig implements CLISerializable
{
  private QueryStrand  strand;
  private BlastnTask   task;
  private Byte         gapOpen;
  private Byte         gapExtend;
  private Byte         penalty;
  private Byte         reward;
  private boolean      useIndex;
  private String       indexName;
  private Dust         dust;
  private File         filteringDb;
  private Integer      windowMaskerTaxID;
  private File         windowMaskerDB;
  private Double       percIdentity;
  private Integer      cullingLimit;
  private Double       bestHitOverhang;
  private Double       bestHitScoreEdge;
  private boolean      subjectBestHit;
  private TemplateType templateType;
  private Byte         templateLength;
  private Boolean      sumStats;
  private Double       extDropoffPrelimGapped;
  private Double       extDropoffFinalGapped;
  private boolean      nonGreedyProgramExt;
  private Integer      minRawGappedScore;
  private boolean      ungappedAlignment;
  private Short        wordSize;

  public QueryStrand getStrand() {
    return strand;
  }

  public Short getWordSize() {
    return wordSize;
  }

  public void setStrand(QueryStrand strand) {
    this.strand = strand;
  }

  public BlastnTask getTask() {
    return task;
  }

  public void setTask(BlastnTask task) {
    this.task = task;
  }

  public Byte getGapOpen() {
    return gapOpen;
  }

  public void setGapOpen(Byte gapOpen) {
    this.gapOpen = gapOpen;
  }

  public Byte getGapExtend() {
    return gapExtend;
  }

  public void setGapExtend(Byte gapExtend) {
    this.gapExtend = gapExtend;
  }

  public Byte getPenalty() {
    return penalty;
  }

  public void setPenalty(Byte penalty) {
    this.penalty = penalty;
  }

  public Byte getReward() {
    return reward;
  }

  public void setReward(Byte reward) {
    this.reward = reward;
  }

  public boolean isUseIndexEnabled() {
    return useIndex;
  }

  public void setUseIndexEnabled(boolean useIndex) {
    this.useIndex = useIndex;
  }

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public Dust getDust() {
    return dust;
  }

  public void setDust(Dust dust) {
    this.dust = dust;
  }

  public File getFilteringDb() {
    return filteringDb;
  }

  public void setFilteringDb(File filteringDb) {
    this.filteringDb = filteringDb;
  }

  public Integer getWindowMaskerTaxID() {
    return windowMaskerTaxID;
  }

  public void setWindowMaskerTaxID(Integer windowMaskerTaxID) {
    this.windowMaskerTaxID = windowMaskerTaxID;
  }

  public File getWindowMaskerDB() {
    return windowMaskerDB;
  }

  public void setWindowMaskerDB(File windowMaskerDB) {
    this.windowMaskerDB = windowMaskerDB;
  }

  public Double getPercIdentity() {
    return percIdentity;
  }

  public void setPercIdentity(Double percIdentity) {
    this.percIdentity = percIdentity;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public void setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public void setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public void setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
  }

  public boolean isSubjectBestHitEnabled() {
    return subjectBestHit;
  }

  public void setSubjectBestHitEnabled(boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
  }

  public TemplateType getTemplateType() {
    return templateType;
  }

  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  public Byte getTemplateLength() {
    return templateLength;
  }

  public void setTemplateLength(Byte templateLength) {
    this.templateLength = templateLength;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public void setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
  }

  public Double getExtDropoffPrelimGapped() {
    return extDropoffPrelimGapped;
  }

  public BlastNConfig setExtDropoffPrelimGapped(Double extDropoffPrelimGapped) {
    this.extDropoffPrelimGapped = extDropoffPrelimGapped;
    return this;
  }

  public Double getExtDropoffFinalGapped() {
    return extDropoffFinalGapped;
  }

  public BlastNConfig setExtDropoffFinalGapped(Double extDropoffFinalGapped) {
    this.extDropoffFinalGapped = extDropoffFinalGapped;
    return this;
  }

  public boolean isNonGreedyProgramExtEnabled() {
    return nonGreedyProgramExt;
  }

  public BlastNConfig setNonGreedyProgramExtEnabled(boolean nonGreedyProgramExt) {
    this.nonGreedyProgramExt = nonGreedyProgramExt;
    return this;
  }

  public Integer getMinRawGappedScore() {
    return minRawGappedScore;
  }

  public BlastNConfig setMinRawGappedScore(Integer minRawGappedScore) {
    this.minRawGappedScore = minRawGappedScore;
    return this;
  }

  public boolean isUngappedAlignmentEnabled() {
    return ungappedAlignment;
  }

  public BlastNConfig setUngappedAlignmentEnabled(boolean ungappedAlignment) {
    this.ungappedAlignment = ungappedAlignment;
    return this;
  }

  public StdBlastConfig setWordSize(Short wordSize) {
    this.wordSize = wordSize;
    return this;
  }

  @Override
  public void toArgs(CliBuilder args) {
    super.toArgs(args);

    args.appendNonNull(ToolOption.Strand, strand)
      .appendNonNull(ToolOption.Task, task)
      .appendNonNull(ToolOption.GapOpenCost, gapOpen)
      .appendNonNull(ToolOption.GapExtendCost, gapExtend)
      .appendNonNull(ToolOption.MismatchPenalty, penalty)
      .appendNonNull(ToolOption.MatchReward, reward)
      .appendNonNull(ToolOption.MegablastIndexName, indexName)
      .appendNonNull(ToolOption.Dust, dust)
      .appendNonNull(ToolOption.FilteringDatabase, filteringDb)
      .appendNonNull(ToolOption.WindowMaskerTaxonomicID, windowMaskerTaxID)
      .appendNonNull(ToolOption.WindowMaskerDatabase, windowMaskerDB)
      .appendNonNull(ToolOption.PercentIdentity, percIdentity)
      .appendNonNull(ToolOption.CullingLimit, cullingLimit)
      .appendNonNull(ToolOption.BestHitOverhang, bestHitOverhang)
      .appendNonNull(ToolOption.BestHitScoreEdge, bestHitScoreEdge)
      .appendNonNull(ToolOption.MegablastTemplateType, templateType)
      .appendNonNull(ToolOption.MegablastTemplateLength, templateLength)
      .appendNonNull(ToolOption.SumStats, sumStats)
      .appendNonNull(ToolOption.XDropoffPrelimGappedExtensions, extDropoffPrelimGapped)
      .appendNonNull(ToolOption.XDropoffFinalGappedExtensions, extDropoffFinalGapped)
      .appendNonNull(ToolOption.MinRawGappedScore, minRawGappedScore)
      .appendNonNull(ToolOption.WordSize, wordSize);

    if (useIndex)
      args.set(ToolOption.UseMegablastIndex, true);
    if (subjectBestHit)
      args.set(ToolOption.SubjectBestHit);
    if (nonGreedyProgramExt)
      args.set(ToolOption.NonGreedyExtension);
    if (ungappedAlignment)
      args.set(ToolOption.UngappedAlignmentOnly);
  }
}
