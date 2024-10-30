package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;

@JsonDeserialize(
    as = BrokenJobListEntryImpl.class
)
public interface BrokenJobListEntry {
  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);

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
