package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty

data class QueryJobPatchRequest(
  @JsonProperty("meta")
  val meta: QueryJobUserMeta?
)