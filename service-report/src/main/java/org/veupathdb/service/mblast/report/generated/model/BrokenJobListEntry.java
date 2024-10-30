package org.veupathdb.service.mblast.report.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;

@JsonDeserialize(
    as = BrokenJobListEntryImpl.class
)
public interface BrokenJobListEntry {
  @JsonProperty("reportJobID")
  String getReportJobID();

  @JsonProperty("reportJobID")
  void setReportJobID(String reportJobID);

  @JsonProperty("rawConfig")
  Object getRawConfig();

  @JsonProperty("rawConfig")
  void setRawConfig(Object rawConfig);

  @JsonProperty("createdOn")
  OffsetDateTime getCreatedOn();

  @JsonProperty("createdOn")
  void setCreatedOn(OffsetDateTime createdOn);

  @JsonProperty("failedOn")
  OffsetDateTime getFailedOn();

  @JsonProperty("failedOn")
  void setFailedOn(OffsetDateTime failedOn);
}
