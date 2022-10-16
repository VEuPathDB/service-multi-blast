package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = ReportJobListEntryImpl.class
)
public interface ReportJobListEntry {
  @JsonProperty("reportJobID")
  String getReportJobID();

  @JsonProperty("reportJobID")
  void setReportJobID(String reportJobID);

  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);

  @JsonProperty("status")
  JobStatus getStatus();

  @JsonProperty("status")
  void setStatus(JobStatus status);

  @JsonProperty("userMeta")
  ReportJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(ReportJobUserMeta userMeta);
}
