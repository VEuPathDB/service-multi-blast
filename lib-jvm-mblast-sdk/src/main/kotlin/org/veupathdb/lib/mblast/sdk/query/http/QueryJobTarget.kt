package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobTarget(
  @JsonProperty("targetDisplayName")
  val targetDisplayName: String,

  @JsonProperty("targetFile")
  val targetFile: String,
)