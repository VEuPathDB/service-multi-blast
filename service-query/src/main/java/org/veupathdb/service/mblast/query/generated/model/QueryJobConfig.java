package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(
    as = QueryJobConfigImpl.class
)
public interface QueryJobConfig {
  @JsonProperty("site")
  TargetSite getSite();

  @JsonProperty("site")
  void setSite(TargetSite site);

  @JsonProperty("targets")
  List<QueryJobTarget> getTargets();

  @JsonProperty("targets")
  void setTargets(List<QueryJobTarget> targets);

  @JsonProperty("query")
  String getQuery();

  @JsonProperty("query")
  void setQuery(String query);

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "false"
  )
  Boolean getAddToUserCollection();

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "false"
  )
  void setAddToUserCollection(Boolean addToUserCollection);
}
