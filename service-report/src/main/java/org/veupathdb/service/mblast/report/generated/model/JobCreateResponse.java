package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = JobCreateResponseImpl.class
)
public interface JobCreateResponse {
  @JsonProperty("reportJobID")
  String getReportJobID();

  @JsonProperty("reportJobID")
  void setReportJobID(String reportJobID);
}
