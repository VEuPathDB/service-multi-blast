package mb.lib.formatter.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.api.model.io.JsonKeys;
import mb.api.model.reports.ReportRequest;
import mb.lib.model.HashID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Payload extends ReportRequest
{
  private HashID jobID;

  public Payload(HashID jobID, ReportRequest base) {
    this.jobID = jobID;
    this.setType(base.getType());
    this.setDelim(base.getDelim());
    this.setFields(base.getFields());
    this.setShowGIs(base.getShowGIs());
    this.setNumDescriptions(base.getNumDescriptions());
    this.setNumAlignments(base.getNumAlignments());
    this.setLineLength(base.getLineLength());
    this.setHTML(base.getHTML());
    this.setSortHits(base.getSortHits());
    this.setSortHSPs(base.getSortHSPs());
    this.setMaxTargetSeqs(base.getMaxTargetSeqs());
    this.setParseDefLines(base.getParseDefLines());
  }

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonSetter(JsonKeys.JobID)
  public Payload setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }
}
