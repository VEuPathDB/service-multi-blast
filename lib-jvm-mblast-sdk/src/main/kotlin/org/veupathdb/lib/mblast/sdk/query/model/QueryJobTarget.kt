package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobTarget(
  @JsonProperty("targetDisplayName")
  val targetDisplayName: String,

  @JsonProperty("targetFile")
  val targetFile: String,
)