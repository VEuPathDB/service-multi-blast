package org.veupathdb.service.multiblast.model.blast;

import java.io.File;

public class BlastXConfig extends StdBlastConfig
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
  private Short wordSize;

  public BlastxTask getTask() {
    return task;
  }

  public Short getWordSize() {
    return wordSize;
  }

  public BlastXConfig setTask(BlastxTask task) {
    this.task = task;
    return this;
  }

  public File getIpgList() {
    return ipgList;
  }

  public BlastXConfig setIpgList(File ipgList) {
    this.ipgList = ipgList;
    return this;
  }

  public File getNegativeIpgList() {
    return negativeIpgList;
  }

  public BlastXConfig setNegativeIpgList(File negativeIpgList) {
    this.negativeIpgList = negativeIpgList;
    return this;
  }

  public Integer getGapOpen() {
    return gapOpen;
  }

  public BlastXConfig setGapOpen(Integer gapOpen) {
    this.gapOpen = gapOpen;
    return this;
  }

  public Integer getGapExtend() {
    return gapExtend;
  }

  public BlastXConfig setGapExtend(Integer gapExtend) {
    this.gapExtend = gapExtend;
    return this;
  }

  public Double getxDropGap() {
    return xDropGap;
  }

  public StdBlastConfig setWordSize(Short wordSize) {
    this.wordSize = wordSize;
    return this;
  }

  public BlastXConfig setxDropGap(Double xDropGap) {
    this.xDropGap = xDropGap;
    return this;
  }

  public Double getxDropGapFinal() {
    return xDropGapFinal;
  }

  public BlastXConfig setxDropGapFinal(Double xDropGapFinal) {
    this.xDropGapFinal = xDropGapFinal;
    return this;
  }

  public Boolean getSumStats() {
    return sumStats;
  }

  public BlastXConfig setSumStats(Boolean sumStats) {
    this.sumStats = sumStats;
    return this;
  }

  public Integer getMaxIntronLength() {
    return maxIntronLength;
  }

  public BlastXConfig setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
    return this;
  }

  public Seg getSeg() {
    return seg;
  }

  public BlastXConfig setSeg(Seg seg) {
    this.seg = seg;
    return this;
  }

  public BlastxScoringMatrix getMatrix() {
    return matrix;
  }

  public BlastXConfig setMatrix(BlastxScoringMatrix matrix) {
    this.matrix = matrix;
    return this;
  }

  public Double getThreshold() {
    return threshold;
  }

  public BlastXConfig setThreshold(Double threshold) {
    this.threshold = threshold;
    return this;
  }

  public Integer getCullingLimit() {
    return cullingLimit;
  }

  public BlastXConfig setCullingLimit(Integer cullingLimit) {
    this.cullingLimit = cullingLimit;
    return this;
  }

  public Double getBestHitOverhang() {
    return bestHitOverhang;
  }

  public BlastXConfig setBestHitOverhang(Double bestHitOverhang) {
    this.bestHitOverhang = bestHitOverhang;
    return this;
  }

  public Double getBestHitScoreEdge() {
    return bestHitScoreEdge;
  }

  public BlastXConfig setBestHitScoreEdge(Double bestHitScoreEdge) {
    this.bestHitScoreEdge = bestHitScoreEdge;
    return this;
  }

  public Boolean getSubjectBestHit() {
    return subjectBestHit;
  }

  public BlastXConfig setSubjectBestHit(Boolean subjectBestHit) {
    this.subjectBestHit = subjectBestHit;
    return this;
  }

  public Boolean getUngapped() {
    return ungapped;
  }

  public BlastXConfig setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
    return this;
  }

  public QueryStrand getStrand() {
    return strand;
  }

  public BlastXConfig setStrand(QueryStrand strand) {
    this.strand = strand;
    return this;
  }

  public Integer getQueryGenCode() {
    return queryGenCode;
  }

  public BlastXConfig setQueryGenCode(Integer queryGenCode) {
    this.queryGenCode = queryGenCode;
    return this;
  }

  public CompBasedStats getCompBasedStats() {
    return compBasedStats;
  }

  public BlastXConfig setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
    return this;
  }

  public Boolean getUseSmithWatermanAlignment() {
    return useSmithWatermanAlignment;
  }

  public BlastXConfig setUseSmithWatermanAlignment(Boolean useSmithWatermanAlignment) {
    this.useSmithWatermanAlignment = useSmithWatermanAlignment;
    return this;
  }
}
