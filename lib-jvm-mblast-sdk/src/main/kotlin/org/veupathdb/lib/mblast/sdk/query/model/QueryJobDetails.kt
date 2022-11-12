package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus
import org.veupathdb.lib.mblast.sdk.query.blast.BlastQueryConfig
import java.time.OffsetDateTime

data class QueryJobDetails(
  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("jobConfig")
  val jobConfig: QueryJobConfig,

  @JsonProperty("blastConfig")
  val blastConfig: BlastQueryConfig,

  @JsonProperty("createdOn")
  val createdOn: OffsetDateTime,

  @JsonProperty("userMeta")
  val userMeta: QueryJobUserMeta?,

  @JsonProperty("subJobs")
  val subJobs: List<HashID>,
)

