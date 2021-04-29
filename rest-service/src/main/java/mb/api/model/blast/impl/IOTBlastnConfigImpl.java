package mb.api.model.blast.impl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import mb.api.model.blast.IOTBlastnConfig;
import mb.lib.blast.model.CompBasedStats;
import org.veupathdb.lib.blast.BlastTool;
import org.veupathdb.lib.blast.field.ScoringMatrix;
import org.veupathdb.lib.blast.field.Seg;
import org.veupathdb.lib.blast.field.TBlastNTask;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("tblastn")
public class IOTBlastnConfigImpl extends IOBlastConfigImpl implements IOTBlastnConfig
{
  private final BlastTool tool = IOTBlastnConfig._DISCRIMINATOR_TYPE_NAME;

  private TBlastNTask          task;
  private Integer              wordSize;
  private Integer              gapOpen;
  private Integer              gapExtend;
  private Byte                 dbGencode;
  private Integer       maxIntronLength;
  private ScoringMatrix matrix;
  private Double        threshold;
  private CompBasedStats       compBasedStats;
  private Seg                  seg;
  private Boolean              softMasking;
  private List<Integer>        taxIds;
  private List<Integer>        negativeTaxIds;
  private String               dbSoftMask;
  private String               dbHardMask;
  private Integer              cullingLimit;
  private Double               bestHitOverhang;
  private Double               bestHitScoreEdge;
  private Boolean              subjectBestHit;
  private Boolean              sumStats;
  private Double               xDropGap;
  private Double               xDropGapFinal;
  private Boolean              ungapped;
  private Integer              windowSize;
  private Boolean              useSWTraceback;

  @JsonProperty("tool")
  public BlastTool getTool() {
    return this.tool;
  }

  @JsonProperty("task")
  public TBlastNTask getTask() {
    return this.task;
  }

  @JsonProperty("task")
  public void setTask(TBlastNTask task) {
    this.task = task;
  }

  @JsonProperty("wordSize")
  public Integer getWordSize() {
    return this.wordSize;
  }

  @JsonProperty("wordSize")
  public void setWordSize(Integer wordSize) {
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

  @JsonProperty("dbGencode")
  public Byte getDbGencode() {
    return this.dbGencode;
  }

  @JsonProperty("dbGencode")
  public void setDbGencode(Byte dbGencode) {
    this.dbGencode = dbGencode;
  }

  @JsonProperty("maxIntronLength")
  public Integer getMaxIntronLength() {
    return this.maxIntronLength;
  }

  @JsonProperty("maxIntronLength")
  public void setMaxIntronLength(Integer maxIntronLength) {
    this.maxIntronLength = maxIntronLength;
  }

  @JsonProperty("matrix")
  public ScoringMatrix getMatrix() {
    return this.matrix;
  }

  @JsonProperty("matrix")
  public void setMatrix(ScoringMatrix matrix) {
    this.matrix = matrix;
  }

  @JsonProperty("threshold")
  public Double getThreshold() {
    return this.threshold;
  }

  @JsonProperty("threshold")
  public void setThreshold(Double threshold) {
    this.threshold = threshold;
  }

  @JsonProperty("compBasedStats")
  public CompBasedStats getCompBasedStats() {
    return this.compBasedStats;
  }

  @JsonProperty("compBasedStats")
  public void setCompBasedStats(CompBasedStats compBasedStats) {
    this.compBasedStats = compBasedStats;
  }

  @JsonProperty("seg")
  public Seg getSeg() {
    return this.seg;
  }

  @JsonProperty("seg")
  public void setSeg(Seg seg) {
    this.seg = seg;
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

  @JsonProperty("cullingLimit")
  public Integer getCullingLimit() {
    return this.cullingLimit;
  }

  @JsonProperty("cullingLimit")
  public void setCullingLimit(Integer cullingLimit) {
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

  @JsonProperty("ungapped")
  public Boolean getUngapped() {
    return this.ungapped;
  }

  @JsonProperty("ungapped")
  public void setUngapped(Boolean ungapped) {
    this.ungapped = ungapped;
  }

  @JsonProperty("windowSize")
  public Integer getWindowSize() {
    return this.windowSize;
  }

  @JsonProperty("windowSize")
  public void setWindowSize(Integer windowSize) {
    this.windowSize = windowSize;
  }

  @JsonProperty("useSWTraceback")
  public Boolean getUseSWTraceback() {
    return this.useSWTraceback;
  }

  @JsonProperty("useSWTraceback")
  public void setUseSWTraceback(Boolean useSWTraceback) {
    this.useSWTraceback = useSWTraceback;
  }
}
