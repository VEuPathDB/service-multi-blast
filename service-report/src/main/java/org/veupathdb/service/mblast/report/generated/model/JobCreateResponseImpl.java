package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("reportJobID")
public class JobCreateResponseImpl implements JobCreateResponse {
  @JsonProperty("reportJobID")
  private String reportJobID;

  @JsonProperty("reportJobID")
  public String getReportJobID() {
    return this.reportJobID;
  }

  @JsonProperty("reportJobID")
  public void setReportJobID(String reportJobID) {
    this.reportJobID = reportJobID;
  }
}
