package org.veupathdb.lib.mblast.sdk.report.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReportJobPatchRequest(
  @JsonProperty("userMeta")
  val userMeta: ReportJobUserMeta?,
)