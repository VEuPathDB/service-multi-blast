package mb.lib.extern.model.format;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobPayload
{
  private String    jobID;
  private Integer   format;
  private Character fieldDelim;
  private String[]  fields;
  private Boolean   showGIs;
  private Integer   numDescriptions;
  private Integer   numAlignments;
  private Integer   lineLength;
  private Boolean   html;
  private Integer   sortHits;
  private Integer   sortHSPs;
  private Integer   maxTargetSeqs;
  private Boolean   parseDeflines;

  @JsonGetter("jobID")
  public String getJobID() {
    return jobID;
  }

  @JsonSetter
  public JobPayload setJobID(String jobID) {
    this.jobID = jobID;
    return this;
  }

  @JsonGetter("format")
  public Integer getFormat() {
    return format;
  }

  @JsonSetter
  public JobPayload setFormat(Integer format) {
    this.format = format;
    return this;
  }

  @JsonGetter("fieldDelim")
  public Character getFieldDelim() {
    return fieldDelim;
  }

  @JsonSetter
  public JobPayload setFieldDelim(Character fieldDelim) {
    this.fieldDelim = fieldDelim;
    return this;
  }

  @JsonGetter("fields")
  public String[] getFields() {
    return fields;
  }

  @JsonSetter
  public JobPayload setFields(String[] fields) {
    this.fields = fields;
    return this;
  }

  @JsonGetter("showGIs")
  public Boolean getShowGIs() {
    return showGIs;
  }

  @JsonSetter
  public JobPayload setShowGIs(Boolean showGIs) {
    this.showGIs = showGIs;
    return this;
  }

  @JsonGetter("numDescriptions")
  public Integer getNumDescriptions() {
    return numDescriptions;
  }

  @JsonSetter
  public JobPayload setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
    return this;
  }

  @JsonGetter("numAlignments")
  public Integer getNumAlignments() {
    return numAlignments;
  }

  @JsonSetter
  public JobPayload setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
    return this;
  }

  @JsonGetter("lineLength")
  public Integer getLineLength() {
    return lineLength;
  }

  @JsonSetter
  public JobPayload setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
    return this;
  }

  @JsonGetter("html")
  public Boolean getHtml() {
    return html;
  }

  @JsonSetter
  public JobPayload setHtml(Boolean html) {
    this.html = html;
    return this;
  }

  @JsonGetter("sortHits")
  public Integer getSortHits() {
    return sortHits;
  }

  @JsonSetter
  public JobPayload setSortHits(Integer sortHits) {
    this.sortHits = sortHits;
    return this;
  }

  @JsonGetter("sortHSPs")
  public Integer getSortHSPs() {
    return sortHSPs;
  }

  @JsonSetter
  public JobPayload setSortHSPs(Integer sortHSPs) {
    this.sortHSPs = sortHSPs;
    return this;
  }

  @JsonGetter("maxTargetSeqs")
  public Integer getMaxTargetSeqs() {
    return maxTargetSeqs;
  }

  @JsonSetter
  public JobPayload setMaxTargetSeqs(Integer maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
    return this;
  }

  @JsonGetter("parseDeflines")
  public Boolean getParseDeflines() {
    return parseDeflines;
  }

  @JsonSetter
  public JobPayload setParseDeflines(Boolean parseDeflines) {
    this.parseDeflines = parseDeflines;
    return this;
  }
}
