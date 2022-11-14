package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus
import java.time.OffsetDateTime

data class QueryJobListEntry(
  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("site")
  val site: TargetSite,

  @JsonProperty("createdOn")
  val createdOn: OffsetDateTime,

  @JsonProperty("userMeta")
  val userMeta: QueryJobUserMeta?
)