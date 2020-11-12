package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

public class BlastpConfig extends StdBlastConfig
{
  private BlastpTask          task;
  private File                ipgList;
  private File                negativeIpgList;
  private Integer             gapOpen;
  private Integer             gapExtend;
  private Double              xDropGap;
  private Double              xDropGapFinal;
  private Seg                 seg;
  private BlastpScoringMatrix matrix;
  private Double              threshold;
  private Integer             cullingLimit;
  private Double              bestHitOverhang;
  private Double              bestHitScoreEdge;
  private Boolean             subjectBestHit;
  private Boolean             ungapped;
  private CompBasedStats      compBasedStats;
  private Boolean             useSmithWatermanAlignments;

  public BlastpTask getTask() {
    return task;
  }

  public BlastpConfig setTask(BlastpTask task) {
    this.task = task;
    return this;
  }

  public File getIpgList() {
    return ipgList;
  }

  public BlastpConfig setIpgList(File ipgList) {
    this.ipgList = ipgList;
    return this;
  }

  public File getNegativeIpgList() {
    return negativeIpgList;
  }

  public BlastpConfig setNegativeIpgList(File negativeIpgList) {
    this.negativeIpgList = negativeIpgList;
    return this;
  }

  public Integer getGapOpen() {
    return gapOpen;
  }

  public BlastpConfig setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
    return this;
  }

  public Integer getGapExtend() {
    return gapExtend;
  }

  public BlastpConfig setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
    return this;
  }

  public Double getxDropGap() {
    return xDropGap;
  }

  public BlastpConfig setxDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
    return this;
  }

  public Double getxDropGapFinal() {
    return xDropGapFinal;
  }

  public BlastpConfig setxDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
    return this;
  }

  public Seg getSeg() {
    return seg;
  }

  public BlastpConfig setSeg(Seg seg) {
    this.seg = seg;
    return this;
  }

  public BlastpScoringMatrix getMatrix() {
    return matrix;
  }

  public BlastpConfig setMatrix(BlastpScoringMatrix matrix) {
    this.matrix = matrix;
    return this;
  }

  public Double getThreshold() {
    return threshold;
  }

  public BlastpConfig setThreshold(Double threshold) {
    this.threshold = threshold;
    return this;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public BlastpConfig setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
    return this;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public BlastpConfig setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
    return this;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public BlastpConfig setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
    return this;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public BlastpConfig setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
    return this;
  }

  public Boolean getUngapped() {
    return ungapped;
  }

  public BlastpConfig setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
    return this;
  }

  public CompBasedStats getCompBasedStats() {
    return compBasedStats;
  }

  public BlastpConfig setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
    return this;
  }

  public Boolean getUseSmithWatermanAlignments() {
    return useSmithWatermanAlignments;
  }

  public BlastpConfig setUseSmithWatermanAlignments(Boolean useSmithWatermanAlignments) {
    this.useSmithWatermanAlignments = useSmithWatermanAlignments;
    return this;
  }
}
