package org.veupathdb.lib.mblast.sdk.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.mblast.sdk.query.blast.*

data class QueryJobPostRequest(
  @JsonProperty("jobConfig")
  val jobConfig: QueryJobConfig,

  @JsonProperty("blastConfig")
  val blastConfig: BlastQueryConfig,

  @JsonProperty("userMeta")
  val userMeta: QueryJobUserMeta?,
)


