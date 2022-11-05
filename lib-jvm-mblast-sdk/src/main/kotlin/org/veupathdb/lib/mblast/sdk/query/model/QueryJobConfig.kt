package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobConfig(
  @JsonProperty("site")
  val site: TargetSite,

  @JsonProperty("targets")
  val targets: List<QueryJobTarget>,

  @JsonProperty("query")
  val query: String?,

  @JsonProperty("addToUserCollection")
  val addToUserCollection: Boolean?,
)

