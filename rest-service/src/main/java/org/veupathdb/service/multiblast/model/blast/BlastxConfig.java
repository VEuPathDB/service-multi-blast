package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

public class BlastxConfig extends StdBlastConfig
{
  private BlastxTask          task;
  private File                ipgList;
  private File                negativeIpgList;
  private Integer             gapOpen;
  private Integer             gapExtend;
  private Double              xDropGap;
  private Double              xDropGapFinal;
  private Boolean             sumStats;
  private Integer             maxIntronLength;
  private Seg                 seg;
  private BlastxScoringMatrix matrix;
  private Double              threshold;
  private Integer             cullingLimit;
  private Double              bestHitOverhang;
  private Double              bestHitScoreEdge;
  private Boolean             subjectBestHit;
  private Boolean             ungapped;
  private QueryStrand         strand;
  private Integer             queryGenCode;
  private CompBasedStats      compBasedStats;
  private Boolean             useSmithWatermanAlignment;

  public BlastxTask getTask() {
    return task;
  }

  public BlastxConfig setTask(BlastxTask task) {
    this.task = task;
    return this;
  }

  public File getIpgList() {
    return ipgList;
  }

  public BlastxConfig setIpgList(File ipgList) {
    this.ipgList = ipgList;
    return this;
  }

  public File getNegativeIpgList() {
    return negativeIpgList;
  }

  public BlastxConfig setNegativeIpgList(File negativeIpgList) {
    this.negativeIpgList = negativeIpgList;
    return this;
  }

  public Integer getGapOpen() {
    return gapOpen;
  }

  public BlastxConfig setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
    return this;
  }

  public Integer getGapExtend() {
    return gapExtend;
  }

  public BlastxConfig setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
    return this;
  }

  public Double getxDropGap() {
    return xDropGap;
  }

  public BlastxConfig setxDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
    return this;
  }

  public Double getxDropGapFinal() {
    return xDropGapFinal;
  }

  public BlastxConfig setxDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
    return this;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public BlastxConfig setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
    return this;
  }

  public Integer getMaxIntronLength() {
    return maxIntronLength;
  }

  public BlastxConfig setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
    return this;
  }

  public Seg getSeg() {
    return seg;
  }

  public BlastxConfig setSeg(Seg seg) {
    this.seg = seg;
    return this;
  }

  public BlastxScoringMatrix getMatrix() {
    return matrix;
  }

  public BlastxConfig setMatrix(BlastxScoringMatrix matrix) {
    this.matrix = matrix;
    return this;
  }

  public Double getThreshold() {
    return threshold;
  }

  public BlastxConfig setThreshold(Double threshold) {
    this.threshold = threshold;
    return this;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public BlastxConfig setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
    return this;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public BlastxConfig setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
    return this;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public BlastxConfig setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
    return this;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public BlastxConfig setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
    return this;
  }

  public Boolean getUngapped() {
    return ungapped;
  }

  public BlastxConfig setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
    return this;
  }

  public QueryStrand getStrand() {
    return strand;
  }

  public BlastxConfig setStrand(QueryStrand strand) {
    this.strand = strand;
    return this;
  }

  public Integer getQueryGenCode() {
    return queryGenCode;
  }

  public BlastxConfig setQueryGenCode(Integer queryGenCode) {
    this.queryGenCode = queryGenCode;
    return this;
  }

  public CompBasedStats getCompBasedStats() {
    return compBasedStats;
  }

  public BlastxConfig setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
    return this;
  }

  public Boolean getUseSmithWatermanAlignment() {
    return useSmithWatermanAlignment;
  }

  public BlastxConfig setUseSmithWatermanAlignment(Boolean useSmithWatermanAlignment) {
    this.useSmithWatermanAlignment = useSmithWatermanAlignment;
    return this;
  }
}
