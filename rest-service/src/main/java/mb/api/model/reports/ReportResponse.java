package mb.api.model.reports;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import mb.api.model.io.JsonKeys;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReportResponse
{
  private final HashID        jobID;
  private final HashID        reportID;
  private final ReportRequest config;
  private final JobStatus     status;
  private final List<String>  files;
  private final String        description;

  public ReportResponse(
    HashID jobID,
    HashID reportID,
    ReportRequest config,
    JobStatus status,
    String description
  ) {
    this.jobID       = jobID;
    this.reportID    = reportID;
    this.config      = config;
    this.status      = status;
    this.files       = new ArrayList<>();
    this.description = description;
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

  @JsonGetter(JsonKeys.Files)
  public List<String> getFiles() {
    return files;
  }

  @JsonGetter(JsonKeys.Description)
  public String getDescription() {
    return description;
  }
}
