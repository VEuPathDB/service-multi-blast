package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

public class BlastPConfig extends StdBlastConfig
{
  private BlastpTask          task;
  private File                ipgList;
  private File                negativeIpgList;
  private Byte                gapOpen;
  private Byte                gapExtend;
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

  public BlastPConfig setTask(BlastpTask task) {
    this.task = task;
    return this;
  }

  public File getIpgList() {
    return ipgList;
  }

  public BlastPConfig setIpgList(File ipgList) {
    this.ipgList = ipgList;
    return this;
  }

  public File getNegativeIpgList() {
    return negativeIpgList;
  }

  public BlastPConfig setNegativeIpgList(File negativeIpgList) {
    this.negativeIpgList = negativeIpgList;
    return this;
  }

  public Byte getGapOpen() {
    return gapOpen;
  }

  public BlastPConfig setGapOpen(Byte gapOpen) {
    this.gapOpen = gapOpen;
    return this;
  }

  public Byte getGapExtend() {
    return gapExtend;
  }

  public BlastPConfig setGapExtend(Byte gapExtend) {
    this.gapExtend = gapExtend;
    return this;
  }

  public Double getxDropGap() {
    return xDropGap;
  }

  public BlastPConfig setxDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
    return this;
  }

  public Double getxDropGapFinal() {
    return xDropGapFinal;
  }

  public BlastPConfig setxDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
    return this;
  }

  public Seg getSeg() {
    return seg;
  }

  public BlastPConfig setSeg(Seg seg) {
    this.seg = seg;
    return this;
  }

  public BlastpScoringMatrix getMatrix() {
    return matrix;
  }

  public BlastPConfig setMatrix(BlastpScoringMatrix matrix) {
    this.matrix = matrix;
    return this;
  }

  public Double getThreshold() {
    return threshold;
  }

  public BlastPConfig setThreshold(Double threshold) {
    this.threshold = threshold;
    return this;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public BlastPConfig setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
    return this;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public BlastPConfig setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
    return this;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public BlastPConfig setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
    return this;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public BlastPConfig setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
    return this;
  }

  public Boolean getUngapped() {
    return ungapped;
  }

  public BlastPConfig setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
    return this;
  }

  public CompBasedStats getCompBasedStats() {
    return compBasedStats;
  }

  public BlastPConfig setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
    return this;
  }

  public Boolean getUseSmithWatermanAlignments() {
    return useSmithWatermanAlignments;
  }

  public BlastPConfig setUseSmithWatermanAlignments(Boolean useSmithWatermanAlignments) {
    this.useSmithWatermanAlignments = useSmithWatermanAlignments;
    return this;
  }
}
