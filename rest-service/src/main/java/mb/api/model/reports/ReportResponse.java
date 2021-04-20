package mb.api.model.reports;

import com.fasterxml.jackson.annotation.JsonGetter;
import mb.api.model.io.JsonKeys;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

public class ReportResponse
{
  private final HashID jobID;
  private final HashID reportID;
  private final ReportRequest config;
  private final JobStatus status;

  public ReportResponse(
    HashID jobID,
    HashID reportID,
    ReportRequest config,
    JobStatus status
  ) {
    this.jobID    = jobID;
    this.reportID = reportID;
    this.config   = config;
    this.status   = status;
  }

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonGetter(JsonKeys.ReportID)
  public HashID getReportID() {
    return reportID;
  }

  @JsonGetter(JsonKeys.Config)
  public ReportRequest getConfig() {
    return config;
  }

  @JsonGetter(JsonKeys.Status)
  public JobStatus getStatus() {
    return status;
  }
}
