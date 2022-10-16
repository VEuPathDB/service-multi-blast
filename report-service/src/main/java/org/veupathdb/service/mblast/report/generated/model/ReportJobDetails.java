package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = ReportJobDetailsImpl.class
)
public interface ReportJobDetails {
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

  @JsonProperty("blastConfig")
  BlastFormatConfig getBlastConfig();

  @JsonProperty("blastConfig")
  void setBlastConfig(BlastFormatConfig blastConfig);

  @JsonProperty("userMeta")
  ReportJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(ReportJobUserMeta userMeta);
}
