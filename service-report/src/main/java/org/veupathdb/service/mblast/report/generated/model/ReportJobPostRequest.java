package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
    as = ReportJobPostRequestImpl.class
)
public interface ReportJobPostRequest {
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

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "true"
  )
  Boolean getAddToUserCollection();

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "true"
  )
  void setAddToUserCollection(Boolean addToUserCollection);

  @JsonProperty("userMeta")
  ReportJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(ReportJobUserMeta userMeta);
}
