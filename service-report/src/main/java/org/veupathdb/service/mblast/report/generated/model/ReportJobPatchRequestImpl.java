package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("userMeta")
public class ReportJobPatchRequestImpl implements ReportJobPatchRequest {
  @JsonProperty("userMeta")
  private ReportJobUserMeta userMeta;

  @JsonProperty("userMeta")
  public ReportJobUserMeta getUserMeta() {
    return this.userMeta;
  }

  @JsonProperty("userMeta")
  public void setUserMeta(ReportJobUserMeta userMeta) {
    this.userMeta = userMeta;
  }
}
