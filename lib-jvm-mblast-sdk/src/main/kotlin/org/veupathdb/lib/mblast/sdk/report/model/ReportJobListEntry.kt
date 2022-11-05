package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus

data class ReportJobListEntry(
  @JsonProperty("reportJobID")
  val reportJobID: HashID,

  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("userMeta")
  val userMeta: ReportJobUserMeta?
)
