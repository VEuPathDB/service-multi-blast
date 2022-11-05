package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.report.blast.BlastFormatConfig

data class ReportJobPostRequest(
  @JsonProperty("queryJobID")
  val queryJobID: HashID,

  @JsonProperty("blastConfig")
  val blastConfig: BlastFormatConfig?,

  @JsonProperty("addToUserCollection")
  val addToUserCollection: Boolean?,

  @JsonProperty("userMeta")
  val userMeta: ReportJobUserMeta?
)
