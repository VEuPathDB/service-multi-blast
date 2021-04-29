package mb.api.model.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.api.model.io.JsonKeys;
import mb.lib.blast.model.IOHSPSorting;
import mb.lib.blast.model.IOHitSorting;
import mb.lib.model.HashID;
import org.veupathdb.lib.blast.BlastFormatter;
import org.veupathdb.lib.blast.field.FormatField;
import org.veupathdb.lib.blast.field.FormatType;
import org.veupathdb.lib.blast.field.OutFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportRequest
{
  private HashID            jobID;
  private String            description;
  private FormatType        type;
  private String            delim;
  private List<FormatField> fields;
  private Boolean           showGIs;
  private Long              numDescriptions;
  private Long              numAlignments;
  private Integer           lineLength;
  private Boolean           html;
  private IOHitSorting      sortHits;
  private IOHSPSorting      sortHSPs;
  private Long              maxTargetSeqs;
  private Boolean           parseDefLines;

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonSetter(JsonKeys.JobID)
  public ReportRequest setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }

  @JsonGetter(JsonKeys.Description)
  public String getDescription() {
    return description;
  }

  @JsonSetter(JsonKeys.Description)
  public ReportRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  @JsonGetter(JsonKeys.Format)
  public FormatType getType() {
    return type;
  }

  @JsonSetter(JsonKeys.Format)
  public ReportRequest setType(FormatType type) {
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
  public List<FormatField> getFields() {
    return fields;
  }

  @JsonSetter(JsonKeys.Fields)
  public ReportRequest setFields(List<FormatField> fields) {
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
  public Long getNumDescriptions() {
    return numDescriptions;
  }

  @JsonSetter(JsonKeys.NumDescriptions)
  public ReportRequest setNumDescriptions(Long numDescriptions) {
    this.numDescriptions = numDescriptions;
    return this;
  }

  @JsonGetter(JsonKeys.NumAlignments)
  public Long getNumAlignments() {
    return numAlignments;
  }

  @JsonSetter(JsonKeys.NumAlignments)
  public ReportRequest setNumAlignments(Long numAlignments) {
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
  public IOHitSorting getSortHits() {
    return sortHits;
  }

  @JsonSetter(JsonKeys.SortHits)
  public ReportRequest setSortHits(IOHitSorting sortHits) {
    this.sortHits = sortHits;
    return this;
  }

  @JsonSetter(JsonKeys.SortHSPs)
  public IOHSPSorting getSortHSPs() {
    return sortHSPs;
  }

  @JsonSetter(JsonKeys.SortHSPs)
  public ReportRequest setSortHSPs(IOHSPSorting sortHSPs) {
    this.sortHSPs = sortHSPs;
    return this;
  }

  @JsonGetter(JsonKeys.MaxTargetSequences)
  public Long getMaxTargetSeqs() {
    return maxTargetSeqs;
  }

  @JsonSetter(JsonKeys.MaxTargetSequences)
  public ReportRequest setMaxTargetSeqs(Long maxTargetSeqs) {
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

  public BlastFormatter toInternalValue() {
    var out = new BlastFormatter();

    out.setOutFormat(new OutFormat().setType(type).setDelimiter(delim).setFields(fields));
    out.setShowGIs(showGIs);
    out.setNumDescriptions(numDescriptions);
    out.setNumAlignments(numAlignments);
    out.setLineLength(lineLength);
    out.setHTML(html);
    out.setSortHits(sortHits.toInternalValue());
    out.setSortHSPs(sortHSPs.toInternalValue());
    out.setMaxTargetSequences(maxTargetSeqs);
    out.setParseDefLines(parseDefLines);

    return out;
  }

  public static ReportRequest fromInternalValue(BlastFormatter val) {
    var out = new ReportRequest();

    if (val.getOutFormat() != null) {
      out.setType(val.getOutFormat().getType());
      out.setDelim(val.getOutFormat().getDelimiter());
      if (val.getOutFormat().getFields() != null && val.getOutFormat().getFields().size() > 0)
        out.setFields(val.getOutFormat().getFields());
    }

    out.setShowGIs(val.getShowGIs());
    out.setNumDescriptions(val.getNumDescriptions());
    out.setNumAlignments(val.getNumAlignments());
    out.setLineLength(val.getLineLength());
    out.setHTML(val.getHTML());
    out.setSortHits(IOHitSorting.fromInternalValue(val.getSortHits()));
    out.setSortHSPs(IOHSPSorting.fromInternalValue(val.getSortHSPs()));
    out.setMaxTargetSeqs(val.getMaxTargetSequences());
    out.setParseDefLines(val.getParseDefLines());

    return out;
  }
}
