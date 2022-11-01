package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.query.blast.BlastQueryConfig

data class QueryJobDetails(
  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("jobConfig")
  val jobConfig: QueryJobConfig,

  @JsonProperty("blastConfig")
  val blastConfig: BlastQueryConfig,

  @JsonProperty("meta")
  val meta: QueryJobUserMeta?,

  @JsonProperty("subJobs")
  val subJobs: List<HashID>,
)

