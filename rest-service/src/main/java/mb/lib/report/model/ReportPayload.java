package mb.lib.report.model;

import com.fasterxml.jackson.annotation.*;
import mb.api.model.io.JsonKeys;
import mb.lib.model.HashID;
import org.veupathdb.lib.blast.BlastFormatter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReportPayload
{
  private HashID         jobID;
  private HashID         reportID;
  private BlastFormatter config;

  @JsonCreator
  public ReportPayload(
    @JsonProperty(JsonKeys.JobID)    HashID         jobID,
    @JsonProperty(JsonKeys.ReportID) HashID         reportID,
    @JsonProperty(JsonKeys.Config)   BlastFormatter config
  ) {
    this.jobID    = jobID;
    this.reportID = reportID;
    this.config   = config;
  }

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonSetter(JsonKeys.JobID)
  public ReportPayload setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }

  @JsonGetter(JsonKeys.Config)
  public BlastFormatter getConfig() {
    return config;
  }

  @JsonSetter(JsonKeys.Config)
  public ReportPayload setConfig(BlastFormatter config) {
    this.config = config;
    return this;
  }

  @JsonGetter(JsonKeys.ReportID)
  public HashID getReportID() {
    return reportID;
  }

  @JsonSetter(JsonKeys.ReportID)
  public ReportPayload setReportID(HashID reportID) {
    this.reportID = reportID;
    return this;
  }
}
