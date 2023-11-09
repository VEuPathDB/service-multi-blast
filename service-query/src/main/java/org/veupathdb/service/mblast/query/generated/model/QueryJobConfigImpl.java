package org.veupathdb.service.mblast.query.generated.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "site",
    "targets",
    "query",
    "addToUserCollection"
})
public class QueryJobConfigImpl implements QueryJobConfig {
  @JsonProperty("site")
  private TargetSite site;

  @JsonProperty("targets")
  private List<QueryJobTarget> targets;

  @JsonProperty("query")
  private String query;

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "false"
  )
  private Boolean addToUserCollection;

  @JsonProperty("site")
  public TargetSite getSite() {
    return this.site;
  }

  @JsonProperty("site")
  public void setSite(TargetSite site) {
    this.site = site;
  }

  @JsonProperty("targets")
  public List<QueryJobTarget> getTargets() {
    return this.targets;
  }

  @JsonProperty("targets")
  public void setTargets(List<QueryJobTarget> targets) {
    this.targets = targets;
  }

  @JsonProperty("query")
  public String getQuery() {
    return this.query;
  }

  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "false"
  )
  public Boolean getAddToUserCollection() {
    return this.addToUserCollection;
  }

  @JsonProperty(
      value = "addToUserCollection",
      defaultValue = "false"
  )
  public void setAddToUserCollection(Boolean addToUserCollection) {
    this.addToUserCollection = addToUserCollection;
  }
}
