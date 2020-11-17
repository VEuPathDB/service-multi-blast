package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

public class TBlastNConfig extends StdBlastConfig
{
  private TBlastnTask          task;
  private Integer              gapOpen;
  private Integer              gapExtend;
  private Double               xDropGap;
  private Double               xDropGapFinal;
  private Boolean              sumStats;
  private Integer              dbGenCode;
  private Boolean              ungapped;
  private Integer              maxIntronLength;
  private Seg                  seg;
  private TBlastnScoringMatrix matrix;
  private Double               threshold;
  private Integer              cullingLimit;
  private Double               bestHitOverhang;
  private Double               bestHitScoreEdge;
  private Boolean              subjectBestHit;
  private CompBasedStats       compBasedStats;
  private Boolean              useSmithWatermanAlignments;
  private File                 inPssm;

  public TBlastnTask getTask() {
    return task;
  }

  public TBlastNConfig setTask(TBlastnTask task) {
    this.task = task;
    return this;
  }

  public Integer getGapOpen() {
    return gapOpen;
  }

  public TBlastNConfig setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
    return this;
  }

  public Integer getGapExtend() {
    return gapExtend;
  }

  public TBlastNConfig setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
    return this;
  }

  public Double getxDropGap() {
    return xDropGap;
  }

  public TBlastNConfig setxDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
    return this;
  }

  public Double getxDropGapFinal() {
    return xDropGapFinal;
  }

  public TBlastNConfig setxDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
    return this;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public TBlastNConfig setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
    return this;
  }

  public Integer getDbGenCode() {
    return dbGenCode;
  }

  public TBlastNConfig setDbGenCode(Integer dbGenCode) {
    this.dbGenCode = dbGenCode;
    return this;
  }

  public Boolean getUngapped() {
    return ungapped;
  }

  public TBlastNConfig setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
    return this;
  }

  public Integer getMaxIntronLength() {
    return maxIntronLength;
  }

  public TBlastNConfig setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
    return this;
  }

  public Seg getSeg() {
    return seg;
  }

  public TBlastNConfig setSeg(Seg seg) {
    this.seg = seg;
    return this;
  }

  public TBlastnScoringMatrix getMatrix() {
    return matrix;
  }

  public TBlastNConfig setMatrix(TBlastnScoringMatrix matrix) {
    this.matrix = matrix;
    return this;
  }

  public Double getThreshold() {
    return threshold;
  }

  public TBlastNConfig setThreshold(Double threshold) {
    this.threshold = threshold;
    return this;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public TBlastNConfig setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
    return this;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public TBlastNConfig setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
    return this;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public TBlastNConfig setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
    return this;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public TBlastNConfig setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
    return this;
  }

  public CompBasedStats getCompBasedStats() {
    return compBasedStats;
  }

  public TBlastNConfig setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
    return this;
  }

  public Boolean getUseSmithWatermanAlignments() {
    return useSmithWatermanAlignments;
  }

  public TBlastNConfig setUseSmithWatermanAlignments(Boolean useSmithWatermanAlignments) {
    this.useSmithWatermanAlignments = useSmithWatermanAlignments;
    return this;
  }

  public File getInPssm() {
    return inPssm;
  }

  public TBlastNConfig setInPssm(File inPssm) {
    this.inPssm = inPssm;
    return this;
  }
}
