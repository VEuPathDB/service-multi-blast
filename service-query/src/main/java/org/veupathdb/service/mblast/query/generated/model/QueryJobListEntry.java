package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;

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
  Date getCreatedOn();

  @JsonProperty("createdOn")
  void setCreatedOn(Date createdOn);

  @JsonProperty("userMeta")
  QueryJobUserMeta getUserMeta();

  @JsonProperty("userMeta")
  void setUserMeta(QueryJobUserMeta userMeta);
}
