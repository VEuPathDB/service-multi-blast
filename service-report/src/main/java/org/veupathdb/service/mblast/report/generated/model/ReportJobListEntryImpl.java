package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reportJobID",
    "queryJobID",
    "status",
    "userMeta"
})
public class ReportJobListEntryImpl implements ReportJobListEntry {
  @JsonProperty("reportJobID")
  private String reportJobID;

  @JsonProperty("queryJobID")
  private String queryJobID;

  @JsonProperty("status")
  private JobStatus status;

  @JsonProperty("userMeta")
  private ReportJobUserMeta userMeta;

  @JsonProperty("reportJobID")
  public String getReportJobID() {
    return this.reportJobID;
  }

  @JsonProperty("reportJobID")
  public void setReportJobID(String reportJobID) {
    this.reportJobID = reportJobID;
  }

  @JsonProperty("queryJobID")
  public String getQueryJobID() {
    return this.queryJobID;
  }

  @JsonProperty("queryJobID")
  public void setQueryJobID(String queryJobID) {
    this.queryJobID = queryJobID;
  }

  @JsonProperty("status")
  public JobStatus getStatus() {
    return this.status;
  }

  @JsonProperty("status")
  public void setStatus(JobStatus status) {
    this.status = status;
  }

  @JsonProperty("userMeta")
  public ReportJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(ReportJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }
}
