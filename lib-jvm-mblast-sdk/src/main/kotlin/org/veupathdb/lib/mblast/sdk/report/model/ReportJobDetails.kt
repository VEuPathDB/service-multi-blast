package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus
import org.veupathdb.lib.mblast.sdk.report.blast.BlastFormatConfig

data class ReportJobDetails(
  @JsonProperty("reportJobID")
  val reportJobID: HashID,

  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("status")
  val status: JobStatus,

  @JsonProperty("blastConfig")
  val blastConfig: BlastFormatConfig,

  @JsonProperty("userMeta")
  val userMeta: ReportJobUserMeta?,
)

