package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = ReportJobRequestImpl.class
)
public interface ReportJobRequest {
  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);

  @JsonProperty(
      value = "blastConfig",
      defaultValue = "{\n"
              + "\"formatType\" : \"pairwise\"\n"
              + "}"
  )
  BlastFormatConfig getBlastConfig();

  @JsonProperty(
      value = "blastConfig",
      defaultValue = "{\n"
              + "\"formatType\" : \"pairwise\"\n"
              + "}"
  )
  void setBlastConfig(BlastFormatConfig blastConfig);

  @JsonProperty("userMeta")
  ReportJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(ReportJobUserMeta userMeta);
}
