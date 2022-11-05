package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobUserMeta(
  @JsonProperty("summary")
  val summary: String?,

  @JsonProperty("description")
  val description: String?
)

