package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobPatchRequest(
  @JsonProperty("userMeta")
  val userMeta: QueryJobUserMeta?
)