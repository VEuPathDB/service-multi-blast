package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID

data class QueryJobListEntry(
  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("site")
  val site: TargetSite,

  @JsonProperty("meta")
  val meta: QueryJobUserMeta?
)