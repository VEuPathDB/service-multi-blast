package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = ReportJobPatchRequestImpl.class
)
public interface ReportJobPatchRequest {
  @JsonProperty("userMeta")
  ReportJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(ReportJobUserMeta userMeta);
}
