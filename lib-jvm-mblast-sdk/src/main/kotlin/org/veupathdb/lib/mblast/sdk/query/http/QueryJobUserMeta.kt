package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobUserMeta(
  @JsonProperty("summary")
  val summary: String?,

  @JsonProperty("description")
  val description: String?
)

