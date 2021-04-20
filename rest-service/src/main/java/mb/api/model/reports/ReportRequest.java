package mb.api.model.reports;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.model.BlastReportField;
import mb.lib.blast.model.BlastReportType;
import mb.lib.blast.model.HitSorting;
import mb.lib.blast.model.HSPSorting;
import mb.lib.util.MD5;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportRequest
{
  private BlastReportType        type;
  private String                 delim;
  private List<BlastReportField> fields;
  private Boolean                showGIs;
  private Integer                numDescriptions;
  private Integer                numAlignments;
  private Integer                lineLength;
  private Boolean                html;
  private HitSorting             sortHits;
  private HSPSorting             sortHSPs;
  private Integer                maxTargetSeqs;
  private Boolean                parseDefLines;

  @JsonGetter(JsonKeys.Format)
  public BlastReportType getType() {
    return type;
  }

  @JsonSetter(JsonKeys.Format)
  public ReportRequest setType(BlastReportType type) {
    this.type = type;
    return this;
  }

  @JsonGetter(JsonKeys.FieldDelim)
  public String getDelim() {
    return delim;
  }

  @JsonSetter(JsonKeys.FieldDelim)
  public ReportRequest setDelim(String delim) {
    this.delim = delim;
    return this;
  }

  @JsonGetter(JsonKeys.Fields)
  public List<BlastReportField> getFields() {
    return fields;
  }

  @JsonSetter(JsonKeys.Fields)
  public ReportRequest setFields(List<BlastReportField> fields) {
    this.fields = fields;
    return this;
  }

  @JsonGetter(JsonKeys.ShowNCBIGIs)
  public Boolean getShowGIs() {
    return showGIs;
  }

  @JsonSetter(JsonKeys.ShowNCBIGIs)
  public ReportRequest setShowGIs(Boolean showGIs) {
    this.showGIs = showGIs;
    return this;
  }

  @JsonGetter(JsonKeys.NumDescriptions)
  public Integer getNumDescriptions() {
    return numDescriptions;
  }

  @JsonSetter(JsonKeys.NumDescriptions)
  public ReportRequest setNumDescriptions(Integer numDescriptions) {
    this.numDescriptions = numDescriptions;
    return this;
  }

  @JsonGetter(JsonKeys.NumAlignments)
  public Integer getNumAlignments() {
    return numAlignments;
  }

  @JsonSetter(JsonKeys.NumAlignments)
  public ReportRequest setNumAlignments(Integer numAlignments) {
    this.numAlignments = numAlignments;
    return this;
  }

  @JsonGetter(JsonKeys.LineLength)
  public Integer getLineLength() {
    return lineLength;
  }

  @JsonSetter(JsonKeys.LineLength)
  public ReportRequest setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
    return this;
  }

  @JsonGetter(JsonKeys.HTML)
  public Boolean getHTML() {
    return html;
  }

  @JsonSetter(JsonKeys.HTML)
  public ReportRequest setHTML(Boolean html) {
    this.html = html;
    return this;
  }

  @JsonGetter(JsonKeys.SortHits)
  public HitSorting getSortHits() {
    return sortHits;
  }

  @JsonSetter(JsonKeys.SortHits)
  public ReportRequest setSortHits(HitSorting sortHits) {
    this.sortHits = sortHits;
    return this;
  }

  @JsonSetter(JsonKeys.SortHSPs)
  public HSPSorting getSortHSPs() {
    return sortHSPs;
  }

  @JsonSetter(JsonKeys.SortHSPs)
  public ReportRequest setSortHSPs(HSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
    return this;
  }

  @JsonGetter(JsonKeys.MaxTargetSequences)
  public Integer getMaxTargetSeqs() {
    return maxTargetSeqs;
  }

  @JsonSetter(JsonKeys.MaxTargetSequences)
  public ReportRequest setMaxTargetSeqs(Integer maxTargetSeqs) {
    this.maxTargetSeqs = maxTargetSeqs;
    return this;
  }

  @JsonGetter(JsonKeys.ParseDefLines)
  public Boolean getParseDefLines() {
    return parseDefLines;
  }

  @JsonSetter(JsonKeys.ParseDefLines)
  public ReportRequest setParseDefLines(Boolean parseDefLines) {
    this.parseDefLines = parseDefLines;
    return this;
  }

  @JsonIgnore
  public byte[] hashConfig() {
    return MD5.hash(hashString());
  }

  private String hashString() {
    return "ReportRequest{" +
      "type=" + type.externalName() +
      ", delim='" + delim + '\'' +
      ", fields=" + fields.stream()
      .map(BlastReportField::toString)
      .sorted()
      .collect(Collectors.joining(",", "[", "]"))+
      ", showGIs=" + showGIs +
      ", numDescriptions=" + numDescriptions +
      ", numAlignments=" + numAlignments +
      ", lineLength=" + lineLength +
      ", html=" + html +
      ", sortHits=" + sortHits +
      ", sortHSPs=" + sortHSPs +
      ", maxTargetSeqs=" + maxTargetSeqs +
      ", parseDefLines=" + parseDefLines +
      '}';
  }
}
