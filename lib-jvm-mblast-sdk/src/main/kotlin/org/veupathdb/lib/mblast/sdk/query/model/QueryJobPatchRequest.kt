package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobPatchRequest(
  @JsonProperty("meta")
  val meta: QueryJobUserMeta?
)