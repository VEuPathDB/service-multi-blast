package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;

@JsonDeserialize(
    as = QueryJobListEntryImpl.class
)
public interface QueryJobListEntry {
  @JsonProperty("queryJobID")
  String getQueryJobID();

  @JsonProperty("queryJobID")
  void setQueryJobID(String queryJobID);

  @JsonProperty("status")
  JobStatus getStatus();

  @JsonProperty("status")
  void setStatus(JobStatus status);

  @JsonProperty("site")
  TargetSite getSite();

  @JsonProperty("site")
  void setSite(TargetSite site);

  @JsonProperty("createdOn")
  OffsetDateTime getCreatedOn();

  @JsonProperty("createdOn")
  void setCreatedOn(OffsetDateTime createdOn);

  @JsonProperty("userMeta")
  QueryJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(QueryJobUserMeta userMeta);
}
